package io.logico.sbjms20;

import java.util.Date;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

public class Listener implements MessageListener {

    @Override
    public void onMessage(Message m) {
        try {
            TextMessage msg = (TextMessage) m;
            // Show message
            System.out.printf("[Dequeued message at %s] %s\n", (new Date()).toString(), msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}