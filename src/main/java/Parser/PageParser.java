package java.Parser;

import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import java.FileConvertor.ConvertorForYaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Class to parse the page.md
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
public class PageParser {

    /**
     * Method to parse a file that has a separation
     * @param path of the file
     * @param config for the configuration in yaml files
     * @return the content of the page.md in String
     * @throws IOException
     */
    public static String parse(Path path, Map<String, Object> config, Template template) throws IOException {

        String[] contents = Files.readString(path).split("---");
        if(contents.length != 2)
            throw new RuntimeException("The page is malformed!");

        Map<String, Object> page = ConvertorForYaml.readYaml(contents[0]);
        String content = contents[1];

        Context context =
                Context.newBuilder(new Object())
                        .combine("site", config)
                        .combine("page", page)
                        .combine("content", content)
                        .resolver(MapValueResolver.INSTANCE, JavaBeanValueResolver.INSTANCE)
                        .build();

        return template.apply(context);
    }
}
