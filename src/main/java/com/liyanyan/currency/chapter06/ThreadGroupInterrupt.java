package com.liyanyan.currency.chapter06;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/1 1:59 上午
 */
public class ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("TestGroup");
        new Thread(group, ()-> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t1 will exit.");
        }, "t1").start();

        new Thread(group, ()-> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("t2 will exit.");
        }, "t2").start();

        TimeUnit.MILLISECONDS.sleep(2);

        group.interrupt();

    }
}
