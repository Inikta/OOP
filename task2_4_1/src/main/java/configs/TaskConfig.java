package configs;

import enums.CompileEnum;
import enums.DocEnum;
import enums.TestEnum;
import groovy.lang.GroovyObjectSupport;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskConfig extends GroovyObjectSupport {
    private int id;
    private String name;
    private Date deadline;

    private double grade;
    private CompileEnum compileStatus;
    private DocEnum docStatus;
    private List<TestEnum> testsStatus;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadline=" + deadline +
                ", grade=" + grade +
                ", compileStatus=" + compileStatus +
                ", docStatus=" + docStatus +
                ", testsStatus=" + testsStatus +
                '}';
    }
}
