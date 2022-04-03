import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String dataDir = "C:\\Heig-vd\\DIL\\projet-dil-peer_debleser_marques_vecerina\\";
        StringBuilder input = new StringBuilder(), output = new StringBuilder();

        FileReader fileInput = new FileReader(dataDir + "README.md");
        FileWriter fileOutput = new FileWriter("Output.html");

        BufferedReader br = new BufferedReader(fileInput);
        BufferedWriter bw = new BufferedWriter(fileOutput);

        int r = 0;
        while( (r = br.read()) != -1)
        {
            input.append((char) r);
        }

        output = new StringBuilder(convertMarkdownToHTML(String.valueOf(input)));

        bw.write(String.valueOf(output));

        br.close();
        bw.close();
        fileInput.close();
        fileOutput.close();
    }

    public static String convertMarkdownToHTML(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
        return htmlRenderer.render(document);
    }
}

