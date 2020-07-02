package com.liyanyan.currency.chapter09;

/**
 * Created by liyanyan on 2020/6/4 12:50 上午
 */
public class Singleton {
    //
    private static int x = 0;
    private static int y;

    private static Singleton instants = new Singleton();

    private Singleton() {
        x++;
        y++;
    }
    public static Singleton getInstance() {
        return instants;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
