package com.liyanyan.currency.chapter03;

import java.util.stream.IntStream;

/**
 * Created by liyanyan on 2020/5/24 3:01 下午
 */
public class ThreadYield {
    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYield::create).forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
//            if(index == 0) {
//                Thread.yield();
//            }
            System.out.println(index);
        });
    }
}
