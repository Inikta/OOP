import groovy.lang.GroovyObjectSupport;
import enums.CompileEnum;
import enums.TestEnum;

import java.util.Date;

public class Task extends GroovyObjectSupport {

    private final int id;
    private Date deadline;

    private CompileEnum compileState;
    private TestEnum testingState;

    private boolean autoDocumentable;
    private boolean executable;

    public Task(int id, Date deadline) {
        this.id = id;
        this.deadline = deadline;

        compileState = CompileEnum.NONE;
        testingState = TestEnum.NONE;
        autoDocumentable = false;
        executable = false;
    }

    public int getId() {
        return id;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public CompileEnum getCompileState() {
        return compileState;
    }

    public void setCompileState(CompileEnum compileState) {
        this.compileState = compileState;
    }

    public TestEnum getTestingState() {
        return testingState;
    }

    public void setTestingState(TestEnum testingState) {
        this.testingState = testingState;
    }

    public boolean isAutoDocumentable() {
        return autoDocumentable;
    }

    public void setAutoDocumentable(boolean autoDocumentable) {
        this.autoDocumentable = autoDocumentable;
    }

    public boolean isExecutable() {
        return executable;
    }

    public void setExecutable(boolean executable) {
        this.executable = executable;
    }
}
