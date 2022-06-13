package scripts;

import groovy.lang.GroovyShell;
import picocli.CommandLine;

@CommandLine.Command(name = "gen-doc")
public class DocsScript extends AbstractScript {
    public DocsScript(GroovyShell shell) {
        super(shell);
    }

    @Override
    public Integer call() throws Exception {
        return null;
    }
}
