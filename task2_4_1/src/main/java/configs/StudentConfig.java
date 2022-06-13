package configs;

import groovy.lang.GroovyObjectSupport;
import lombok.Data;

import java.util.List;

@Data
public class StudentConfig extends GroovyObjectSupport {
    private String nickname;
    private String name;
    private String surname;
    private String gitAddress;
    private String branch;

    private List<TaskConfig> taskConfigs;
    private List<LessonConfig> attendance;

    @Override
    public String toString() {
        return "Student{" +
                "nickname='" + nickname + '\n' +
                ", name='" + name + '\n' +
                ", surname='" + surname + '\n' +
                ", gitAddress='" + gitAddress + '\n' +
                ", tasks=" + taskConfigs +
                '}';
    }
}
