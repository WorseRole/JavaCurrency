package com.liyanyan.currency.charpter15;

/**
 * Created by liyanyan on 2020/7/1 11:48 下午
 */
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifeCycle;
    private final Task<T> task;
    private Cycle cycle;

    //指定Task 的实现，默认路
    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }
    //指定TaskLifecycle 的同时指定task
    public ObservableThread(TaskLifecycle<T> lifeCycle, Task<T> task) {
        super();
        //Task 不允许为null
        if(task == null)
            throw new IllegalArgumentException("The task is required.");
        this.lifeCycle = lifeCycle;
        this.task=task;
    }

    @Override
    public void run() {
        //在执行线程逻辑单元的时候，分别触发相应的时间
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        }catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception ex) {
        this.cycle = cycle;
        if(lifeCycle == null)
            return;
        try {
            switch (cycle) {
                case STARTED:
                    this.lifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifeCycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifeCycle.onError(currentThread(), ex);
                    break;
            }
        }catch (Exception e) {
            if(cycle == Cycle.ERROR) {
                throw e;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return null;
    }
}
