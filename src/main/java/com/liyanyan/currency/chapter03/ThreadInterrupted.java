package com.liyanyan.currency.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/24 4:30 下午
 * interrupted()方法是静态方法，用于判断线程是否被中断，但调用它时会直接擦除线程的interrupt标识
 *    需要注意的是，如果当前线程被打断，那么第一次调用interrupted方法会返回true，并且立即擦除了interrupt标识
 *    第二次包括以后调用永远都是false，除非在此期间线程又一次被打断。
 */
public class ThreadInterrupted {
    /*
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while(true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
    }
     */

    /**
     * 思考：1 和 3 中的判断线程方法不同
     * @param args
     */
    public static void main(String[] args) {
        // 1-判断当前线程是否被中断
        System.out.println("Main thread is interrupted? " + Thread.interrupted());
        // 2-中断当前线程
        Thread.currentThread().interrupt();
        // 3-判断当前线程是否已经被中断
        System.out.println("Main thread is interrupted ?" + Thread.currentThread().isInterrupted());
        try {
            // 4-当前线程执行可中断方法
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            // 5-捕捉中断信号
            System.out.println("i will be interrupted still.");
        }
    }
}
