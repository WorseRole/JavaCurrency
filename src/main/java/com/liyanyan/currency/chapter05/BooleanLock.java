package com.liyanyan.currency.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * Created by liyanyan on 2020/5/30 1:49 上午
 */
public class BooleanLock implements Lock {

    private Thread currentThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {  //使用同步代码块的方式进行方法同步
            while(locked) {  //若当前所已经被某个线程获得，则该线程加入阻塞队列，并且使当前线程wait释放对this monitor的所有权
                //暂存当前线程 要不然如果某个线程被中断，但它有可能还存在于blockList中国，该问题修复，是对BooleanLock的lock方法中，暂存当前线程，最后被中断时将此线程从列表中移除出去
                Thread tempThread = currentThread();
                try {
                    if(!blockedList.contains(tempThread)) {
                        blockedList.add(tempThread);
                    }
                    this.wait();
                }catch (InterruptedException ex) {
                    blockedList.remove(tempThread);
                    throw ex;
                }
                blockedList.add(currentThread);
                this.wait();
            }
            blockedList.remove(currentThread());  //若当前所没有被其他线程获得，则该线程将尝试从阻塞队列中删除自己（没有进过队列删除没有影响，若当前线程是从wait set中被唤醒，则需要自己在阻塞队列中将自己删除）
            this.locked = true;  //locked 开关将被指定为true
            this.currentThread = currentThread();  //记录获取所的线程
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if(mills <= 0) {  //若参数不合法则默认调用lock方法，其实抛出异常最好
                this.lock();
            }else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while(locked) {
                    if(remainingMills <= 0) {  //意味着当前线程被其他线程唤醒或者在指定的wait时间到了之后还没有获得锁，此情况抛异常
                        throw new TimeoutException("can not get the lock during " + mills);
                    }
                    if(!blockedList.contains(currentThread())) {
                        blockedList.add(currentThread());
                    }
                    this.wait(remainingMills);  //等待remainingMills的毫秒数，该值最开始是由其他线程传入，但在多次wait的过程中会重新计算
                    remainingMills = endMills - System.currentTimeMillis();  //重新计算剩余等待时间
                }
                blockedList.remove(currentThread());  //获得该锁并从阻塞队列中删除，blocked状态为true，且指定获得该线程的锁就是当前锁
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if(currentThread == currentThread()) {  //判断当前线程是否为获得了锁的那个线程，只有加了锁的线程才有资格解锁
                this.locked = false;  //locked状态修改为 false
                Optional.of(currentThread().getName() + " release the lock").ifPresent(System.out::println);
                this.notifyAll();  //通知其他在wait set中的线程，你们可以再次尝试抢锁了，这里使用notify和notifyAll都行
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
