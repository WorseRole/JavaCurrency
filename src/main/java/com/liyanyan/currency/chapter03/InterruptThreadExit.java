package com.liyanyan.currency.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/26 1:22 上午
 */
public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
//            @Override
//            public void run() {
//                System.out.println("I will start work");
//                while(!isInterrupted()) {
//                    //working
//                }
//                System.out.println("I will be exiting.");
//            }

            @Override
            public void run() {
                System.out.println("i will start work");
                for(; ; ) {
                    //working
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("i will be exiting.");
            }
        };
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("System will be shutdown");
        t.interrupt();
    }
}
