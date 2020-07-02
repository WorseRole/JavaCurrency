package com.liyanyan.currency.chapter08;

/**
 * Created by liyanyan on 2020/6/3 12:34 上午
 * 线程创建工厂  函数式接口
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
