package com.liyanyan.currency.chapter04;

import java.util.HashMap;

/**
 * Created by liyanyan on 2020/5/27 1:06 上午
 * 这个其实就是HashMap线程不安全可能会导致CPU100%
 *      这个代码可能会使电脑死机。。。
 */
public class HashMapDeadLock {
    private final HashMap<String, String>map = new HashMap<>();
    public void add(String key, String value) {
        this.map.put(key, value); //其实就是这个put()方法造成的，在扩容的时候有一个modCount++
    }

    public static void main(String[] args) {
        final HashMapDeadLock hashMapDeadLock = new HashMapDeadLock();
        for(int x=0; x<2; x++) {
            new Thread(() -> {
                for(int i=0; i<Integer.MAX_VALUE; i++) {
                    hashMapDeadLock.add(String.valueOf(i), String.valueOf(i));
                }
            }).start();
        }
    }
}
