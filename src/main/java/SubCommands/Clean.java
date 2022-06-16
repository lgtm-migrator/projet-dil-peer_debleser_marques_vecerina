package java.SubCommands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import Utils.Utils;

/**
 * Clean a static site. Concretely, remove the build directory that
 * was make by a build command.
 *
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    /**
     * Method to carry out the fonctionalities of clean command
     * @return 0 when the clean is done
     */
    @Override public Integer call() {
        File dir = site.toFile();
        if (!dir.exists()) {
            System.out.println("Nothing to clean");
            return 0;
        }
        Utils.deleteFile(dir);
        System.out.println("Website files cleaned");
        return 0;
    }
}