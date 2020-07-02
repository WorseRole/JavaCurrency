package com.liyanyan.currency.chapter14;

/**
 * Created by liyanyan on 2020/6/8 2:09 上午
 */
public final class Singleton {
    private byte[] data = new byte[1024];
    private Singleton(){}
    //饿汉
//    private static Singleton instance = new Singleton();
//    public static Singleton getInstance() {
//        return instance;
//    }

    //懒汉 线程不安全 所以需要在getInstance()方法加入同步控制，每次只能又一个线程能够进入
//    private static Singleton instance;
//    public static synchronized Singleton getInstance() {
//        if(instance==null) {
//            return new Singleton();
//        }
//        return instance;
//    }

    //volatile + double check  需要禁止指令重排序，用上 volatile
//    private volatile static Singleton instance;
//    public static Singleton getInstance() {
//        if(instance == null) {
//            synchronized (Singleton.class) {
//                if(instance == null) {
//                    return new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    //内部类
//    private static class Inner {
//        //在静态内部类中持有Singleton的实例，并且可被直接初始化
//        private static Singleton instance = new Singleton();
//    }
//        //调用getInstance方法，事实上就是获得Inner类的instance属性
//    public static Singleton getInstance() {
//        return Inner.instance;
//    }

    //枚举类  枚举不允许被继承，同样时线程安全的，只能被实例化一次，但是枚举不能懒加载
    //对Singleton主动使用，比如调用其中的静态方法则INSTANCE 会立即得到实例化
    private enum EnumSingleton {
        INSTANCE;
        private Singleton instance;
        EnumSingleton() {
            this.instance = new Singleton();
        }
        private Singleton getInstance() {
            return instance;
        }
    }
    public static Singleton getInstance() {
        return EnumSingleton.INSTANCE.getInstance();
    }


}
