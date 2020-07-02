package com.liyanyan.currency.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/27 12:15 上午
 */
public class Mutex {
    private final static Object MUTEX = new Object();

    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        for(int i=0; i<5; i++) {
            new Thread(mutex::accessResource).start();
        }
    }
}
