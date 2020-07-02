package com.liyanyan.currency.chapter01;

/**
 * Created by liyanyan on 2020/5/21 10:13 下午
 */
public class TicketWindow extends Thread {

    //柜台名称
    private final String name;
    //最多受理50笔业务
    private static final int MAX = 50;

    //private int index = 1;
    //将index 设 static 为共享的，每个线程才能实时的共享变量
    /**
     * 但是如果共享资源有很多呢？
     * 且static修饰的变量生命周期很长，所以我们就需要实现Runnable接口来解决这个问题
     * 将线程的控制和业务逻辑的运行彻底分离开来
     */
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(index<=MAX) {
            System.out.println("柜台:" + name + " 当前号码是: " + (index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();
    }
}
