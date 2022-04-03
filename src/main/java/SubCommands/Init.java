package SubCommands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "init", description = "Initialize a static site directory")
public class Init implements Callable<Integer> {

    @Parameters(paramLabel = "SITE", description = "The site to build")
    public Path site;

    @Override public Integer call() throws URISyntaxException, IOException {
        File dir = site.toFile();
        if (!dir.exists() && dir.mkdir())
            throw new IOException("Can't create directory");

        // Getting details
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter title");
        String title = scanner.nextLine();

        System.out.println("Enter description : ");
        String description = scanner.nextLine();

        System.out.println("Enter domain : ");
        String domain = scanner.nextLine();

        // Writing config file
        File config = new File(dir, "config.yaml");
        OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(config), StandardCharsets.UTF_8);
        String toWrite = "title: " + title + "\n description: " + description + "\n domain: " + domain;
        out.write(toWrite);
        out.close();

        // Writing index file
        File index = new File(dir, "index.md");
        out = new OutputStreamWriter(new FileOutputStream(index), StandardCharsets.UTF_8);
        out.write("#" + title + "\n ## description \n" + description);
        out.close();
        return 0;
    }

}
