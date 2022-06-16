package main.java.SubCommands;

import Watcher.Watcher;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * Serve the compilation result on a site
 */
@Command(name = "serve", description = "Serve a static site")
public class Serve implements Callable<Integer> {

    @Parameters(paramLabel = "SITE", description = "The site to serve")
    public Path site;

    @CommandLine.Option(names = {"-w", "--watch"}, description = "build site for every update")
    private static boolean beingWatched;

    @Override
    public Integer call() throws IOException {

        if(beingWatched){
            var watcher = new Watcher(site);
            watcher.watch(String.valueOf(Path.of(site.toString())));
            beingWatched = false;
        }

        // Build the site
        new CommandLine(new Build()).execute(site.toString());

        // Serve the site
        Javalin.create(config -> {
            config.addStaticFiles(site.resolve("build").toAbsolutePath().toString(), Location.EXTERNAL);
        }).start(8080);

        return 0;
    }


}