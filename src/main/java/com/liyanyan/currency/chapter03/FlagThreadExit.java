package com.liyanyan.currency.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/26 1:28 上午
 * 使用Volatile开关控制
 */
public class FlagThreadExit {
    static class MyTask extends Thread {
        //由于线程的interrupt标识很有可能被擦除，或者逻辑单元中不会调用任何可中断方法，
        // 所以使用volatile 修饰的开关flag 关闭线程也是一种常用的做法。
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("i will start work");
            while (!closed && !isInterrupted()) {
                //正在运行
            }
            System.out.println("i will be exiting.");
        }
        public void close() {
            this.closed = true;
            this.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask t = new MyTask();
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("System will be shutdown.");
        t.close();
    }
}
