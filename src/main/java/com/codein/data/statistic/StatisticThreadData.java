package com.codein.data.statistic;


public class StatisticThreadData {

    private final String threadName;

    private final Integer count;

    private final long nanoTimeOfFirstElement;

    private final long nanoTimeOfLastElement;

    public StatisticThreadData(String threadName, Integer count, long nanoTimeOfFirstElement, long nanoTimeOfLastElement) {
        this.threadName = threadName;
        this.count = count;
        this.nanoTimeOfFirstElement = nanoTimeOfFirstElement;
        this.nanoTimeOfLastElement = nanoTimeOfLastElement;
    }

    public String getThreadName() {
        return threadName;
    }

    public Integer getCount() {
        return count;
    }

    public long getNanoTimeOfFirstElement() {
        return nanoTimeOfFirstElement;
    }

    public long getNanoTimeOfLastElement() {
        return nanoTimeOfLastElement;
    }
}
