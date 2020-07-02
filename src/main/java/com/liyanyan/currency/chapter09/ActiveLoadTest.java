package com.liyanyan.currency.chapter09;

/**
 * Created by liyanyan on 2020/6/4 12:43 上午
 */
public class ActiveLoadTest {
    public static void main(String[] args) {
        System.out.println(Child.y); //用子类调用父类的静态变量只会导致父类的初始化，子类则不会被初始化
//        System.out.println(Child.x);
    }
}
