package com.codein.data.model;

import java.util.Comparator;

public class DataKeyComparator implements Comparator<DataKey> {
    public int compare(DataKey o1, DataKey o2) {
        if (o1.getMillisTime().equals(o2.getMillisTime())) {
            return 0;
        }
        return o1.getMillisTime() < o2.getMillisTime() ? -1 : 1;
    }
}
