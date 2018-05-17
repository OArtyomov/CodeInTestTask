package com.codein;

import com.codein.data.DataService;
import com.codein.data.statistic.StatisticData;
import com.codein.internal.DataFillerThread;

import java.util.ArrayList;
import java.util.List;

public class DataInitService {

    private final Integer threadCount;

    private DataService dataService;

    private List<DataFillerThread> threads;

    public DataInitService(Integer threadCount, Integer maxElementsCount) {
        this.threadCount = threadCount;
        threads = new ArrayList<>(threadCount);
        this.dataService = new DataService(maxElementsCount);
    }


    public void execute() {
        for (int x = 1; x <= threadCount; x++) {
            int timeToWait = x * 100;
            DataFillerThread thread = new DataFillerThread(dataService, timeToWait, "Index" + x);
            thread.start();
            threads.add(thread);
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    public StatisticData getStatistic() {
        return dataService.getStatistic();
    }

}
