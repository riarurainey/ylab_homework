package io.ylab.intensive.lesson05.sqlquerybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLQueryBuilderImpl implements SQLQueryBuilder {
    private final DataSource dataSource;

    @Autowired
    public SQLQueryBuilderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String queryForTable(String tableName) throws SQLException {
        List<String> columns = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, tableName, null);

            if (resultSet.next()) {
                resultSet = databaseMetaData.getColumns(null, null, tableName, null);
                while (resultSet.next()) {
                    columns.add(resultSet.getString("COLUMN_NAME"));
                }

                String resultColumns = String.join(", ", columns);
                return String.format("SELECT %s FROM %s", resultColumns, tableName);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return null;
    }

    @Override
    public List<String> getTables() throws SQLException {
        ResultSet resultSet = null;
        List<String> tableNames = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});

            while (resultSet.next()) {
                tableNames.add(resultSet.getString("TABLE_NAME"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
        }
        return tableNames;
    }
}
