package com.codein.data;

import com.codein.data.model.DataKey;
import com.codein.data.model.DataKeyComparator;
import com.codein.data.statistic.StatisticData;
import com.codein.data.statistic.StatisticService;

import java.util.concurrent.ConcurrentSkipListMap;

public class DataService {

    private final ConcurrentSkipListMap<DataKey, Integer> itemsMap;

    private final StatisticService statisticService;

    private final int maxElementsCount;

    private int maxSizeInQueue;


    public DataService(int maxElementsCount) {
        this.maxElementsCount = maxElementsCount;
        this.itemsMap = new ConcurrentSkipListMap<>(new DataKeyComparator());
        this.statisticService = new StatisticService();
    }

    public synchronized void add(int value, Thread currentThread) {
        long timeInMillis = System.currentTimeMillis();
        DataKey dataKey = new DataKey(timeInMillis, currentThread);
        int queueSize = itemsMap.size();
        int currentSize = queueSize;
        if (queueSize > 0) {
            DataKey keyOfFirstElement = itemsMap.firstKey();
            if ((!keyOfFirstElement.getThread().equals(currentThread)) || (queueSize == maxElementsCount)) {
                Object result = itemsMap.remove(keyOfFirstElement);
                if (result != null) {
                    statisticService.removeElement(keyOfFirstElement);
                    currentSize = currentSize - 1;
                }
            }
        }
        itemsMap.put(dataKey, value);
        statisticService.saveElement(dataKey);
        currentSize = currentSize + 1;
        if (currentSize > maxSizeInQueue) {
            maxSizeInQueue = currentSize;
        }
    }

    //It is really important to make this method synchronized, because add method can modify data
    public synchronized StatisticData getStatistic() {
        return new StatisticData(maxSizeInQueue, statisticService.getStatisticPerThread());
    }
}
