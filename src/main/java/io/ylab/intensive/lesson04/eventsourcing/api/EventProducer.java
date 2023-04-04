package io.ylab.intensive.lesson04.eventsourcing.api;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.eventsourcing.dto.EventMessage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static io.ylab.intensive.lesson04.eventsourcing.util.Util.OBJECT_MAPPER;

public class EventProducer {
    private ConnectionFactory connectionFactory;

    public EventProducer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void produceMessage(String queueName, EventMessage eventMessage) throws IOException, TimeoutException {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            String message = OBJECT_MAPPER.writeValueAsString(eventMessage);
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Отправлено сообщение '" + message + "'");
        }
    }
}
