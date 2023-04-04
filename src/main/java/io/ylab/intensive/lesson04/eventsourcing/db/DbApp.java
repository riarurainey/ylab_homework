package io.ylab.intensive.lesson04.eventsourcing.db;

import com.rabbitmq.client.ConnectionFactory;
import io.ylab.intensive.lesson04.DbUtil;
import io.ylab.intensive.lesson04.RabbitMQUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

import static io.ylab.intensive.lesson04.RabbitMQUtil.PERSON_QUEUE;

public class DbApp {
  public static void main(String[] args) throws Exception {
    DataSource dataSource = initDb();
    ConnectionFactory connectionFactory = initMQ();
    EventConsumer eventConsumer = new EventConsumer(connectionFactory, new PersonDbImpl(dataSource));
    eventConsumer.startConsume(PERSON_QUEUE);
  }
  
  private static ConnectionFactory initMQ() throws Exception {
    return RabbitMQUtil.buildConnectionFactory();
  }
  
  private static DataSource initDb() throws SQLException {
    String ddl = "" 
                     + "drop table if exists person;" 
                     + "create table if not exists person (\n"
                     + "person_id bigint primary key,\n"
                     + "first_name varchar,\n"
                     + "last_name varchar,\n"
                     + "middle_name varchar\n"
                     + ")";
    DataSource dataSource = DbUtil.buildDataSource();
    DbUtil.applyDdl(ddl, dataSource);
    return dataSource;
  }
}
