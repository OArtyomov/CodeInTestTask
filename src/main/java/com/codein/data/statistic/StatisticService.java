package com.codein.data.statistic;


import com.codein.data.model.DataKey;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class StatisticService {

    private final ConcurrentHashMap<Thread, LinkedList<Long>> elementsPerThread;

    public StatisticService() {
        elementsPerThread = new ConcurrentHashMap<>();
    }

    public void saveElement(DataKey dataKey) {
        Thread elementThread = dataKey.getThread();
        LinkedList<Long> timeForElements = elementsPerThread.computeIfAbsent(elementThread, k -> new LinkedList<>());
        timeForElements.add(dataKey.getMillisTime());
    }

    public void removeElement(DataKey dataKey) {
        Thread elementThread = dataKey.getThread();
        LinkedList<Long> timeForElements = elementsPerThread.get(elementThread);
        if (timeForElements != null) {
            //We always remove only first element in list, so, it will be cheap operation
            timeForElements.remove(0);
            if (timeForElements.size() == 0) {
                elementsPerThread.remove(elementThread);
            }
        }
    }

    public  List<StatisticThreadData> getStatisticPerThread() {
        return buildData(elementsPerThread);
    }

    private List<StatisticThreadData> buildData(ConcurrentHashMap<Thread, LinkedList<Long>> elementsPerThread) {
        if (elementsPerThread != null && elementsPerThread.size() > 0) {
            return elementsPerThread.entrySet().stream().map(this::convertToData).collect(toList());
        }
        return emptyList();
    }

    private StatisticThreadData convertToData(Map.Entry<Thread, LinkedList<Long>> entry) {
        LinkedList<Long> timeOfElements = entry.getValue();
        int elementsSize = timeOfElements.size();
        long timeOfFirst = 0;
        long timeOfLast = 0;
        if (elementsSize > 0) {
            timeOfFirst = timeOfElements.getFirst();
            timeOfLast = timeOfElements.getLast();
        }
        return new StatisticThreadData(entry.getKey().getName(), elementsSize,
                timeOfFirst, timeOfLast, timeOfElements);
    }
}
