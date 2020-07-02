package com.liyanyan.currency.charpter15;

/**
 * Created by liyanyan on 2020/7/1 11:35 下午
 */
public interface TaskLifecycle<T> {
    //任务启动时会触发 onStart 方法
    void onStart(Thread thread);
    //任务正在运行时会触发 onRunning 方法
    void onRunning(Thread thread);
    //任务运行结束时会触发叫做 onFini️sh
    void onFinish(Thread thread, T result);
    //任务执行报错会触发 onError 方法
    void onError(Thread thread, Exception e);

    //生命周期接口的实现(Adapter)
    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {
            System.out.println("onstart");
        }

        @Override
        public void onRunning(Thread thread) {
            System.out.println("onRunning");
        }

        @Override
        public void onFinish(Thread thread, T result) {
            System.out.println("onFinish");
        }

        @Override
        public void onError(Thread thread, Exception e) {
            System.out.println("onError");
        }
    }
}
