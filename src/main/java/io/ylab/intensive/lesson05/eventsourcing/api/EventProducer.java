package io.ylab.intensive.lesson05.eventsourcing.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson05.eventsourcing.dto.EventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Component
public class EventProducer {
    private final ConnectionFactory connectionFactory;
    private final ObjectMapper objectMapper;

    @Autowired
    public EventProducer(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        this.connectionFactory = connectionFactory;
        this.objectMapper = objectMapper;
    }

    public void produceMessage(String queueName, EventMessage eventMessage) throws IOException, TimeoutException {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            String message = objectMapper.writeValueAsString(eventMessage);
            channel.basicPublish("", queueName, null, message.getBytes());
            System.out.println(" [x] Отправлено сообщение '" + message + "'");
        }
    }
}
