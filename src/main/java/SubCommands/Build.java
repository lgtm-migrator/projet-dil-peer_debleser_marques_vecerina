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
import Template.TemplateManager;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import picocli.CommandLine;
import picocli.CommandLine.Command;



@Command(name = "build", description = "Build a static site")
public class Build implements Callable<Integer> {

    @CommandLine.Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @Override public Integer call() throws IOException {

        Map<String, Object> configuration = ConvertorForYaml.parseYaml(site);

        String template = TemplateManager.handlebarParse();
        Files.walk(site)
                .filter(file -> file.toString().endsWith(".md"))
                .forEach(source -> {
                    try {
                        String html = PageParser.parse(site, configuration);

                        Path target = site.resolve("build")
                                .resolve(site.relativize(source).toString().replace(".md", ".html"));
                        Files.createDirectories(target.getParent());
                        Files.writeString(target, html, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        return 0;
    }
}

