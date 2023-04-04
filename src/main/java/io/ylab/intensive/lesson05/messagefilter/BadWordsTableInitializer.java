package io.ylab.intensive.lesson05.messagefilter;

import io.ylab.intensive.lesson05.DbUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BadWordsTableInitializer {
    private final DataSource dataSource;

    public BadWordsTableInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() throws SQLException {
        ResultSet resultSet = null;

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, "badwords", null);

            if (!resultSet.next()) {
                createTable();
                fillTheTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
        }
    }

    @PreDestroy
    public void destroy() throws SQLException {
        String sql = "DROP TABLE badwords";
        DbUtil.applyDdl(sql, dataSource);
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE badwords(word varchar)";
        DbUtil.applyDdl(sql, dataSource);
    }

    public void fillTheTable() {
        String sql = "INSERT INTO badwords (word) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            List<String> words = loadFromFile();
            for (String word : words) {
                preparedStatement.setString(1, word);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadFromFile() {
        List<String> words = null;

        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/badwords.txt"))) {
            words = lines.flatMap(line -> Arrays.stream(line.split(",")))
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
