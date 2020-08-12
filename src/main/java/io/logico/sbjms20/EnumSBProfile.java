package io.logico.sbjms20;

public enum EnumSBProfile {
    BASIC("BASIC","Endpoint=sb://xxxx=", "Queue1", ""),
    STANDARD("STANDARD","Endpoint=sb://yyyy=", "Queue1", "Topic1"),
    PREMIUM("PREMIUM","Endpoint=sb://zzzz=", "Queue1", "Topic1");

    private final String connectionString;
    private final String sku;
    private final String queueName;
    private final String topicName;

    private EnumSBProfile(String sku, String connectionString, String queueName, String topicName) {
        this.sku = sku;
        this.connectionString = connectionString;
        this.queueName = queueName;
        this.topicName = topicName;
    }

    String getConnectionString() {
        return connectionString;
    }

    String getSKU() {
        return sku;
    }

    String getQueueName() {
        return queueName;
    }

    String getTopicName() {
        return topicName;
    }
}
