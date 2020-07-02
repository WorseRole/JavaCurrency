package com.liyanyan.currency.chapter02;

/**
 * Created by liyanyan on 2020/5/22 12:07 上午
 * 守护线程具备自动结束生命线程的特性
 * 守护线程经常用作执行一些后台任务，因此也被成为后台线程
 * 当你希望关闭某些线程或者推出jvm时，一些线程可以自动关闭。
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        //main 线程开始
        Thread thread = new Thread(()-> {
            while(true){
                try{
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //将thread 线程设置为守护线程  一定要在线程启动之前进行设置，否则报 IllegalThreadStateException
        thread.setDaemon(true); //^-^
        thread.start();
        Thread.sleep(2000);
        System.out.println("Main thread finished lifecycle.");
    }
}
