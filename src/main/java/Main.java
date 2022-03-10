import picocli.CommandLine;

import java.util.concurrent.Callable;

public class Main implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return null;
    }

    public static void main(String... args) {
       // int exitCode = new CommandLine(new CheckSum()).execute(args);
       // System.exit(exitCode);
    }
}
