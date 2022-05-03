package Template;

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
                    new OutputStreamWriter(new FileOutputStream(siteName + "/" + "menu.html"), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Create layout.html
        try{
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(siteName + "/" + "layout.html"), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
