package io.ylab.intensive.lesson05.eventsourcing.db;

import com.rabbitmq.client.*;
import io.ylab.intensive.lesson05.eventsourcing.dto.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import static io.ylab.intensive.lesson04.eventsourcing.util.Util.OBJECT_MAPPER;

@Component
public class EventConsumer {
    private final ConnectionFactory connectionFactory;
    private final PersonDb personDb;

    @Autowired
    public EventConsumer(ConnectionFactory connectionFactory, PersonDb personDb) {
        this.connectionFactory = connectionFactory;
        this.personDb = personDb;
    }

    public void startConsume(String queueName) throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println(" [*] Ждем сообщения в очереди");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Получено:" + message);
                EventMessage eventMessage = OBJECT_MAPPER.readValue(message, EventMessage.class);
                try {
                    processMessage(eventMessage);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }

    private void processMessage(EventMessage eventMessage) throws SQLException {
        switch (eventMessage.getType()) {
            case PERSON_CREATION:
                personDb.savePerson(eventMessage.getPerson());
                break;
            case PERSON_DELETION:
                personDb.deletePerson(eventMessage.getPerson());
                break;
            default:
                throw new RuntimeException();
        }
    }
}
