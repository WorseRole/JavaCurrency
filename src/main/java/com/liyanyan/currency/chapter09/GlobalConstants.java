package com.liyanyan.currency.chapter09;

import java.util.Random;

/**
 * Created by liyanyan on 2020/6/4 12:46 上午
 */
public class GlobalConstants {
    static {
        System.out.println("the GlobalConstants will be initialized.");
    }
    //在其他类中使用MAX 不会导致GlobalConstants 的初始化，静态代码块不会输出
    public final static int MAX = 100;
    //虽然RANDOM 是静态常量，但是由于计算复杂，只有初始化之后才能得到结果，
    // 因此在其他类中使用RANDOM 会导致GlobalConstants 的初始化
    public final static int RANDOM = new Random().nextInt();
}
