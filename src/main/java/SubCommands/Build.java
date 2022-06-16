package SubCommands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.concurrent.Callable;

import FileConvertor.ConvertorForYaml;
import FileConvertor.ConvertorToHtml;
import Parser.PageParser;
import Watcher.Watcher;
import com.github.jknack.handlebars.Template;
import picocli.CommandLine;
import picocli.CommandLine.Command;


/**
 * Build a static site from a specified path. Once the build done, all
 * .md and .yaml files have been converted in a .html file in a new
 * directory named build. Other files still unchanged.
 *
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @CommandLine.Option(names = {"-w", "--watch"}, description = "build site for every update")
    private static boolean beingWatched;

    /**
     * Method to carry out the fonctionalities of build command
     * @return Ok status if build terminated with success
     * @throws IOException
     */
    @Override public Integer call() throws IOException {

        // start if the user use the option watch
        if(beingWatched){
            var watcher = new Watcher(site);
            watcher.watch(String.valueOf(Path.of(site.toString()))); //watch the directory for changes
            beingWatched = false;
        }

        Map<String, Object> configuration = ConvertorForYaml.parseYaml(site);
        Template template = ConvertorToHtml.getMdTemplate(site);

        new CommandLine(new Clean()).execute(site.toString());

        // browse the directory
        Files.walk(site)
                .filter(file -> file.toString().endsWith(".md"))
                .forEach(source -> {
                    try {
                        String html = PageParser.parse(site, configuration, template);

                        //create html file when there is a md file
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