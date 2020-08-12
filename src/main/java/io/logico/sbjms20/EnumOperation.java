package io.logico.sbjms20;

public enum EnumOperation {
    QUEUE_SEND("QUEUE", "SEND"),
    QUEUE_RECEIVE("QUEUE", "RECEIVE"),
    TOPIC_SEND("TOPIC", "SEND"),
    TOPIC_RECEIVE("TOPIC", "RECEIVE");

    private final String targetObject;
    private final String operation;

    private EnumOperation(String targetObject, String operation) {
        this.operation = operation;
        this.targetObject = targetObject;
    }

    String getOperation() {
        return operation;
    }

    String getTargetObject() {
        return targetObject;
    }
}
