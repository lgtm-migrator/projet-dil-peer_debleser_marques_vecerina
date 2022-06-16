package java.SubCommands;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Initialize a static site in a specified path. This command could
 * create or extend the directory sprecified with the path, with .yaml, .md
 * files for example.
 *
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

    @Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;


    /**
     * Method to carry out the fonctionalities of init command
     * @return Ok status if build terminated with success
     * @throws IOException
     */
    @Override public Integer call() throws URISyntaxException, IOException {
        URI uri = Objects.requireNonNull(this.getClass().getResource("/init")).toURI();

        // Initialize a zip file system when the template is stored in a jar file
        if (uri.getScheme().equals("jar")) {
            Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            FileSystems.newFileSystem(uri, env);
        }

        Path template = Paths.get(uri);
        Files.walk(template).forEach(source -> {
            try {
                if (Files.isRegularFile(source)) {
                    // The FileSystem (file or jar) is inferred by the path.
                    // As we copy files from the jar to the filesystem,
                    // the toString method is called to prevent a wrong inference.
                    Path target = site.resolve(template.relativize(source).toString());
                    Files.createDirectories(target.getParent());
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        return 0;
    }
}
