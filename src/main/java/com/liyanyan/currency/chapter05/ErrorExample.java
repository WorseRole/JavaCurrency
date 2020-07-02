package com.liyanyan.currency.chapter05;

/**
 * Created by liyanyan on 2020/5/28 1:07 上午
 */
public class ErrorExample {
    //必须在同步方法中使用wait方法和notify方法,执行方法的条件就是必须持有同步方法的monitor的所有权
    private void testWait() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void testNotify() {
        this.notify();
    }

    //同步代码的monitor必须与执行wait notify 方法的对象一致，
    // 简单说就是用那个对象的monitor进行同步，就只能用哪个对象进行wait和notify操作
    private final Object MUTEX = new Object();
    private synchronized void testWait1() {
        try {
            MUTEX.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private synchronized void testNotify1() {
        MUTEX.notify();
    }

    public static void main(String[] args) {
        ErrorExample error = new ErrorExample();
        //全部报错
        error.testWait();
        error.testNotify();

        error.testWait1();
        error.testNotify1();

    }
}
