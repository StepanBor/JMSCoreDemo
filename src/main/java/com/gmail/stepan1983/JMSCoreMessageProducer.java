package com.gmail.stepan1983;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSCoreMessageProducer implements Runnable {

    public void run() {

        /*ConnectionFactory->Connection->Session->MessageProducer/Consumer->Send/Receive*/

        try {

            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("tcp://PRIMARY:61616");

            Connection connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("JMSCoreTest.Queue");


            MessageProducer producer = session.createProducer(destination);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            TextMessage textMessage = session.createTextMessage("Sending message from" + Thread.currentThread().getName());

            System.out.println("Sending message from" + Thread.currentThread().getName());

            producer.send(textMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
