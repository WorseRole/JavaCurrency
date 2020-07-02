package com.liyanyan.currency.charpter15;

/**
 * Created by liyanyan on 2020/7/1 11:44 下午
 */
@FunctionalInterface
public interface Task<T> {
    //任务执行接口，该接口允许有返回值
    T call();
}
