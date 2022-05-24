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

    /**
     * Method to convert markdown to html
     * @param markdown the content of a markdown file
     * @return the content of the html file
     */
    private static String convertMarkdownToHTML(String markdown) {
        Node document = parser.parse(markdown);
        return htmlRenderer.render(document);
    }

    /**
     * Method to create html files
     * @param path of the file
     * @param fileName name of the file
     * @throws IOException if can't find file
     */
    public static void createHtmlFile(String path, String fileName)throws IOException{

        //Files.createFile(Path.of(path + "\\build" + fileName + ".html"));
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(path + fileName + ".html"), StandardCharsets.UTF_8));
            bw.write(convertMarkdownToHTML(fileToString(fileName + ".md")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object apply(String s, Options options) throws IOException {
        return null;
    }
}
