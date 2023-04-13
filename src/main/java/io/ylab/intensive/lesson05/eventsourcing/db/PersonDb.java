package io.ylab.intensive.lesson05.eventsourcing.db;

import io.ylab.intensive.lesson05.eventsourcing.Person;

import java.sql.SQLException;

public interface PersonDb {

    void deletePerson(Person person) throws SQLException;

    void savePerson(Person person) throws SQLException;
}
