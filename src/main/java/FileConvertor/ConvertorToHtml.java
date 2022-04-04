package FileConvertor;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConvertorToHtml {
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

    private static String convertMarkdownToHTML(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(document);
    }

    public static void createHtmlFile(String path, String fileName)throws IOException{

        Files.createFile(Path.of(path + fileName));
        try{
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
            bw.write(convertMarkdownToHTML(fileToString(fileName + ".md")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
