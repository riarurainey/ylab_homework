package io.ylab.intensive.lesson04.eventsourcing.db;

import io.ylab.intensive.lesson04.eventsourcing.Person;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


public interface PersonDb {

    void deletePerson(Person person) throws SQLException;

    void savePerson(Person person) throws SQLException;
}
