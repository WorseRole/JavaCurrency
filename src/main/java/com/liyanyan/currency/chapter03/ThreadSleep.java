package com.liyanyan.currency.chapter03;

/**
 * Created by liyanyan on 2020/5/24 2:53 下午
 * * *  sleep方法不会放弃monitor锁的所有权  * * *
 */
public class ThreadSleep {
    public static void main(String[] args) {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
            long endTome = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms", (endTome - startTime)));
        }).start();

        long startTime = System.currentTimeMillis();
        sleep(3_000L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main thread total spend %d ms", (endTime - startTime)));
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
