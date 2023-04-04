package io.ylab.intensive.lesson05.messagefilter;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Consumer {
    private final ConnectionFactory connectionFactory;
    private final Producer producer;
    private final BadWordValidator validator;

    @Autowired
    public Consumer(ConnectionFactory connectionFactory, Producer producer, BadWordValidator validator) {
        this.connectionFactory = connectionFactory;
        this.producer = producer;
        this.validator = validator;
    }

    public void startConsume() throws IOException, TimeoutException {
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare("input", false, false, false, null);
            System.out.println(" [*] Ждем сообщения в очереди");

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("Получено:" + message);
                    try {
                        producer.produceMessage(validator.filterWord(message));
                    } catch (TimeoutException e) {
                        throw new RuntimeException(e);
                    }

                }
            };
            channel.basicConsume("input", true, consumer);
        }
    }
}
