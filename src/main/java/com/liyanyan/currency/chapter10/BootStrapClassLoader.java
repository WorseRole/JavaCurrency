package com.liyanyan.currency.chapter10;

/**
 * Created by liyanyan on 2020/6/5 12:13 上午
 * 根加载器是最为顶层的加载器，负责虚拟机核心类库的加载
 */
public class BootStrapClassLoader {
    public static void main(String[] args) {
        //String.class的类加载器是根加载器 根加载器是获取不到引用的，因此输出为null
        System.out.println("Bootstrap:" + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
