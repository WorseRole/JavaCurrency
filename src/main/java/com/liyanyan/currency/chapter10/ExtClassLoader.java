package com.liyanyan.currency.chapter10;

/**
 * Created by liyanyan on 2020/6/5 12:17 上午
 * 扩展类加载器 他是JAVA_HOME下的jre\lib\ext子目录里面的类库
 */
public class ExtClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
    }
}
