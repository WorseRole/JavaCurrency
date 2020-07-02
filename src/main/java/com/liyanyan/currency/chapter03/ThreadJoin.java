package com.liyanyan.currency.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by liyanyan on 2020/5/26 12:39 上午
 * join某个线程A,会使线程B进入等待，知道线程A结束生命线程，或者到达给定的时间
 * 那么在此期间B线程是处于BLOCKED的，而不是A线程
 *
 *  下面代码 创建了两个线程，分别启动，并且调用了每个线程的join方法（这里是main线程进行调用的）
 *    因此在第一个线程还没有结束生命周期的时候，第二个线程是不会得到执行的，但是此时，第二个线程也已经启动了
 *  只有main线程需要等待，最后执行
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        //定义两个线程，并保存在threads中
        List<Thread> threadList = IntStream.range(1, 3)
                .mapToObj(ThreadJoin::create).collect(Collectors.toList());
        //启动这两个线程
        threadList.forEach(Thread::start);
        //执行这两个线程的join方法   这块是main线程执行的这两个线程的join方法
        for(Thread thread: threadList) {
            thread.join();
        }
        //main线程循环输出
        for(int i=0; i<10; i++) {
            System.out.println(Thread.currentThread().getName() + "##" + i);
            shortSleep();
        }
    }

    public static Thread create(int seq) {
        return new Thread(() -> {
            for(int i=0; i<10; i++) {
                System.out.println(Thread.currentThread().getName() + "##" + i);
                shortSleep();
            }
        }, String.valueOf(seq) );
    }
    public static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
