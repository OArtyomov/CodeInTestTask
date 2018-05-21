package com.codein.internal;


import com.codein.data.DataService;

import java.util.concurrent.ThreadLocalRandom;

public class DataFiller extends Thread {

    private final DataService dataService;

    public DataFiller(DataService dataService, String threadName) {
        this.dataService = dataService;
        setName(threadName);
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            dataService.add(5, this);
            try {
                int i = ThreadLocalRandom.current().nextInt(1, 100);
                int timeToSleep = i * 20;
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
            }
        }

    }
}
