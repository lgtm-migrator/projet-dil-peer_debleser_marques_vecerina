package Watcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Class to watch a directory for changes
 * @author De Bleser Dimitri
 * @author Vecerina Ivan
 * @author Peer Vincent
 * @author Marques Nora Andre
 * @version 1.0
 */
public class Watcher {

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;

    /**
     * Constructor
     * @param dir path of directory
     * @throws IOException
     */
    public Watcher(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();

        registerAllInDirectory(dir);
    }

    /**
     * Method to register the directory to watch
     * @param dirPath path of directory
     * @throws IOException
     */
    private void registerDirectory(Path dirPath) throws IOException {
        WatchKey key = dirPath.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dirPath);
    }

    /**
     * Method to add to the watcher every file and directory in the parent directory
     * @param dirPath path of parent directory
     * @throws IOException
     */
    private void registerAllInDirectory(final Path dirPath) throws IOException {
        Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>(){
            public FileVisitResult preVisitResult(Path dir, BasicFileAttributes attributes) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Method that register every change to directory and tells the user what change
     * @param dirPath path of directory
     */
    public void watch(String dirPath){
        for (;;){

            // wait for key to be signalled
            WatchKey key;
            try{
                key = watcher.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            Path dir = keys.get(key);
            if(dir == null){
                System.err.println("WatchKey not recognized!");
                continue;
            }

            // display change to user
            for (WatchEvent<?> event : key.pollEvents()){
                WatchEvent.Kind<?> kind = event.kind();

                // Context for directory entry event is the file name of entry
                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);

                System.out.format("%s: %s\n", event.kind().name(), child);

                // if new file added to directory need to be added to watcher
                if(kind == ENTRY_CREATE){
                    try{
                        if(Files.isDirectory(child))
                            registerAllInDirectory(child);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            boolean valid = key.reset();

            if(!valid) {
                keys.remove(key);

                if(keys.isEmpty())
                    break;
            }
        }
    }
}
