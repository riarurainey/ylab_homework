package ylab_homework_04.eventsourcing.api;


import ylab_homework_04.eventsourcing.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonApi {
  void deletePerson(Long personId);

  void savePerson(Long personId, String firstName, String lastName, String middleName);

  Person findPerson(Long personId) throws SQLException;

  List<Person> findAll() throws SQLException;
}
