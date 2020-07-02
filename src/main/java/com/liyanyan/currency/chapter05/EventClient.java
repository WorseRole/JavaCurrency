package com.liyanyan.currency.chapter05;

/**
 * Created by liyanyan on 2020/5/28 12:31 上午
 * 生产者消费者  队列来消息了，唤醒阻塞的消费者，让消费者去获取CPU的时间片
 * 但是这只是支持一个线程的生产和一个线程的消费。也就是但线程通信。多线程的话，需要将notify换为notifyAll
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for( ; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();
        new Thread(() -> {
            for( ; ; ) {
                eventQueue.take();
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }, "Consumer").start();

    }
}
