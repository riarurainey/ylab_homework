package io.ylab.intensive.lesson05.messagefilter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class MessageFilterApp {
    public static void main(String[] args) throws IOException, SQLException, TimeoutException, InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.start();
        Consumer consumer = applicationContext.getBean(Consumer.class);
        consumer.startConsume();
    }
}
