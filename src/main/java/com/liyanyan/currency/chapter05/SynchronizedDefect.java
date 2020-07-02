package com.liyanyan.currency.chapter05;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/30 1:35 上午
 *  1.无法控制阻塞时长
 *  2.阻塞不可被中断
 */
public class SynchronizedDefect {

    public synchronized void syncMethod() {
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod, "T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2); //为了确保T1先执行，特意让主线程睡两秒
        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        System.out.println(t2.isInterrupted());  //true
        System.out.println(t2.getState());  //BLOCKED
        //这时t2 因为正庆争抢monitor锁进入阻塞状态，他是无法中断的，虽然可以设置T2线程的interrupt标识，
        // 但synchronized不会像sleep和wait方法一样会捕捉中断信号
    }

}
