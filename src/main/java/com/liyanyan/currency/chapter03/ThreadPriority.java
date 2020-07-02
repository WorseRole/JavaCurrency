package com.liyanyan.currency.chapter03;

/**
 * Created by liyanyan on 2020/5/24 3:19 下午
 * 设置完优先级 很明显 t2 的打印频率要高很多
 */
public class ThreadPriority {
    //比较优先级
    /*
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while(true) {
                System.out.println("t1");
            }
        });
        t1.setPriority(3);

        Thread t2 = new Thread(() -> {
            while(true) {
                System.out.println("t2");
            }
        });
        t2.setPriority(10);

        t1.start();
        t2.start();
    }
     */

    //看一下setPriority()的源码，不能大于10，也不能小于1。 且不能大于所在的线程组，若大于则重新设置为线程组的优先级
    /*
    public static void main(String[] args) {
        //定义一个线程组
        ThreadGroup threadGroup = new ThreadGroup("test");
        threadGroup.setMaxPriority(7);
        Thread thread = new Thread(threadGroup, "test-thread");
        thread.setPriority(10);
        System.out.println(thread.getPriority());
    }
     */

    //默认优先级和它的父类就是派生它的线程的优先级保持一致
    public static void main(String[] args) {
        Thread t1 = new Thread();
        System.out.println("t1 priority " + t1.getPriority());
        Thread t2 = new Thread(() -> {
            Thread t3 = new Thread();
            System.out.println("t3 priority" + t3.getPriority());
        });
        t2.setPriority(6);
        t2.start();
        System.out.println("t2 priority " + t2.getPriority());
    }
}
