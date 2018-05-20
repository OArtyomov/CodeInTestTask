package com.codein;

import com.codein.data.DataService;
import com.codein.data.statistic.StatisticData;
import com.codein.internal.DataFiller;

import java.util.ArrayList;
import java.util.List;

public class PoolManager {

    private final Integer threadCount;

    private DataService dataService;

    private List<DataFiller> threads;

    public PoolManager(Integer threadCount, Integer maxElementsCount) {
        this.threadCount = threadCount;
        threads = new ArrayList<>(threadCount);
        this.dataService = new DataService(maxElementsCount);
    }


    public void execute() {
        for (int x = 1; x <= threadCount; x++) {
            DataFiller thread = new DataFiller(dataService, "Index" + x);
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
