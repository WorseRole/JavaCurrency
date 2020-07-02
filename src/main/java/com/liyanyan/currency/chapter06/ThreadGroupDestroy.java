package com.liyanyan.currency.chapter06;

/**
 * Created by liyanyan on 2020/6/1 2:03 上午
 */
public class ThreadGroupDestroy {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("TestGroup");

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("group.isDestroyed= " + group.isDestroyed());
        mainGroup.list();

        group.destroy();

        System.out.println("group.isDestroy=" + group.isDestroyed());
        mainGroup.list();

    }
}
