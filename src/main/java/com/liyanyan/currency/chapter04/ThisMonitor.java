package com.liyanyan.currency.chapter04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * Created by liyanyan on 2020/5/27 12:44 上午
 * 最终发现其实只有一个方法在被调用，可以用jstack工具进行查看，t1为time_wating t2为block
 *      可见使用sychronized 关键字同步类的不同实例方法，
 *      争抢的是同一个monitor的lock，而与之关联的引用则是ThisMonitor的实例引用
 */
public class ThisMonitor {
    public synchronized void method1() {
        System.out.println(currentThread().getName() + " enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void method2() {
        System.out.println(currentThread().getName() + "enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //与method2其实表达的意思是一样的
    public void method_2() {
        synchronized (this) {
            System.out.println(currentThread().getName() + "enter to method_2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThisMonitor thisMonitor = new ThisMonitor();
        new Thread(thisMonitor::method1, "T1").start();
        new Thread(thisMonitor::method2, "T2").start();
    }
}
