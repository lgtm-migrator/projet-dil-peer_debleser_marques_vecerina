package Template;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
                    "   <title>{{ site.titre }} | {{ page.titre }}</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "   {% include menu.html }\n" +
                    "   {{ content }}\n" +
                    "</body>\n" +
                    "</html>\n";

    public TemplateManager(String name){
        siteName = name;
    }

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

    public String handlebarParse(Object o, String fileName){
        TemplateLoader loader = new ClassPathTemplateLoader();
        loader.setPrefix("/template");
        loader.setSuffix(".html");
        Handlebars handlebars = new Handlebars(loader);

        try {
            Template template = handlebars.compile(fileName);
            return template.apply(o);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
