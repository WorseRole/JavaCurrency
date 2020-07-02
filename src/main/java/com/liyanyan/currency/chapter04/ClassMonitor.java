package com.liyanyan.currency.chapter04;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * Created by liyanyan on 2020/5/27 12:55 上午
 * 两个类方法(静态方法)分别使用synchronized对其进行同步：
 *      同ThisMonitor分析一样，与该monitor关联的引用是ClassMonitor.class实例
 */
public class ClassMonitor {

    public static synchronized void method1() {
        System.out.println(currentThread().getName() + "enter to method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static synchronized void method2() {
        System.out.println(currentThread().getName() + "enter to method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void method_2() {
        synchronized (ClassMonitor.class) {
            System.out.println(currentThread().getName() + "enter to method_2");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::method1).start();
        new Thread(ClassMonitor::method2).start();
    }

}
