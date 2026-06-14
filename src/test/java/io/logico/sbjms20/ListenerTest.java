package io.logico.sbjms20;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import jakarta.jms.TextMessage;

class ListenerTest {

    @Test
    void onMessagePrintsDequeuedTextMessage() throws Exception {
        TextMessage message = textMessage("unit-test-message");
        PrintStream originalOutput = System.out;
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();

        try (PrintStream outputStream = new PrintStream(capturedOutput, true, StandardCharsets.UTF_8.name())) {
            System.setOut(outputStream);
            new Listener().onMessage(message);
        } finally {
            System.setOut(originalOutput);
        }

        String output = capturedOutput.toString(StandardCharsets.UTF_8.name());
        assertTrue(output.contains("[Dequeued message at "));
        assertTrue(output.contains("unit-test-message"));
    }

    private TextMessage textMessage(String text) {
        InvocationHandler handler = (proxy, method, args) -> {
            if ("getText".equals(method.getName())) {
                return text;
            }
            if ("toString".equals(method.getName())) {
                return "TextMessage[" + text + "]";
            }
            return defaultValue(method.getReturnType());
        };

        return (TextMessage) Proxy.newProxyInstance(
                TextMessage.class.getClassLoader(),
                new Class<?>[]{TextMessage.class},
                handler);
    }

    private Object defaultValue(Class<?> returnType) {
        if (!returnType.isPrimitive()) {
            return null;
        }
        if (boolean.class.equals(returnType)) {
            return false;
        }
        if (byte.class.equals(returnType)) {
            return (byte) 0;
        }
        if (short.class.equals(returnType)) {
            return (short) 0;
        }
        if (int.class.equals(returnType)) {
            return 0;
        }
        if (long.class.equals(returnType)) {
            return 0L;
        }
        if (float.class.equals(returnType)) {
            return 0.0F;
        }
        if (double.class.equals(returnType)) {
            return 0.0D;
        }
        if (char.class.equals(returnType)) {
            return '\0';
        }
        return null;
    }
}