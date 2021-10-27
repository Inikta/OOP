package ru.nsu.nikita;

public class Subject {
    public String teacher;
    public String subject;
    public int grade;

    /*
     * Constructor for one subject.
     * 0 and 1 are for pass-or-fail system and 2,3,4,5 for differential.
     * -1 means nothing was passed
     */

    public Subject(String personName, String subjectName, int number) throws IllegalArgumentException {
        this.teacher = personName;
        this.subject = subjectName;
        this.grade = number;
        if (grade < -1 || grade > 5) {
            throw new IllegalArgumentException();
        }
    }

    /* string constructor for subject
     * format: "Subject | Grade | Teacher\0"
     */

    @Override
    public String toString() {
        String delim = " | ";
        String result = subject + delim;
        if (grade < 2) {
            if (grade == -1) {
                result += "None";
            }
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


    public String getSubject() {
        return subject;
    }

    /*
     * pack of getters
     */

    public String getTeacher() {
        return teacher;
    }

    public int getGrade() {
        return grade;
    }

    // * set new grade for the subject

    public void setGrade(int newGrade) {
        grade = newGrade;
    }
}
