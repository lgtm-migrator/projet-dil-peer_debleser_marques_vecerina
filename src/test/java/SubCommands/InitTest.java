package SubCommands;

import main.java.Statique.Statique;
import main.java.SubCommands.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InitTest {
    Path site;

    @BeforeEach
    void setUp() throws IOException {
        site = Files.createTempDirectory(Paths.get("."), "site_");
    }

    @AfterEach
    void tearDown() throws IOException {
        Utils.deleteRecursive(site);
    }

    @Test
    public void init() {
        int exitCode = new CommandLine(new Statique()).execute("init", site.toString());
        assertEquals(exitCode, 0);
        assertTrue(Files.exists(site.resolve("config.yaml")));
        assertTrue(Files.exists(site.resolve("index.md")));
        assertTrue(Files.exists(site.resolve("init/pages/page1.md")));
        assertTrue(Files.exists(site.resolve("init/pages/page2.md")));
        assertTrue(Files.exists(site.resolve("init/template/layout.hbs")));
        assertTrue(Files.exists(site.resolve("init/template/menu.hbs")));
    }
}
