package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Utils {

    public static void deleteFile(File file) {
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