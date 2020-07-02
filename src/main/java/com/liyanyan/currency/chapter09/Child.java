package com.liyanyan.currency.chapter09;

/**
 * Created by liyanyan on 2020/6/4 12:43 上午
 */
public class Child extends Parent {
    static {
        System.out.println("the child will be initialized.");
    }
    public static int x = 10;
}
