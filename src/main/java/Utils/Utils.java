package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Utilities needed for this project
 * @author De Bleser Dimitri
 */
public class Utils {

    /**
     * Delete file and files within it if it's a folder
     * @param file to delete
     */
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