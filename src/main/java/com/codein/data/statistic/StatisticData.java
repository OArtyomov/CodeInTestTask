package com.codein.data.statistic;


import java.util.List;

public class StatisticData {

    private final int maxCountInQueue;

    private final List<StatisticThreadData> statisticPerThread;

    public int getMaxCountInQueue() {
        return maxCountInQueue;
    }

    public List<StatisticThreadData> getStatisticPerThread() {
        return statisticPerThread;
    }

    public StatisticData(int maxCountInQueue, List<StatisticThreadData> statisticPerThread) {
        this.maxCountInQueue = maxCountInQueue;
        this.statisticPerThread = statisticPerThread;
    }
}
