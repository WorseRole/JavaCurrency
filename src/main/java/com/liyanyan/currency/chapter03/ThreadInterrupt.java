package com.liyanyan.currency.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/24 4:02 下午
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("oh, i am be interrupted.");
            }
        });
        thread.start();
        //想睡1分钟，然后被在main线程中调用这个线程去调interrupt函数，中断阻塞
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
