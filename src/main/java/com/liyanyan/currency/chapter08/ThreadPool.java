package com.liyanyan.currency.chapter08;

/**
 * Created by liyanyan on 2020/6/2 12:36 上午
 * utils包提供ExecutorService 线程池的实现。目的：重复利用线程，提高系统效率。
 * Thread是一个重量级的资源，创建，启动以及销毁都是比较耗资源的，因此对线程的重复利用是一种非常好的程序设计习惯，
 *      加之系统中可创建的线程数量是有限的，线程数量和系统性能是一种抛物线的关系，也就是说当线程数量达到某个数值的时候，
 *      性能反倒会降低很多，因此对线程的管理，尤其是对数量的控制直接决定程序的性能。
 * 完整的线程池具备要素：
 *      任务队列、线程数量管理功能(init/max/core)、任务拒绝策略、线程工厂、QueueSize、Keepedalive时间
 */
public interface ThreadPool {
    //提交任务带线程池
    void execute(Runnable runnable);
    //关闭线程
    void shutdown();
    //获取线程池的初始化大小
    int getInitSize();
    //获取线程池最大的线程数
    int getMaxSize();
    //获取线程池的核心数量
    int getCoreQueue();
    //获取线程池中用于缓存任务队列的大小
    int getQueueSize();
    //获取线程池中活跃的数量
    int getActiveCount();
    //获取线程池是否被已经被shutdown
    boolean isShutdown();

}
