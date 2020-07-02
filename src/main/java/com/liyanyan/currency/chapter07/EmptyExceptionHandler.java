package com.liyanyan.currency.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/1 11:56 下午
 *
 * ThreadGroup中 uncaughtException(Thread t, Throwable e)
 * 该ThreadGroup 如果有父类ThreadGroup，则直接调用父类Group的uncaughtException方法
 * 如果设置了全局默认的UncaughtExceptionHandler，则调用uncaughtException方法
 * 若既没有父 ThreadGroup，也没有设置全局默认的UncaughtExceptionHandler，则会直接将异常的堆栈信息定向到System.err中
 *
 */
public class EmptyExceptionHandler {
    public static void main(String[] args) {
        // get current thread's group
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        final Thread thread = new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //here will throw unchecked exception
            System.out.println(1/0);
        }, "Test-Thread");
        thread.start();
    }
}
