package com.liyanyan.currency.chapter02;

/**
 * Created by liyanyan on 2020/5/21 11:00 下午
 */
public class ThreadConstruction {
    public static void main(String[] args) {
        //创建线程 t1
        Thread t1 = new Thread("t1");
        //创建一个 ThreadGroup
        ThreadGroup threadGroup = new ThreadGroup("TestGroup");
        //创建一个线程t2 并把它放进 threadGroup中
        Thread t2 = new Thread(threadGroup, "t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();

        System.out.println("main thread belong group: " + mainThreadGroup.getName());

        System.out.println("t1 and main: " + (mainThreadGroup == t1.getThreadGroup()));

        System.out.println("t2 and main: " + (mainThreadGroup == t2.getThreadGroup()));

        System.out.println("t2 and TestGroup: " + (threadGroup == t2.getThreadGroup()));

        System.out.println(t2.getThreadGroup()+"---=-=-=-=-==");


    }
}
