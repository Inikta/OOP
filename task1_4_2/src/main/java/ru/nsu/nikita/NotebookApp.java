package ru.nsu.nikita;

import picocli.CommandLine;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "notebook")

public class NotebookApp implements Callable<String[]> {

    @CommandLine.Option(names = {"-add"}, arity = "2")
    String[] addParameters;

    @CommandLine.Option(names = {"-rm"}, arity = "1")
    String[] removeParameters;

    @CommandLine.Option(names = {"-show"}, arity = "0..*")
    String[] showParameters;

    @Override
    public String[] call() throws Exception {

        return new String[0];
    }

    public static void main(String... args) throws IOException {
        String path = ".." + File.pathSeparator + "task1_4_2" + File.pathSeparator + "notes";
        Notebook notebook = new Notebook(path);

        int exitCode = new CommandLine(new NotebookApp()).execute(args);
        System.exit(exitCode);
    }

}
