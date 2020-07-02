package com.liyanyan.currency.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/24 4:09 下午
 */
public class ThreadisInterrupted {
    //isInterrupt()方法主要判断当前线程是否被中断
    /*
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    //空循环 //最后需要自己中断程序呀教训呀，要不然你的电脑该烫手了
                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n ", thread.isInterrupted());
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s\n ", thread.isInterrupted());
    }
     */

    //因为sleep()方法是可中断方法，它会捕捉到中断信号，并会擦除interrupt标识，因此都是false
    //可中断方法捕获到中断信号，为了不影响线程中其他方法的执行，将线程的interrupt标识复位是一种很合理的设计
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.printf("i am be interrupted ? %s\n", isInterrupted());
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("i am be interrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("i am be interrupted ? %s\n", thread.isInterrupted());
    }

}
