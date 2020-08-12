package io.logico.sbjms20;

import com.microsoft.azure.servicebus.jms.ServiceBusJmsConnectionFactory;
import com.microsoft.azure.servicebus.jms.ServiceBusJmsConnectionFactorySettings;

import javax.jms.*;
import java.util.Date;

public class SBJMSQueueTopic {
    final int MAXLOOP = 100;
    EnumSBProfile enumSbProfile;
    ServiceBusJmsConnectionFactorySettings connectionFactorySettings;
    ConnectionFactory factory;

    public SBJMSQueueTopic(EnumSBProfile enumSbProfile) {
        // Instantiate Service Bus Connection Factory Setting
        this.connectionFactorySettings = new ServiceBusJmsConnectionFactorySettings();
        this.connectionFactorySettings.setConnectionIdleTimeoutMS(20000);
        this.factory = new ServiceBusJmsConnectionFactory(enumSbProfile.getConnectionString(), this.connectionFactorySettings);
    }

    public void sendQueue(EnumSBProfile enumSbProfile) {
        try (JMSContext jmsContext = factory.createContext() ) {
            // Create the queue and topic
            Queue queue = jmsContext.createQueue(enumSbProfile.getQueueName());
            // Create the JMS message producer
            JMSProducer producer = jmsContext.createProducer();
            for( int loop = 0 ; loop < MAXLOOP ; loop++ ) {
                // Create the message
                TextMessage msg = jmsContext.createTextMessage(String.format("[%d/%d] Send to %s in %s at %s", loop + 1, MAXLOOP, enumSbProfile.getQueueName(), enumSbProfile.getSKU(), (new Date()).toString()));
                // Show message
                System.out.printf("[Enqueuing message to %s in %s] %s\n", enumSbProfile.getQueueName(), enumSbProfile.getSKU(), msg.getText());
                // send the message to the queue
                producer.send(queue, msg);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receiveQueue(EnumSBProfile enumSbProfile) {
        try (JMSContext jmsContext = factory.createContext() ) {
            // Create the queue and topic
            Queue queue = jmsContext.createQueue(enumSbProfile.getQueueName());
            // set Message Listener
            JMSConsumer consumer = jmsContext.createConsumer(queue);
            consumer.setMessageListener(new Listener());
            System.out.println("Receiver is ready, waiting for messages...");
            System.out.println("press Ctrl+c to shutdown...");
            while (true) {
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendTopic(EnumSBProfile enumSbProfile) {
        try (JMSContext jmsContext = factory.createContext() ) {
            // Create the queue and topic
            Topic topic = jmsContext.createTopic(enumSbProfile.getTopicName());
            // Create the JMS message producer
            JMSProducer producer = jmsContext.createProducer();
            for( int loop = 0 ; loop < MAXLOOP ; loop++ ) {
                // Create the message
                TextMessage msg = jmsContext.createTextMessage(String.format("[%d/%d] Send to %s in %s at %s", loop + 1, MAXLOOP, enumSbProfile.getQueueName(), enumSbProfile.getSKU(), (new Date()).toString()));
                msg.setIntProperty("number", loop);
                // Show message
                System.out.printf("[Enqueuing message to %s in %s] %s\n", enumSbProfile.getQueueName(), enumSbProfile.getSKU(), msg.getText());
                // send the message to the queue
                producer.send(topic, msg);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public void receiveTopic(EnumSBProfile enumSbProfile) {
        try (JMSContext jmsContext = factory.createContext() ) {
            // Create the queue and topic
            Topic topic = jmsContext.createTopic(enumSbProfile.getTopicName());
            // set Message Listener
            JMSConsumer consumer = jmsContext.createConsumer(topic);
            consumer.setMessageListener(new Listener());
            System.out.println("Receiver is ready, waiting for messages...");
            System.out.println("press Ctrl+c to shutdown...");
            while (true) {
                Thread.sleep(1000);
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
