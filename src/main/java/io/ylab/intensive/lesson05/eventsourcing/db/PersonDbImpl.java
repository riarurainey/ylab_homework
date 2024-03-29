package io.ylab.intensive.lesson05.eventsourcing.db;


import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class PersonDbImpl implements PersonDb {
    private final DataSource dataSource;

    @Autowired
    public PersonDbImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        PreparedStatement pstm = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE from person WHERE person_id =  ?";
            pstm = connection.prepareStatement(sql);
            pstm.setLong(1, person.getId());
            pstm.executeUpdate();
        } finally {
            pstm.close();
        }
    }

    @Override
    public void savePerson(Person person) throws SQLException {
        PreparedStatement pstm = null;

        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO person (person_id, first_name, middle_name, last_name) VALUES (?, ?, ?, ?)";
            pstm = connection.prepareStatement(sql);
            pstm.setLong(1, person.getId());
            pstm.setString(2, person.getName());
            pstm.setString(3, person.getMiddleName());
            pstm.setString(4, person.getLastName());
            pstm.executeUpdate();
        } finally {
            pstm.close();
        }
    }
}