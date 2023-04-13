package io.ylab.intensive.lesson04.persistentmap;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, методы которого надо реализовать
 */
public class PersistentMapImpl implements PersistentMap {

    private DataSource dataSource;
    private String mapName = null;

    public PersistentMapImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init(String name) {
        this.mapName = name;
    }

    @Override
    public boolean containsKey(String key) throws SQLException {
        return this.get(key) != null;
    }

    @Override
    public List<String> getKeys() throws SQLException {
        List<String> keys = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM persistent_map WHERE map_name = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, mapName);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                keys.add(resultSet.getString("KEY"));
            }
            return keys;
        } finally {
            resultSet.close();
            pstm.close();
        }
    }

    @Override
    public String get(String key) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM persistent_map WHERE map_name = ? AND KEY = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, mapName);
            pstm.setString(2, key);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("value");
            }
            return null;
        } finally {
            resultSet.close();
            pstm.close();
        }
    }

    @Override
    public void remove(String key) throws SQLException {
        PreparedStatement pstm = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE from persistent_map WHERE map_name =  ? AND KEY = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, mapName);
            pstm.setString(2, key);
            pstm.executeUpdate();
        } finally {
            pstm.close();
        }
    }

    @Override
    public void put(String key, String value) throws SQLException {
        PreparedStatement pstm = null;
        try (Connection connection = dataSource.getConnection()) {
            if (this.get(key) != null) {
                String sql = "UPDATE persistent_map SET value = ? WHERE KEY = ? AND map_name = ?";
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, value);
                pstm.setString(2, key);
                pstm.setString(3, mapName);

            } else {
                String sql = "INSERT INTO persistent_map (map_name, KEY, value) VALUES (?, ?, ?)";
                pstm = connection.prepareStatement(sql);
                pstm.setString(1, mapName);
                pstm.setString(2, key);
                pstm.setString(3, value);
            }
            pstm.executeUpdate();
        } finally {
            pstm.close();
        }
    }

    @Override
    public void clear() throws SQLException {
        PreparedStatement pstm = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE from persistent_map WHERE map_name =  ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, mapName);
            pstm.executeUpdate();
        } finally {
            pstm.close();
        }
    }
}
