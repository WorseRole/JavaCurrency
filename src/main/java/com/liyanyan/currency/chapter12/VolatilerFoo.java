package com.liyanyan.currency.chapter12;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/6/8 1:59 上午
 */
public class VolatilerFoo {
    //init_value 的最大值
    final static int MAX = 5;
    //init_value 的初始值
    //static int init_value = 0;
    volatile static int init_value = 0;

    public static void main(String[] args) {
        //启动一个Reader 线程，当发现local_value和 init_value 不同时，则输出init_value被修改的信息
        new Thread(()-> {
            int localValue = init_value;
            while(localValue<MAX) {
                if(init_value != localValue) {
                    System.out.printf("The init_value is updated to [%d]\n", init_value);
                    //对localValue 进行重新赋值
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        //启动Updater线程，主要用于对init_value 的修改，当local_value>=5 的时候则退出生命周期
        new Thread(()-> {
            int localValue = init_value;
            while (localValue<MAX) {
                //修改init_value
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
