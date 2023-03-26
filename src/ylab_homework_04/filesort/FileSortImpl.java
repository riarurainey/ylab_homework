package ylab_homework_04.filesort;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileSortImpl implements FileSorter {
    private DataSource dataSource;
    private static final int BATCH_SIZE = 5;

    public FileSortImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File sort(File data) throws SQLException {
        String line;
        List<Long> parseLongs = new ArrayList<>();
        int currentBatchSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(data))) {
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    parseLongs.add(Long.parseLong(line));
                    currentBatchSize++;
                    if (currentBatchSize == BATCH_SIZE) {
                        sendBatch(parseLongs);
                        currentBatchSize = 0;
                        parseLongs.clear();
                    }
                }
            }
            sendBatch(parseLongs);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement pstm = null;
        PrintWriter writer = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM numbers ORDER BY val ASC";
            pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();
            writer = new PrintWriter("src/ylab_homework_04/filesort/files/nums-sorted.txt", StandardCharsets.UTF_8);
            while (resultSet.next()){
                writer.println(resultSet.getString("val"));
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            pstm.close();
            writer.close();
        }

        return null;
    }

    private void sendBatch(List<Long> list) throws SQLException {
        PreparedStatement pstm = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO numbers (val) VALUES (?)";
            pstm = connection.prepareStatement(sql);
            for (Long num : list) {
                pstm.setLong(1, num);
                pstm.addBatch();
            }
            pstm.executeBatch();
        } finally {
            pstm.close();
        }
    }
}
