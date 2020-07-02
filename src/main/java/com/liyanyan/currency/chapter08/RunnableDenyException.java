package com.liyanyan.currency.chapter08;

/**
 * Created by liyanyan on 2020/6/3 12:42 上午
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message) {
        super(message);
    }
}
