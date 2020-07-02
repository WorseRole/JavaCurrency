package com.liyanyan.currency.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {
                enjoyMusic();
            }
        }.start();
        //    new Thread(TryConcurrency::enjoyMusic).start(); //lambda表达式
        browseNews();
    }

    //看新闻
    private static void browseNews() {
        for (; ; ) {
            System.out.println("嗯，好新闻");
            sleep(1);
        }
    }

    //听歌
    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("嗯，好歌");
            sleep(1);
        }
    }

    //自定义sleep
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
