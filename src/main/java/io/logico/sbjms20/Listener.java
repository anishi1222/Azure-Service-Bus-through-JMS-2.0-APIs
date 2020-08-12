package io.logico.sbjms20;

import javax.jms.*;
import java.util.Date;

public class Listener implements MessageListener {

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