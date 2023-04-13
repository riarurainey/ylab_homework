package io.ylab.intensive.lesson04.eventsourcing.dto;

import io.ylab.intensive.lesson04.eventsourcing.Person;
import io.ylab.intensive.lesson04.eventsourcing.domain.Type;

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
