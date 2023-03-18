package datedmap;

import java.util.*;

public interface DatedMap {

    void put(String key, String value);
    String get(String key);
    boolean containsKey(String key);
    void remove(String key);
    Set<String> keySet();
    Date getKeyLastInsertionDate(String key);

}
