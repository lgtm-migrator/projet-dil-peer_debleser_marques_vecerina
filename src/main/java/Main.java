import SubCommands.SubCommands;
import picocli.CommandLine;

import java.util.concurrent.Callable;

public class Main implements Callable<Integer> {

    @CommandLine.Command(name = "Subcommands", subcommands = SubCommands.class)
    public static void main(String... args) {
       int sc = new CommandLine(new SubCommands()).execute(args);
       System.exit(sc);
    }


    @Override
    public Integer call() throws Exception {
        return null;
    }

}
