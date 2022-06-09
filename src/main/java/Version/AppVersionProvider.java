package Version;

import picocli.CommandLine;

import java.util.Properties;

public class AppVersionProvider implements CommandLine.IVersionProvider {

    @Override
    public String[] getVersion() {
        final Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("statique.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[]{"Statique_v" + properties.getProperty("version")};
    }
}
