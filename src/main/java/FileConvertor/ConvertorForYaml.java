package FileConvertor;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class ConvertorForYaml {
    public static Map<String, Object> readYaml(String path) throws IOException{
        Map<String, Object> data = null;
        try{
            FileInputStream fis = new FileInputStream(path);
            Yaml yaml = new Yaml();
            data = yaml.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }
}
