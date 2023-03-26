package ylab_homework_04.eventsourcing.api;

import com.rabbitmq.client.ConnectionFactory;
import ylab_homework_04.DbUtil;
import ylab_homework_04.RabbitMQUtil;
import ylab_homework_04.eventsourcing.Person;

import javax.sql.DataSource;
import java.util.List;


public class ApiApp {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = initMQ();
        DataSource dataSource = DbUtil.buildDataSource();

        EventProducer eventProducer = new EventProducer(connectionFactory);
        PersonApi personApi = new PersonApiImpl(eventProducer, dataSource);

        personApi.savePerson(124L, "Samuel","Ilisaev", "Rashidovich");

        //Ожидание сохранения сущности
        while (personApi.findAll().size() == 0){ };

        List<Person> persons = personApi.findAll();
        System.out.println("Сущности в базе данных: " + persons);

        Person person1 = personApi.findPerson(persons.get(0).getId());
        System.out.println("Сущность в базе данных: " +person1);

        personApi.deletePerson(person1.getId());

        List<Person> all = personApi.findAll();
        System.out.println("Сущности в базе данных после удаления: " + all);
    }

    private static ConnectionFactory initMQ() throws Exception {
        return RabbitMQUtil.buildConnectionFactory();
    }
}
