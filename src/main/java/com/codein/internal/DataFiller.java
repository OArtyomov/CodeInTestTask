package com.codein.internal;


import com.codein.data.DataService;

public class DataFiller extends Thread {

    private final DataService dataService;

    private final int timeToWait;


    public DataFiller(DataService dataService, int timeToWait, String threadName) {
        this.dataService = dataService;
        this.timeToWait = timeToWait;
        setName(threadName);
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            dataService.add(5, this);
            try {
                Thread.sleep(timeToWait);
            } catch (InterruptedException e) {
            }
        }

    }
}
