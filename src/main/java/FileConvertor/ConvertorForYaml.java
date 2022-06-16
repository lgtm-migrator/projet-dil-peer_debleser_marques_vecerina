package java.FileConvertor;

import org.yaml.snakeyaml.Yaml;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * Class to convert yaml file
 * @author Dimitri De Bleser
 * @author André Marques Nora
 * @author Vincent Peer
 * @author Ivan Vecerina
 * @version 1.0
 */
public class ConvertorForYaml {

    public static final String CONFIG = "config.yaml";
    private static final Yaml yaml = new Yaml();

    /**
     * Method to parse content of yaml file
     * @param path of the file
     * @return content of the yaml in a map
     * @throws IOException
     */
    public static Map<String, Object> parseYaml(Path path) throws IOException{
        Map<String, Object> dataConfig = yaml.load(Files.readString(path.resolve(CONFIG)));
        return dataConfig;
    }

    /**
     * Method to read/load the content of yaml
     * @param file name of file
     * @return content in a map
     * @throws IOException
     */
    public static Map<String, Object> readYaml(String file) throws IOException{
        Map<String, Object> data = yaml.load(file);
        return data;
    }
}
