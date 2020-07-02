package com.liyanyan.currency.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/1 1:55 上午
 */
public class ThreadGroupPriority {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("group1");
        Thread thread = new Thread(group, ()-> {
            while(true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");

        thread.setDaemon(true);
        thread.start();

        System.out.println("group.getMaxPriority()= " + group.getMaxPriority());

        System.out.println("thread.getPriority()= " + thread.getPriority());
        //change ThreadGroup priority
        group.setMaxPriority(3);

        System.out.println("group.getMaxPriority()= " + group.getMaxPriority());
        System.out.println("thread.getPriority()= " + thread.getPriority());


    }
}
