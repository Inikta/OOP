package ru.nsu.nikita;

public class Subject {
    public String teacher;
    public String subject;
    public int grade;

    public Subject(String personName, String subjectName, int number) throws IllegalArgumentException {     //constructor for one subject
        this.teacher = personName;
        this.subject = subjectName;
        this.grade = number;                //0 and 1 are for pass-or-fail system and 2,3,4,5 for differential
        if (grade < 0 || grade > 5) {
            throw new IllegalArgumentException();
        }
    }

    public String subjectToString() {       //string constructor for subject; format: "Subject | Grade | Teacher\0"
        String delim = " | ";
        String result = subject + delim;
        if (grade < 2) {
            if (grade == 1) {
                result += "Passed";
            } else {
                result += "Failed";
            }
        } else {
            result += ((Integer) grade).toString();
        }
        result += delim;
        result += teacher;

        return result;
    }
}
