package Parser;

import FileConvertor.ConvertorForYaml;
import com.github.jknack.handlebars.Context;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.context.JavaBeanValueResolver;
import com.github.jknack.handlebars.context.MapValueResolver;
import com.github.jknack.handlebars.io.FileTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PageParser {


    public static String parse(Path path, Map<String, Object> config) throws IOException {
        Template template = new Template();

        String[] contents = Files.readString(path).split("{---}");
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
