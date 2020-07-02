package com.liyanyan.currency.chapter05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by liyanyan on 2020/5/30 1:48 上午
 */
public interface Lock {
    void lock() throws InterruptedException;
    void lock(long mills) throws InterruptedException, TimeoutException;
    void unlock();
    List<Thread> getBlockedThreads();
}
