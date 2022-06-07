import groovy.lang.GroovyObjectSupport;

import java.util.*;

public class StudentsGroup extends GroovyObjectSupport {
    private String name;
    private Map<Integer, Student> idMap;
    private List<Task> taskList;

    public StudentsGroup(String name) {
        this.name = name;
        this.idMap = new HashMap<>();
    }

    public StudentsGroup(String name, List<Student> students) {
        this.name = name;
        this.idMap = new HashMap<>();
        for (Student student : students) {
            idMap.put(student.getId(), student);
        }
    }

    //Adders
    public void addStudent(Student student) {
        idMap.put(student.getId(), student);
    }

    public void removeStudent(Student student) {
        idMap.remove(student.getId());
    }

    //Removers
    public void removeStudent(int studentID) {
        idMap.remove(studentID);
    }

    //Getters
    public Student getStudentByID(int studentID) {
        return idMap.get(studentID);
    }

    public Student getStudentByNickname(String nickname) {
        return getStudentsByNickname(nickname).get(0);
    }

    public Student getStudentByName(String initial) {
        return getStudentsByName(initial).get(0);
    }

    public List<Student> getStudentsByName(String initial) {
        return idMap.values().stream().filter(student -> Arrays.stream(student.getFullName()).toList().contains(initial)).toList();
    }

    public List<Student> getStudentsByNickname(String nickname) {
        return idMap.values().stream().filter(student -> student.getNickname().contains(nickname)).toList();
    }

    //===============================
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Student> getIdMap() {
        return idMap;
    }

    public void setIdMap(Map<Integer, Student> idMap) {
        this.idMap = idMap;
    }
}
