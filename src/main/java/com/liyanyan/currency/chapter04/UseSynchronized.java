package com.liyanyan.currency.chapter04;

/**
 * Created by liyanyan on 2020/5/27 12:33 上午
 */
public class UseSynchronized implements Runnable {

    //1.错误示范
    //与monitor关联的对象不能为空 每一个对象和一个monitor关联，对象都为null了，monitor就无从谈起了
    private final Object mutex = null;
    public void syncMethod() {
        synchronized (mutex) {
            //
        }
    }

    //2.synchronized作用域太大
    /*
        synchronized应该尽可能的之作用于共享资源(数据)的读写作用域
        @Override
        public synchronized void run() {
            //
        }
     */

    //3.不同的monitor企图锁相同的方法
    private final Object MUTEX = new Object();
    @Override
    public void run() {
        synchronized (MUTEX) {
            //...
        }
        //...
    }
    public static void main(String[] args) {
        for(int i=0; i<5; i++) {
            new Thread(UseSynchronized::new).start();
        }
    }

    //4.多个锁的交叉导致死锁
    //多个锁交叉很容易引起线程出现死锁的情况
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_RIGHT = new Object();
    public void read() {
        synchronized (MUTEX_READ) {
            synchronized (MUTEX_RIGHT) {
                //...
            }
        }
    }
    public void right() {
        synchronized (MUTEX_RIGHT) {
            synchronized (MUTEX_RIGHT) {
                //...
            }
        }
    }

}
