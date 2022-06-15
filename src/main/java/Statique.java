import SubCommands.*;
import picocli.CommandLine;

import java.util.Properties;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(
        name = "statique",
        description = "A brand new static site generator.",
        subcommands = {Init.class, Clean.class, Build.class, Serve.class},
        versionProvider = AppVersionProvider.class)

/**
 *
 * @author De Bleser Dimitri
 * @author Vecerina Ivan
 * @author Peer Vincent
 * @author Marques Nora Andre
 * @version 1.0
 */
public class Statique implements Callable<Integer> {

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true, description = "version of the static site generator")
    private static boolean versionCMD;

    public static void main(String... args) {
        int exitCode = new CommandLine(new Statique()).execute(args);
        if (exitCode != 0) {
            System.exit(exitCode);
        }
    }

    @Override
    public Integer call() {
        CommandLine.usage(this, System.out);

        return 0;
    }
}

/**
 * @author De Bleser Dimitri
 * @author Vecerina Ivan
 * @author Peer Vincent
 * @author Marques Nora Andre
 * @version 1.0
 */
class AppVersionProvider implements CommandLine.IVersionProvider {

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