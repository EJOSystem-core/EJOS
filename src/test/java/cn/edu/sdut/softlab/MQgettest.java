/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author huanlu
 */
public class MQgettest {

    private static final String url = "tcp://127.0.0.1:61616";
    //private  static final String queueName = "queue-test";
    private static final String topicName = "topic-test";

    public static void main(String[] args) throws JMSException {
        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2.创建COnnection
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话(参数一:是否在事务中去处理,参数二:应答模式-自适应)
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建一个目标
        //Destination destination = session.createQueue(queueName);
        Destination destination = session.createTopic(topicName);

        //6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //7.创建一个监听器
        consumer.setMessageListener((Message message) -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收到消息" + textMessage.getText());
            } catch (JMSException ex) {
                Logger.getLogger(MQgettest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //9.关闭连接
        connection.close();
    }
}
