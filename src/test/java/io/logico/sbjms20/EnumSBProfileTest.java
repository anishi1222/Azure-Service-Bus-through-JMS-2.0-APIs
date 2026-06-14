package io.logico.sbjms20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumSBProfileTest {

    @Test
    void basicProfileExposesQueueOnlyConfiguration() {
        assertEquals("BASIC", EnumSBProfile.BASIC.getSKU());
        assertEquals("Endpoint=sb://xxxx=", EnumSBProfile.BASIC.getConnectionString());
        assertEquals("Queue1", EnumSBProfile.BASIC.getQueueName());
        assertEquals("", EnumSBProfile.BASIC.getTopicName());
    }

    @Test
    void standardProfileExposesQueueAndTopicConfiguration() {
        assertEquals("STANDARD", EnumSBProfile.STANDARD.getSKU());
        assertEquals("Endpoint=sb://yyyy=", EnumSBProfile.STANDARD.getConnectionString());
        assertEquals("Queue1", EnumSBProfile.STANDARD.getQueueName());
        assertEquals("Topic1", EnumSBProfile.STANDARD.getTopicName());
    }

    @Test
    void premiumProfileExposesQueueAndTopicConfiguration() {
        assertEquals("PREMIUM", EnumSBProfile.PREMIUM.getSKU());
        assertEquals("Endpoint=sb://zzzz=", EnumSBProfile.PREMIUM.getConnectionString());
        assertEquals("Queue1", EnumSBProfile.PREMIUM.getQueueName());
        assertEquals("Topic1", EnumSBProfile.PREMIUM.getTopicName());
    }
}