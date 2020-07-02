package com.liyanyan.currency.chapter09;

/**
 * Created by liyanyan on 2020/6/4 12:37 上午
 */
public class Simple {
    static {
        System.out.println("i will be initialized.");
    }

    public static int x = 10;

    public static void test() {

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("liyanyan.concurrent.chapter09.Simple");
    }

}
