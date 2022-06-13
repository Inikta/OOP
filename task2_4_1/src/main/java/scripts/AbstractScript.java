package scripts;

import groovy.lang.GroovyShell;
import picocli.CommandLine;

import java.util.concurrent.Callable;

public abstract class AbstractScript implements Callable<Integer> {

    @CommandLine.Parameters(index = "0", arity = "1")
    protected String group;

    protected static final String scriptsFolder = "src\\main\\groovy\\";
    protected String scriptName;

    protected GroovyShell shell;

    public AbstractScript(GroovyShell shell) {
        this.shell = shell;
    }
}
