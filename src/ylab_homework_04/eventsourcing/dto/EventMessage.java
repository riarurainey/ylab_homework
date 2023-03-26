package ylab_homework_04.eventsourcing.dto;

import ylab_homework_04.eventsourcing.Person;
import ylab_homework_04.eventsourcing.domain.Type;

public class EventMessage {
    private Type type;
    private Person person;

    public EventMessage() {
    }

    public EventMessage(Type type, Person person) {
        this.type = type;
        this.person = person;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
