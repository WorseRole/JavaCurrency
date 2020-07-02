package com.liyanyan.currency.chapter14;

/**
 * Created by liyanyan on 2020/6/8 2:23 上午
 */
public enum SingletonEnum {
    INSTANCE;
    //实例变量
    private byte[] data = new byte[1024];
    SingletonEnum() {
        System.out.println("INSTANCE will be initialized immediately");
    }
    public static void method() {
        //调用该方法时则会主动使用Singleton，INSTANCE 将会被实例化
    }
    public static SingletonEnum getInstance() {
        return INSTANCE;
    }

}
