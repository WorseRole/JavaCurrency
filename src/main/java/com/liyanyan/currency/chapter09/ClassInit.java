package com.liyanyan.currency.chapter09;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/4 12:53 上午
 */
public class ClassInit {
    static {
        cc = 16000;
    }
    private static int cc = 15000;

    static {
        try {
            System.out.println("the classInit static code block will be invoke.");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //父类中有静态变量 value
    //静态语句块只能对后面的静态变量进行赋值，但是不能对其访问
    static class Parent {
        static int value = 10;
        static {
            value = 20;
        }

    }
    //子类使用父类的静态变量为自己的静态变量赋值
    static class Child extends Parent {
        static int i = value;
    }

    public static void main(String[] args) {
        System.out.println(Child.i);

        //在同一时间，只能有一个线程执行到静态代码块中的内容，并且静态代码块仅仅只会被执行一次
        // JVM保证类<clinit>()方法在多线程的执行环境下的同步语义，
        // 因此在单例设计模式下，采用Holder的方式是一种最佳的设计方案
//        IntStream.range(0, 5).forEach(i -> new Thread(ClassInit::new));
        HashMap hashMap = new HashMap(16);

    }
}
