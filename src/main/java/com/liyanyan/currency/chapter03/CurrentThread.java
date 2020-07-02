package com.liyanyan.currency.chapter03;

/**
 * Created by liyanyan on 2020/5/24 3:42 下午
 */
public class CurrentThread {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                //一直 true
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();

        String name = Thread.currentThread().getName();
        System.out.println("main".equals(name));
    }
}
