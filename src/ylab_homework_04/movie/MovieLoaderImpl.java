package ylab_homework_04.movie;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieLoaderImpl implements MovieLoader {
    private DataSource dataSource;

    public MovieLoaderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadData(File file) {
        List<String> list = parseFromFile(file);
        List<Movie> movies = mapParsedFileToMovie(list);
        try {
            saveToDb(movies);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private List<String> parseFromFile(File csvFile) {
        String line;
        List<String> parseStrings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && (!line.startsWith("Year") && !line.startsWith("INT"))) {
                    parseStrings.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseStrings;
    }

    private List<Movie> mapParsedFileToMovie(List<String> csvMovies) {
        List<Movie> movies = new ArrayList<>();
        for (String parsedMovie : csvMovies) {
            String[] split = parsedMovie.split(";");
            Movie movie = new Movie();
            movie.setYear(Integer.parseInt(split[0]));
            movie.setLength("".equals(split[1]) ? null : Integer.parseInt(split[1]));
            movie.setTitle("".equals(split[2]) ? null : split[2]);
            movie.setSubject("".equals(split[3]) ? null : split[3]);
            movie.setActors("".equals(split[4]) ? null : split[4]);
            movie.setActress("".equals(split[5]) ? null : split[5]);
            movie.setDirector("".equals(split[6]) ? null : split[6]);
            movie.setPopularity("".equals(split[7]) ? null : Integer.parseInt(split[7]));
            movie.setAwards("Yes".equals(split[8]));
            movies.add(movie);
        }
        return movies;
    }

    private void saveToDb(List<Movie> movies) throws SQLException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO movie (year, length, title, subject, actors, actress, director, popularity, awards) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            for (Movie movie : movies) {
                preparedStatement.setInt(1, movie.getYear());

                if (movie.getLength() != null) {
                    preparedStatement.setInt(2, movie.getLength());
                } else {
                    preparedStatement.setNull(2, Types.INTEGER);
                }

                if (movie.getTitle() != null) {
                    preparedStatement.setString(3, movie.getTitle());
                } else {
                    preparedStatement.setNull(3, Types.VARCHAR);
                }

                if (movie.getSubject() != null) {
                    preparedStatement.setString(4, movie.getSubject());
                } else {
                    preparedStatement.setNull(4, Types.VARCHAR);
                }

                if (movie.getActors() != null) {
                    preparedStatement.setString(5, movie.getActors());
                } else {
                    preparedStatement.setNull(5, Types.VARCHAR);
                }

                if (movie.getActress() != null) {
                    preparedStatement.setString(6, movie.getActress());
                } else {
                    preparedStatement.setNull(6, Types.VARCHAR);
                }

                if (movie.getDirector() != null) {
                    preparedStatement.setString(7, movie.getDirector());
                } else {
                    preparedStatement.setNull(7, Types.VARCHAR);
                }

                if (movie.getPopularity() != null) {
                    preparedStatement.setInt(8, movie.getPopularity());
                } else {
                    preparedStatement.setNull(8, Types.INTEGER);
                }

                preparedStatement.setBoolean(9, movie.getAwards());
                preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }finally {
            preparedStatement.close();
        }
    }
}
