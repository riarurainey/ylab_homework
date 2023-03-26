package ylab_homework_04.eventsourcing.db;

import ylab_homework_04.eventsourcing.Person;

import java.sql.SQLException;

public interface PersonDb {

    void deletePerson(Person person) throws SQLException;

    void savePerson(Person person) throws SQLException;
}
