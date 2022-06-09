package SubCommands;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import Utils.Utils;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

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