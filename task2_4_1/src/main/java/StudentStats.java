import groovy.lang.GroovyObjectSupport;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class StudentStats extends GroovyObjectSupport {
    private Map<Task, Integer> taskStateMap;
    private Map<Date, Integer> attendanceMap;

    public StudentStats() {
        this.taskStateMap = new HashMap<>();
        this.attendanceMap = new HashMap<>();
    }

    public StudentStats(List<Task> taskList, List<Date> dates) {
        this.taskStateMap = new HashMap<>();
        this.attendanceMap = new HashMap<>();

        for (Task task : taskList) {
            taskStateMap.put(task, 0);
        }

        for (Date date : dates) {
            attendanceMap.put(date, 0);
        }
    }

    public StudentStats(Map<Task, Integer> taskStateMap, Map<Date, Integer> attendanceMap) {
        this.taskStateMap = taskStateMap;
        this.attendanceMap = attendanceMap;
    }

    public void addTask(Task task) {
        taskStateMap.put(task, 0);
    }

    public void removeTask(Task task) {
        taskStateMap.remove(task);
    }

    public Task getTask(int taskID) {
        Task filtered;
        try {
            filtered = taskStateMap.keySet().stream().filter(task -> task.getId() == taskID).toList().get(0);
        } catch (IndexOutOfBoundsException exc) {
            throw new IllegalArgumentException("No task with such ID: " + taskID);
        }
        return filtered;
    }

    public Map<Task, Integer> getTaskStateMap() {
        return new HashMap<>(taskStateMap);
    }

    public void setTaskStateMap(Map<Task, Integer> taskStateMap) {
        this.taskStateMap = taskStateMap;
    }

    public Map<Date, Integer> getAttendanceMap() {
        return new HashMap<>(attendanceMap);
    }

    public void setAttendanceMap(Map<Date, Integer> attendanceMap) {
        this.attendanceMap = attendanceMap;
    }
}
