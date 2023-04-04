package io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Producer {
    private final ConnectionFactory connectionFactory;

    public Producer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void produceMessage(String message) throws IOException, TimeoutException {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("output", false, false, false, null);
            channel.basicPublish("", "output", null, message.getBytes());
            System.out.println(" [x] Отправлено сообщение '" + message + "'");
        }
    }
}

