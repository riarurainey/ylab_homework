package ylab_homework_04;

import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtil {

  public static final String PERSON_QUEUE = "Person_Queue";
  /*
   * Настройки подключения НЕ МЕНЯЕМ!
   * Надо настроить RabbitMQ таким образом, чтобы он работал со следующими
   * настройками
   */
  public static ConnectionFactory buildConnectionFactory() throws IOException, TimeoutException {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    connectionFactory.setPort(5672);
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    connectionFactory.setVirtualHost("/");
    connectionFactory.newConnection().close();
    return connectionFactory;
  }
}
