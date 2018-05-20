package com.codein.data.model;

public class DataKey {

    private final Long millisTime;

    private final Thread thread;

    public DataKey(Long timeInMilliseconds, Thread thread) {
        this.millisTime = timeInMilliseconds;
        this.thread = thread;
    }

    public Long getMillisTime() {
        return millisTime;
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public String toString() {
        return "DataKey{" +
                "time=" + millisTime +
                ", thread=" + thread +
                '}';
    }
}
