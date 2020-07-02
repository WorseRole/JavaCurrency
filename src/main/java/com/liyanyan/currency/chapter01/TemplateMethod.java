package com.liyanyan.currency.chapter01;

/**
 * Created by liyanyan on 2020/5/21 10:04 下午
 *
 * run()和start()是比较典型的模版设计模式，父类编写算法结构代码，子类实现逻辑细节
 * 以下程序，程序由父类进行控制，并且是final修饰的，不允许被重写，子类只需要实现想要的逻辑即可。
 */
public class TemplateMethod {

    public final void print(String message) {
        System.out.println("######");
        wrapPrint(message);
        System.out.println("######");
    }

    protected void wrapPrint(String message) {}

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        t1.print("hello world");
    }

}
