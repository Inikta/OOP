package configs;

import groovy.lang.GroovyObjectSupport;
import lombok.Data;

import java.util.Date;

@Data
public class LessonConfig extends GroovyObjectSupport {
    private Date date;
    private int mark;

    @Override
    public String toString() {
        return "Lesson{" +
                "date=" + date +
                ", mark=" + mark +
                '}';
    }
}
