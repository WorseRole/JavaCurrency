package com.liyanyan.currency.chapter11;

import static java.lang.Thread.currentThread;

/**
 * Created by liyanyan on 2020/6/8 1:57 上午
 */
public class MainThreadClassLoader {
    public static void main(String[] args) {
        System.out.println(currentThread().getContextClassLoader());
    }
}
