package com.liyanyan.currency.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyanyan on 2020/5/26 12:56 上午
 */
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destination) {
        super("[" + airline + "]"); //thread name
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight: %s list query successful \n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
