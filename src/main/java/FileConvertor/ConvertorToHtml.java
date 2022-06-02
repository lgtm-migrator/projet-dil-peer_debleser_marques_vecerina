package FileConvertor;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
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

    private static final Parser parser = Parser.builder().build();
    private static final HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

    /**
     * Method to convert the content of a file to a string
     * @param path of the file
     * @return content of file
     * @throws IOException if can't find the file
     */
    private static String fileToString(String path) throws IOException{
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            int r;
            while( (r = br.read()) != -1)
                text.append((char) r);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    @Override
    public Object apply(String s, Options options) throws IOException {
        Node document = parser.parse(s);
        return htmlRenderer.render(document);
    }
}
