package Watcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 *
 * @author De Bleser Dimitri
 * @author Vecerina Ivan
 * @author Peer Vincent
 * @author Marques Nora Andre
 * @version 1.0
 */
public class Watcher {

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;


    public Watcher(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();

        registerAllInDirectory(dir);
    }

    private void registerDirectory(Path dirPath) throws IOException {
        WatchKey key = dirPath.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dirPath);
    }

    private void registerAllInDirectory(final Path dirPath) throws IOException {
        Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>(){
            public FileVisitResult preVisitResult(Path dir, BasicFileAttributes attributes) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public void watch(String dirPath){
        for (;;){
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

            for (WatchEvent<?> event : key.pollEvents()){
                WatchEvent.Kind kind = event.kind();

                Path name = ((WatchEvent<Path>)event).context();
                Path child = dir.resolve(name);

                System.out.format("%s: %s\n", event.kind().name(), child);

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
