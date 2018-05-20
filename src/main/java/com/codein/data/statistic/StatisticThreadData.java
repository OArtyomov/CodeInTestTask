package com.codein.data.statistic;


import java.util.LinkedList;

public class StatisticThreadData {

    private final String threadName;

    private final Integer count;

    private final long nanoTimeOfFirstElement;

    private final long nanoTimeOfLastElement;

    private final LinkedList<Long> timeOfElements;

    public LinkedList<Long> getTimeOfElements() {
        return timeOfElements;
    }

    StatisticThreadData(String threadName, Integer count, long nanoTimeOfFirstElement, long nanoTimeOfLastElement, LinkedList<Long> timeOfElements) {
        this.threadName = threadName;
        this.count = count;
        this.nanoTimeOfFirstElement = nanoTimeOfFirstElement;
        this.nanoTimeOfLastElement = nanoTimeOfLastElement;
        //Copy of time is must be here
        this.timeOfElements = new LinkedList<>(timeOfElements);
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
