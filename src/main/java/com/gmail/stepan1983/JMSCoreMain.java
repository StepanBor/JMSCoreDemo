package com.gmail.stepan1983;

public class JMSCoreMain {

    public static void main(String[] args) {

        Thread producer1=new Thread(new JMSCoreMessageProducer());
        Thread producer2=new Thread(new JMSCoreMessageProducer());
        Thread producer3=new Thread(new JMSCoreMessageProducer());

        Thread consumer1=new Thread(new JMSCoreMessageConsumer());
        Thread consumer2=new Thread(new JMSCoreMessageConsumer());

        producer1.start();
        producer2.start();
        producer3.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        consumer1.start();
        consumer2.start();

    }

}
