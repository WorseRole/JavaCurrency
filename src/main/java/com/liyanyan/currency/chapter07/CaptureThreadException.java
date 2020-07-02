package com.liyanyan.currency.chapter07;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/1 11:52 下午
 */
public class CaptureThreadException {
    public static void main(String[] args) {
        //设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e)-> {
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这里会出现 unchecked异常
            System.out.println(1/0);
        }, "Test-Thread");
        thread.start();
    }
}
