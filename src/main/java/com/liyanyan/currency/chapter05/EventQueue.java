package com.liyanyan.currency.chapter05;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

/**
 * Created by liyanyan on 2020/5/28 12:17 上午
 * wait 和 sleep
 *  相同点：1都可以使线程进入阻塞 2都是可中断方法
 *  不同点：1sleep是Object方法，sleep是Thread特有的方法 2线程在同步方法中执行sleep时，并不会释放monitor的锁，而wait释放
 *  3sleep短暂休眠后会主动退出阻塞，而wait没有指定时间时则需要唤醒或其他线程中断后才能退出阻塞
 */
public class EventQueue {

    /*
    说一下wait() 和 notify()
        * 很重要的一点：wait方法必须拥有该对象的monitor，也就是说wait方法必须在同步方法中使用
        * wait()会导致当前线程进入阻塞状态，直到其他线程调用了Object的notify或者notifyAll方法才能将其唤醒，
        或阻塞时间到了自动唤醒
        * 当前线程执行该对象的wait方法后，会放弃该monitor的所有权，进入该对象关联的wait set中，
        也就是一旦线程执行了某个object的wait方法后，他会释放该对象的monitor所有权，其他线程将会继续争抢monitor所有权
        * wait方法是可中断的，可以被唤醒，或者其他线程也可以使用interrupt方法将其打断。
        之前说过，可中断方法被打断后会捕捉到中断信号后(就是处理异常)，同时interrupt标识会被擦除
        * 必须在同步方法中使用wait方法和notify方法,执行方法的条件就是必须持有同步方法的monitor的所有权
        * 同步代码的monitor必须与执行wait notify 方法的对象一致，简单说就是用那个对象的monitor进行同步，就只能用哪个对象进行wait和notify操作
     */
    /*
    而notify方法为唤醒：
        * 唤醒单个正在执行该对象wait方法的线程
        * 如果有某个线程由于执行对象的wait方法而进入阻塞则会被唤醒，如果没有则会忽略
        * 被唤醒的线程需要重新获取对象所关联的monitor的lock才能继续执行
     */
    private final int max;

    static class Event{

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }
    public EventQueue(int max) {
        this.max = max;
    }

    /**
     * 将临界值的判断 if 更改为 while，将notify 更改为 notifyAll 即可实现多线程通信
     * @param event
     */
    public void offer(Event event) {
        synchronized (eventQueue) {
            if(eventQueue.size() >= max) {
                try {
                    console(" the queue is full.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted.");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }
    public Event take() {
        synchronized (eventQueue) {
            if(eventQueue.isEmpty()) {
                try {
                    console(" the queue is empty.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console(" the event " + event + " is handle.");
            return event;
        }
    }

    private void console(String message) {
//        System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }

}
