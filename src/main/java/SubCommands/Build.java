package main.java.SubCommands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.Callable;

import Watcher.Watcher;
import com.github.jknack.handlebars.Template;
import main.java.FileConvertor.ConvertorForYaml;
import main.java.FileConvertor.ConvertorToHtml;
import main.java.Parser.PageParser;
import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @CommandLine.Option(names = {"-w", "--watch"}, description = "build site for every update")
    private static boolean beingWatched;

    @Override public Integer call() throws IOException {

        if(beingWatched){
            var watcher = new Watcher(site);
            watcher.watch(String.valueOf(Path.of(site.toString())));
            beingWatched = false;
        }

        Map<String, Object> configuration = ConvertorForYaml.parseYaml(site);

        Template template = ConvertorToHtml.getMdTemplate(site);


        new CommandLine(new Clean()).execute(site.toString()+"/build");

        Files.walk(site)
                .filter(file -> file.toString().endsWith(".md"))
                .forEach(source -> {
                    try {
                        String html = PageParser.parse(site, configuration, template);

                        Path target = site.resolve("build")
                                .resolve(site.relativize(source).toString().replace(".md", ".html"));
                        Files.createDirectories(target.getParent());
                        Files.writeString(target, html, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        System.out.println("Static website built!");

        return CommandLine.ExitCode.OK;
    }
}

