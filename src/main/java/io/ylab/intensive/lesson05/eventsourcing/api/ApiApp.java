package io.ylab.intensive.lesson05.eventsourcing.api;

import io.ylab.intensive.lesson05.eventsourcing.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ApiApp {
    public static void main(String[] args) throws Exception {
        // Тут пишем создание PersonApi, запуск и демонстрацию работы
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        PersonApi personApi = applicationContext.getBean(PersonApi.class);
        // пишем взаимодействие с PersonApi
        personApi.savePerson(124L, "Samuel", "Ilisaev", "Rashidovich");
        //Ожидание сохранения сущности
        while (personApi.findAll().size() == 0) {

        }

        List<Person> persons = personApi.findAll();
        System.out.println("Сущности в базе данных: " + persons);

        Person person1 = personApi.findPerson(persons.get(0).getId());
        System.out.println("Сущность в базе данных: " + person1);

        personApi.deletePerson(person1.getId());

        List<Person> all = personApi.findAll();
        System.out.println("Сущности в базе данных после удаления: " + all);
    }
}
