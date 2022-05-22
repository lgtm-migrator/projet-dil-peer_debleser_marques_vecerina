package FileConvertor;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ConvertorForYaml {

    private static final Yaml yaml = new Yaml();

    public static Map<String, Object> parseYaml(Path path) throws IOException{
        Map<String, Object> dataConfig = yaml.load(Files.readString(path.resolve("config.yaml")));
        return dataConfig;
    }

    public static Map<String, Object> readYaml(String file) throws IOException{
        Map<String, Object> data = yaml.load(file);
        return data;
    }
}
