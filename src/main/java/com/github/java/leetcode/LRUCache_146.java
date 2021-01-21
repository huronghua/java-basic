package com.github.java.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache_146 extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LRUCache_146(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}
