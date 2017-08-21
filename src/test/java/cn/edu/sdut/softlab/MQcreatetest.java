/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author huanlu
 */
public class MQcreatetest {
    private  static final String url="tcp://127.0.0.1:61616";
    //private  static final String queueName = "queue-test";
    private  static final String topicName = "topic-test";
    
    public static void main(String[] args) throws JMSException {
        
        //1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        
        //2.创建COnnection
        Connection connection = connectionFactory.createConnection();
        
        //3.启动连接
        connection.start();
        
        //4.创建会话(参数一:是否在事务中去处理,参数二:应答模式-自适应)
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        
        //5.创建一个目标
        //Destination destination = session.createQueue(queueName);
        Destination destination = session.createTopic(topicName);
        
        //6.创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        
        for (int i = 0; i < 100; i++) {
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("test " + i);
            
            //8.发布消息
            producer.send(textMessage);
            
            System.out.println("发送消息 " + textMessage.getText());
        }
        
        //9.关闭连接
        connection.close();
    }
}
