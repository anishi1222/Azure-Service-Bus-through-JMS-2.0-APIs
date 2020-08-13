package io.logico.sbjms20;

import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String... args) {
        if(args.length!=3) {
            System.err.println("Three arguments are required.\njava sbjms20 {BASIC|STANDARD|PREMIUM} {QUEUE|TOPIC} {SEND|RECEIVE}");
            return;
        }

        // Check Service Bus property based on the 1st argument
        Optional<EnumSBProfile> targetSB = Arrays.stream(EnumSBProfile.values())
                .sequential()
                .filter(enumSbProfile -> enumSbProfile.getSKU().equalsIgnoreCase(args[0]))
                .findFirst();
        if(!targetSB.isPresent()) {
            System.err.println("Argument you specified is out of option.");
            return;
        }

        // Check request operation and target object
        Optional<EnumOperation> targetOperation = Arrays.stream(EnumOperation.values())
                .sequential()
                .filter(enumOperation -> enumOperation.getTargetObject().equalsIgnoreCase(args[1]) && enumOperation.getOperation().equalsIgnoreCase(args[2]) )
                .findFirst();
        if(!targetOperation.isPresent()) {
            System.err.printf("Either argument [%s] or [%s] are invalid.\n", args[1], args[2]);
            return;
        }

        SBJMSQueueTopic sbJMSQueueTopic = new SBJMSQueueTopic(targetSB.get());
        if(targetOperation.get().compareTo(EnumOperation.QUEUE_RECEIVE)==0) {
            sbJMSQueueTopic.receiveQueue(targetSB.get());
        } else if(targetOperation.get().compareTo(EnumOperation.QUEUE_SEND)==0) {
            sbJMSQueueTopic.sendQueue(targetSB.get());
        } else if(targetOperation.get().compareTo(EnumOperation.TOPIC_SEND)==0) {
            sbJMSQueueTopic.sendTopic(targetSB.get());
        } else if(targetOperation.get().compareTo(EnumOperation.TOPIC_RECEIVE)==0) {
            sbJMSQueueTopic.receiveTopic(targetSB.get());
        }
        else {
            System.err.println("specified argument is invalid");
        }
    }
}
