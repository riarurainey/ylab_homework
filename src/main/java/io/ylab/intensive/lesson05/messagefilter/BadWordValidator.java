package io.ylab.intensive.lesson05.messagefilter;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BadWordValidator {
    private final DataSource dataSource;

    public BadWordValidator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private String validate(String word) {
        String sql = "SELECT FROM badwords WHERE word = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return replaceString(word);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return word;
    }

    public String filterWord(String line) {
        String[] words = line.split("\\s+");
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            String resultWord = validate(word);
            builder.append(resultWord).append(" ");
        }
        return builder.toString().trim();
    }

    private String replaceString(String word) {
        char[] chars = word.toCharArray();
        for (int i = 1; i < chars.length - 1; i++) {
            chars[i] = '*';
        }
        return new String(chars);
    }
}
