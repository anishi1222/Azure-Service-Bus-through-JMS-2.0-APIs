package io.logico.sbjms20;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainWithoutThreeArgumentsPrintsUsageMessage() throws Exception {
        String error = runMainAndCaptureError("BASIC", "QUEUE");

        assertTrue(error.contains("Three arguments are required."));
        assertTrue(error.contains("java sbjms20 {BASIC|STANDARD|PREMIUM} {QUEUE|TOPIC} {SEND|RECEIVE}"));
    }

    @Test
    void mainWithUnknownProfilePrintsProfileValidationError() throws Exception {
        String error = runMainAndCaptureError("DEVELOPER", "QUEUE", "SEND");

        assertTrue(error.contains("Argument you specified is out of option."));
    }

    @Test
    void mainWithUnknownOperationPrintsOperationValidationError() throws Exception {
        String error = runMainAndCaptureError("BASIC", "QUEUE", "DELETE");

        assertTrue(error.contains("Either argument [QUEUE] or [DELETE] are invalid."));
    }

    private String runMainAndCaptureError(String... args) throws Exception {
        PrintStream originalError = System.err;
        ByteArrayOutputStream capturedError = new ByteArrayOutputStream();

        try (PrintStream errorStream = new PrintStream(capturedError, true, StandardCharsets.UTF_8.name())) {
            System.setErr(errorStream);
            Main.main(args);
        } finally {
            System.setErr(originalError);
        }

        return capturedError.toString(StandardCharsets.UTF_8.name());
    }
}