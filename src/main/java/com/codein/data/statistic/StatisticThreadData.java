package com.codein.data.statistic;


public class StatisticThreadData {

    private final String threadName;

    private final Integer count;

    public StatisticThreadData(String threadName, Integer count) {
        this.threadName = threadName;
        this.count = count;
    }

    public String getThreadName() {
        return threadName;
    }

    public Integer getCount() {
        return count;
    }
}
