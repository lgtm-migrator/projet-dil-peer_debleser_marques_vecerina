package Template;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TemplateManager {

    private static final String templateDirName = "templates";
    private final String siteName;

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
