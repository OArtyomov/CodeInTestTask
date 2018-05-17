package com.codein.data.model;

import java.util.Comparator;

public class DataKeyComparator implements Comparator<DataKey> {
    public int compare(DataKey o1, DataKey o2) {
        if (o1.getTime().equals(o2.getTime())) {
            return 0;
        }
        return o1.getTime() < o2.getTime() ? -1 : 1;
    }
}
