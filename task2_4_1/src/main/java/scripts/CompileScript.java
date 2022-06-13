package scripts;

import configs.GroupConfig;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import picocli.CommandLine;
import java.io.File;

@CommandLine.Command(name = "compile")
public class CompileScript extends AbstractScript {

    @CommandLine.Parameters(index = "1", arity = "1")
    private String nickname;

    @CommandLine.Parameters(index = "2", arity = "1")
    private String taskName;

    public CompileScript(GroovyShell shell) {
        super(shell);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Compiling...");

        scriptName = "compile-single";

        DelegatingScript script = (DelegatingScript) shell.parse(new File(scriptsFolder + scriptName));
        GroupConfig config = new GroupConfig(); // наш бин с конфигурацией
        script.setDelegate(config);

        script.run();

        return 0;
    }
}
