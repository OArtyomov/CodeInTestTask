package com.codein.data.model;

public class DataKey {
    private final Long time;

    private final Thread thread;

    public Long getTime() {
        return time;
    }

    public Thread getThread() {
        return thread;
    }

    public DataKey(Long time, Thread thread) {
        this.time = time;
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "DataKey{" +
                "time=" + time +
                ", thread=" + thread +
                '}';
    }
}
