package com.liyanyan.currency.charpter15;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/7/2 12:07 上午
 */
public class Main {
    public static void main(String[] args) {
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>() {
            public void onFinish(Thread thread, String result) {
                System.out.println("the result is "+result);
            }
        };
        Observable observableThread = new ObservableThread<>(lifecycle, ()-> {
            try {
                TimeUnit.SECONDS.sleep(10);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done!");
            return "hello Observer";
        });
        observableThread.start();

    }
}
