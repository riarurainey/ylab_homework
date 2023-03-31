package io.ylab.intensive.lesson04.eventsourcing.api;


import io.ylab.intensive.lesson04.eventsourcing.Person;
import io.ylab.intensive.lesson04.eventsourcing.domain.Type;
import io.ylab.intensive.lesson04.eventsourcing.dto.EventMessage;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static io.ylab.intensive.lesson04.RabbitMQUtil.PERSON_QUEUE;

/**
 * Тут пишем реализацию
 */
public class PersonApiImpl implements PersonApi {

    private EventProducer eventProducer;
    private DataSource dataSource;

    public PersonApiImpl(EventProducer eventProducer, DataSource dataSource) {
        this.eventProducer = eventProducer;
        this.dataSource = dataSource;
    }

    @Override
    public void deletePerson(Long personId) {
        Person person = new Person(personId);
        sendMessageToQueue(Type.PERSON_DELETION, person);
    }

    @Override
    public void savePerson(Long personId, String firstName, String lastName, String middleName) {
        Person person = new Person(firstName, lastName, middleName);
        person.setId(personId);
        sendMessageToQueue(Type.PERSON_CREATION, person);
    }

    private void sendMessageToQueue(Type type, Person person) {
        EventMessage eventMessage = new EventMessage(type, person);
        try {
            eventProducer.produceMessage(PERSON_QUEUE, eventMessage);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person findPerson(Long personId) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM person WHERE person_id = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setLong(1, personId);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                Person person = new Person();
                person.setId(personId);
                person.setName(resultSet.getString("first_name"));
                person.setMiddleName(resultSet.getString("middle_name"));
                person.setLastName(resultSet.getString("last_name"));
                return person;
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
            pstm.close();
        }
        return null;
    }

    @Override
    public List<Person> findAll() throws SQLException {
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM person";
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();
            List<Person> persons = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("person_id"));
                person.setName(resultSet.getString("first_name"));
                person.setMiddleName(resultSet.getString("middle_name"));
                person.setLastName(resultSet.getString("last_name"));
                persons.add(person);
            }
            return persons;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            resultSet.close();
            pstm.close();
        }
        return null;
    }
}
