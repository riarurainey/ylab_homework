package io.ylab.intensive.lesson03.datedmap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DatedMapImpl implements DatedMap {

    Map<String, String> storage = new HashMap<>();
    Map<String, Date> dateMap = new HashMap<>();

    @Override
    public void put(String key, String value) {
        storage.put(key, value);
        dateMap.put(key, new Date());
    }

    @Override
    public String get(String key) {
        return storage.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return storage.containsKey(key);
    }

    @Override
    public void remove(String key) {
        storage.remove(key);
        dateMap.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return storage.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return dateMap.get(key);
    }
}
