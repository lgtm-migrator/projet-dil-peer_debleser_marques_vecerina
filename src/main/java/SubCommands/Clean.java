package SubCommands;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(name = "clean", description = "Clean a static site")
public class Clean implements Callable<Integer> {

    @Override public Integer call() throws IOException {
        File dir = new File("./mon");
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