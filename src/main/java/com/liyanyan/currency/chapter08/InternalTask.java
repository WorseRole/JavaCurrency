package com.liyanyan.currency.chapter08;

/**
 * Created by liyanyan on 2020/6/3 12:43 上午
 */
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;
    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //如果当前任务为running 并且没有被中断，则其将不断的从queue 中获取runnable，然后执行run 方法
        while(running && !Thread.currentThread().isInterrupted()) {
            try{
                Runnable task = runnableQueue.take();
                task.run();
            }catch (Exception ex) {
                running = false;
                break;
            }
        }
    }
    //停止当前任务，主要会在线程池的shutdown方法中使用
    public void stop() {
        this.running = false;
    }
}
