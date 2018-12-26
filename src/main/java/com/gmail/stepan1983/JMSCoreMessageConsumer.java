package com.gmail.stepan1983;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSCoreMessageConsumer implements  Runnable {

    public void run() {

        /*ConnectionFactory->Connection->Session->MessageProducer/Consumer->Send/Receive*/

        try {

            ActiveMQConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory("tcp://PRIMARY:61616");

            Connection connection = connectionFactory.createConnection();

            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue("JMSCoreTest.Queue");

            MessageConsumer consumer = session.createConsumer(destination);

            Message message = consumer.receive(1000);

            if (message instanceof TextMessage){
                TextMessage textMessage=(TextMessage) message;

                String text=textMessage.getText();

                System.out.println("Thread "+Thread.currentThread().getName()+" has received message: " +text);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
