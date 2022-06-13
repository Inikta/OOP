import configs.GroupConfig;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import org.codehaus.groovy.control.CompilerConfiguration;
import picocli.CommandLine;
import scripts.CompileScript;
import scripts.DocsScript;
import scripts.ReportScript;
import scripts.TestScript;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "dsl", mixinStandardHelpOptions = true,
        subcommands = {
                CompileScript.class,
                TestScript.class,
                DocsScript.class,
                ReportScript.class
        }
)
public class Main implements Callable<Integer> {
    private static CompilerConfiguration cc;
    private static GroovyShell sh;
    private static final String scriptsFolder = "src\\main\\groovy\\";

    public static void main(String[] args) throws IOException {
        cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName()); // благодаря этой настройке все создаваемые groovy скрипты будут наследоваться от DelegatingScript
        sh = new GroovyShell(Main.class.getClassLoader(), new Binding(), cc);

        DelegatingScript script = (DelegatingScript) sh.parse(new File(scriptsFolder + "config.groovy"));
        GroupConfig config = new GroupConfig(); // наш бин с конфигурацией
        script.setDelegate(config);
        System.out.println(config);
        //int exitCode = new CommandLine(new Main()).execute(args);
        //System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
