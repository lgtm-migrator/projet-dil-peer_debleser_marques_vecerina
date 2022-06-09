import SubCommands.*;
import Version.AppVersionProvider;
import picocli.CommandLine;

import java.util.Properties;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;

@Command(
        name = "statique",
        description = "A brand new static site generator.",
        subcommands = {Init.class, Clean.class, Build.class, Serve.class},
        versionProvider = AppVersionProvider.class)

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