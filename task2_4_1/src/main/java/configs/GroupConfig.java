package configs;

import groovy.lang.GroovyObjectSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupConfig extends GroovyObjectSupport {
    private String groupName;
    private List<StudentConfig> studentConfigs;
}
