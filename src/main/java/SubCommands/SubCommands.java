package SubCommands;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "websiteGen")
public class SubCommands implements Callable<Integer> {

    @Command(name = "new", description = "Create New Website")
    public Integer myNew() {
        return 0;
    }

    @Command(name = "clean", description = "Clean Website")
    public Integer clean() {
        return 0;
    }

    @Command(name = "build", description = "Build Website")
    public Integer build() {
        return 0;
    }

    @Command(name = "serve", description = "Serve Website")
    public Integer serve() {
        return 0;
    }


    @Override
    public Integer call() throws Exception {
        return null;
    }
}
