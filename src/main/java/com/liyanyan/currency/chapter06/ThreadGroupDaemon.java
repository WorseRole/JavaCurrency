package com.liyanyan.currency.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/1 2:08 上午
 */
public class ThreadGroupDaemon {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("Group1");
        new Thread(group1, ()-> {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group1-thread1").start();

        ThreadGroup group2 = new ThreadGroup("Group2");
        new Thread(group2, ()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "group2-thread1").start();

        group2.setDaemon(true);

        TimeUnit.SECONDS.sleep(3);
        System.out.println(group1.isDestroyed());
        System.out.println(group2.isDestroyed());
    }
}
