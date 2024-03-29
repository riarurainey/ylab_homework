package io.ylab.intensive.lesson04.eventsourcing.api;


import io.ylab.intensive.lesson04.eventsourcing.Person;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;


public interface PersonApi {
  void deletePerson(Long personId);

  void savePerson(Long personId, String firstName, String lastName, String middleName);

  Person findPerson(Long personId) throws SQLException;

  List<Person> findAll() throws SQLException;
}
