package io.ylab.intensive.lesson04.persistentmap;


import io.ylab.intensive.lesson04.DbUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class PersistenceMapTest {
  public static void main(String[] args) throws SQLException {
    DataSource dataSource = initDb();
    PersistentMap persistentMap = new PersistentMapImpl(dataSource);

    persistentMap.init("ylab-base");
    persistentMap.put("house", "bricks");
    persistentMap.put("cat", "Tom");
    persistentMap.put("dog", "Row");
    String dog = persistentMap.get("dog");
    System.out.println(dog);
    List<String> keys = persistentMap.getKeys();
    System.out.println(keys);
    boolean cat = persistentMap.containsKey("cat");
    System.out.println(cat);
    persistentMap.remove("cat");
    List<String> keys2 = persistentMap.getKeys();
    System.out.println(keys2);
    persistentMap.clear();
    List<String> keys3 = persistentMap.getKeys();
    System.out.println(keys3);
  }
  
  public static DataSource initDb() throws SQLException {
    String createMapTable = "" 
                                + "drop table if exists persistent_map; " 
                                + "CREATE TABLE if not exists persistent_map (\n"
                                + "   map_name varchar,\n"
                                + "   KEY varchar,\n"
                                + "   value varchar\n"
                                + ");";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(createMapTable, dataSource);
    return dataSource;
  }
}
