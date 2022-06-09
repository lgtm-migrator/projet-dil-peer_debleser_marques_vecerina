import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class StatiqueTest {

    @Test
    void result() {
        int exitCode = new CommandLine(new Statique()).execute();
        assertEquals(exitCode, 0);
    }

    @Test
    void exception() {
        assertThrows(Exception.class, () -> {
            throw new Exception();
        });
    }

    @Test
    void output() throws Exception {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            System.setOut(new PrintStream(output));
            new CommandLine(new Statique()).execute();
            assertTrue((output.toString().contains("A brand new static site generator.")));
        }
    }

}
