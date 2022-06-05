import static enums.CommandsEnum.*;

import enums.CommandsEnum;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import org.codehaus.groovy.control.CompilerConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Path mainPath = Path.of("../groovy/main.groovy").toAbsolutePath().normalize();

    public static void main(String[] args) throws IOException {
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName()); // благодаря этой настройке все создаваемые groovy скрипты будут наследоваться от DelegatingScript
        GroovyShell sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);

        Scanner inputScanner = new Scanner(System.in);
        CommandsEnum command = INIT;

        while (command != EXIT) {
            command = parseInput(inputScanner.nextLine());

            DelegatingScript script = (DelegatingScript)sh.parse(mainPath.toFile());
            script.setDelegate(inputScanner.nextLine());
            script.run();
            System.out.println(config.toString());
        }
    }

    private static CommandsEnum parseInput(String input) {
        List<String> args = Arrays.stream(input.trim().split(" ")).toList();
        switch (args.get(0)) {
            case "init" -> {

            }
            case "help" -> {

            }
            case "create-table" -> {

            }
            case "add-student" -> {

            }
            case "add-task" -> {

            }
            default -> {
                System.out.println("No such command!");
            }
        }
    }
}
