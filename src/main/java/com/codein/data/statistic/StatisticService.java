package com.codein.data.statistic;


import com.codein.data.model.DataKey;

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

    public void saveElement(DataKey dataKey) {
        Thread elementThread = dataKey.getThread();
        Integer value = statisticData.get(elementThread);
        value = value == null ? 1 : value + 1;
        statisticData.put(elementThread, value);
    }

    public void removeElement(DataKey dataKey) {
        Thread elementThread = dataKey.getThread();
        Integer value = statisticData.get(elementThread);
        value = value == null ? 0 : value - 1;
        if (value == 0) {
            statisticData.remove(elementThread);
        } else {
            statisticData.put(elementThread, value);
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
