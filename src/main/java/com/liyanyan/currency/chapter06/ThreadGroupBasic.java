package com.liyanyan.currency.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/1 1:46 上午
 */
public class ThreadGroupBasic {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group1");
        Thread thread = new Thread(group, ()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println("activeCount=" + mainGroup.activeCount());
        System.out.println("activeGroupCount=" + mainGroup.activeGroupCount());
        System.out.println("getMaxPriority=" + mainGroup.getMaxPriority());
        System.out.println("getName=" + mainGroup.getName());
        mainGroup.list();
        System.out.println("--------------------------");
        System.out.println("parentOf=" + mainGroup.parentOf(group));
        System.out.println("parentOf=" + mainGroup.parentOf(mainGroup));

    }
}
