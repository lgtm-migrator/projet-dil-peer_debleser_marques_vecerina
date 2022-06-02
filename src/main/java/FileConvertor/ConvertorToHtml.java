package FileConvertor;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class to convert a markdown file to a html one
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
public class ConvertorToHtml implements Helper<String> {

    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

    public static Template getMdTemplate(Path source) throws IOException {
        TemplateLoader loader = new FileTemplateLoader(source.resolve("template").toFile());
        Handlebars handlebars = new Handlebars(loader);
        handlebars.registerHelper("md", new ConvertorToHtml());
        return handlebars.compile("layout");
    }

    private Object mdToHtml(String markdown){
        Node document = parser.parse(markdown);
        return htmlRenderer.render(document);
    }

    @Override
    public Object apply(String s, Options options){
        return mdToHtml(s);
    }
}
