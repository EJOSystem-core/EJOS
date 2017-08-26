///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cn.edu.sdut.softlab.util;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import javax.jms.Connection;
//import javax.jms.ConnectionFactory;
//import javax.jms.Destination;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageConsumer;
//import javax.jms.MessageListener;
//import javax.jms.MessageProducer;
//import javax.jms.Session;
//import javax.jms.TextMessage;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
///**
// *
// * @author huanlu
// */
//public class TansactionActiveMqUtil {
//
//    @Inject
//    Logger logger;
//
//    @Inject
//    Connection connection;
//
//    @Inject
//    Session session;
//
//    private static final String url = "tcp://127.0.0.1:61616";
//    private static final String queueName = "queue-test";
//    private static final String topicName = "topic-test";
//
//    @PostConstruct
//    public void init() {
//        logger.info("ActiveMq ConnectionFactory init Called:");
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        try {
//            connection = connectionFactory.createConnection();
//        } catch (JMSException ex) {
//            Logger.getLogger(TansactionActiveMqUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public MessageConsumer getTopicConsumer(boolean isTransaction) throws Exception {
//        connection.start();
//        session = connection.createSession(isTransaction, Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createTopic(topicName);
//        return session.createConsumer(destination);
//    }
//
//    public MessageProducer getTopicProducer(boolean isTransaction) throws Exception {
//        connection.start();
//        session = connection.createSession(isTransaction, Session.AUTO_ACKNOWLEDGE);
//        Destination destination = session.createTopic(topicName);
//        return session.createProducer(destination);
//    }
//
//    public void sendMessage(String message) throws Exception {
//        TextMessage textMessage = session.createTextMessage("test " + message);
//        MessageProducer producer = getTopicProducer(true);
//        producer.send(textMessage);
//    }
//
//    public void getMessage() throws Exception {
//        MessageConsumer consumer = this.getTopicConsumer(true);
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                
//            }
//        });
//    }
//}
