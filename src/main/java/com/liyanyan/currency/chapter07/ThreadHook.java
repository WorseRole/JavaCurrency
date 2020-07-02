package com.liyanyan.currency.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/2 12:13 上午
 *
 * JVM进程即将退出时，两个Hook线程会被启动并且运行
 */
public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("the hook thread 1 is running.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thread 1 will exit.");
            }
        });

        //钩子线程可注册多个
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("the hook thread 2 is running.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thread 2 will exit.");
            }
        });
        System.out.println("the program will is stopping.");

    }
}
