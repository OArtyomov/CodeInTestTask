package com.codein.data.statistic;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class StatisticService {

    private final ConcurrentHashMap<Thread, Integer> statisticData;

    public StatisticService() {
        statisticData = new ConcurrentHashMap<>();
    }

    public void incCountPerThread(Thread currentThread) {
        Integer value = statisticData.get(currentThread);
        value = value == null ? 1 : value + 1;
        statisticData.put(currentThread, value);
    }

    public void decCountPerThread(Thread currentThread) {
        Integer value = statisticData.get(currentThread);
        value = value == null ? 0 : value - 1;
        if (value == 0) {
            statisticData.remove(currentThread);
        } else {
            statisticData.put(currentThread, value);
        }
    }

    public List<StatisticThreadData> getStatisticPerThread() {
        return buildData(statisticData);
    }


    private List<StatisticThreadData> buildData(ConcurrentHashMap<Thread, Integer> concurrentHashMap) {
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            return concurrentHashMap.entrySet().stream().map(this::convertToData).collect(toList());
        }
        return emptyList();
    }

    private StatisticThreadData convertToData(Map.Entry<Thread, Integer> entry) {
        return new StatisticThreadData(entry.getKey().getName(), entry.getValue());
    }

}
