package main.java.SubCommands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @Override public Integer call() throws IOException {
        File dir = site.toFile();
        if (!dir.exists()) {
            System.out.println("Nothing to clean");
            return 0;
        }
        deleteFile(dir);
        System.out.println("Website files cleaned");
        return 0;
    }

    private void deleteFile(File file) {
        if (file.exists()) {
            // Check if directory
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null)
                    // If is dir and has files delete these first
                    for (File f : files)
                        deleteFile(f);
            }

            // Finally delete file / folder
            file.delete();
        }
    }

}