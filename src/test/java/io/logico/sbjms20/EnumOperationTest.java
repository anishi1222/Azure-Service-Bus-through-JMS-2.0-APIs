package io.logico.sbjms20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumOperationTest {

    @Test
    void queueSendHasQueueTargetAndSendOperation() {
        assertEquals("QUEUE", EnumOperation.QUEUE_SEND.getTargetObject());
        assertEquals("SEND", EnumOperation.QUEUE_SEND.getOperation());
    }

    @Test
    void queueReceiveHasQueueTargetAndReceiveOperation() {
        assertEquals("QUEUE", EnumOperation.QUEUE_RECEIVE.getTargetObject());
        assertEquals("RECEIVE", EnumOperation.QUEUE_RECEIVE.getOperation());
    }

    @Test
    void topicSendHasTopicTargetAndSendOperation() {
        assertEquals("TOPIC", EnumOperation.TOPIC_SEND.getTargetObject());
        assertEquals("SEND", EnumOperation.TOPIC_SEND.getOperation());
    }

    @Test
    void topicReceiveHasTopicTargetAndReceiveOperation() {
        assertEquals("TOPIC", EnumOperation.TOPIC_RECEIVE.getTargetObject());
        assertEquals("RECEIVE", EnumOperation.TOPIC_RECEIVE.getOperation());
    }
}