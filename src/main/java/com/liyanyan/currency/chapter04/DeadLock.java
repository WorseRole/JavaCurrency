package com.liyanyan.currency.chapter04;

import static java.lang.Thread.currentThread;

/**
 * Created by liyanyan on 2020/5/27 1:01 上午
 */
public class DeadLock {
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();
    public void read() {
        synchronized (MUTEX_READ) {
            System.out.println(currentThread().getName() + " get READ lock");
            synchronized (MUTEX_WRITE) {
                 System.out.println(currentThread().getName() + " get WRITE lock");
            }
            System.out.println(currentThread().getName() + " release WRITE lock");
        }
        System.out.println(currentThread().getName() + " release READ lock");
    }
    public void write() {
        synchronized (MUTEX_WRITE) {
            System.out.println(currentThread().getName() + " get WRITE lock");
            synchronized (MUTEX_READ) {
                System.out.println(currentThread().getName() + " get READ lock");
            }
            System.out.println(currentThread().getName() + " release READ lock");
        }
        System.out.println(currentThread().getName() + " release WRITE lock");
    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        new Thread(() -> {
            while(true) {
                deadLock.read();
            }
        }, "READ-THREAD").start();

        new Thread(() -> {
            while(true) {
                deadLock.write();
            }
        }, "WRITE-THREAD").start();

    }
}
