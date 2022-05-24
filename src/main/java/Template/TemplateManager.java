package Template;

import FileConvertor.ConvertorToHtml;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Class to generate template for the site
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
public class TemplateManager {

    private static final String templateDirName = "templates";
    private final String siteName;

    private static final String templateMenu =
            "<ul>\n " +
                    "   <li><a href=\"/index.html\">home</a></li>\n" +
                    "   <li><a href=\"/content/page.html\">page</a></li>\n" +
                    "</ul>\n";

    private static final String templateLayout =
            "<html lang=\"en\"\n" +
                    "<head>\n" +
                    "   <meta charset=\"utf-8\">\n" +
                    "   <title>{{ site.title }} | {{ page.title }}</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "   {% include menu.html }\n" +
                    "   {{ content }}\n" +
                    "</body>\n" +
                    "</html>\n";

    public TemplateManager(String siteName) {
        this.siteName = siteName;
    }

    /**
     * Method to create the template files
     */
    public void createTemplateFiles(){
        File templateDir = new File("/" + templateDirName);
        if(templateDir.mkdir())
            System.out.println("Template directory created with succes!");
        else
            System.out.println("Template directory exist and will be overwrite!");

        //Create menu.html
        try{
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(siteName + "/" + "menu.html"),
                            StandardCharsets.UTF_8));

            bw.write(templateMenu);
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create layout.html
        try{
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(siteName + "/" + "layout.html"),
                            StandardCharsets.UTF_8));

            bw.write(templateLayout);
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to parse the template files
     * @return content of template
     */
    public static Template handlebarParse() throws IOException {
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/template");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);
        handlebars.registerHelper("md", new ConvertorToHtml());

        return handlebars.compile("layout");
    }
}
