package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Semester {
    private final ArrayList<Subject> subjects;
    public double minScholarship;
    public double maxScholarship;

    /*
     * constructor for ready Subject array
     */

    public Semester(Subject[] subs, double min, double max) {
        this.subjects = (ArrayList<Subject>) Arrays.stream(subs).toList();
        setScholarshipBounds(min, max);
    }

    /*
     * constructor for separated data
     * throws exception, if data arrays are not the same size
     * sets scholarship bounds for this semester
     */

    public Semester(String[] subjectsStr, String[] teachers, int[] grades,
                    double min, double max) throws IncorrectTableException, NullPointerException {
        if (subjectsStr.length != grades.length || subjectsStr.length != teachers.length) {
            throw new IncorrectTableException("Table array or its data is constructed incorrectly.");
        }
        this.subjects = new ArrayList<>();
        for (int i = 0; i < subjectsStr.length; i++) {
            Subject a = new Subject(teachers[i], subjectsStr[i], grades[i]);
            this.subjects.add(a);
        }

        setScholarshipBounds(min, max);
    }

    /*
     * add new subject to the semester
     */

    public void add(String subject, String teacher, int grade) throws NullPointerException {
        subjects.add(new Subject(teacher, subject, grade));
    }

    /*
     * add subject to the semester
     */

    public void remove(String subject) {
        subjects.removeIf(subject1 -> (Objects.equals(subject1.subject, subject)));
    }

    /*
     * set subject grade
     */

    public void setGrade(String subject, int newGrade) throws NoSuchElementException {
        int ind = 0;
        for (Subject sub : subjects) {
            if (Objects.equals(sub.subject, subject)) {
                Subject tempSubject = subjects.get(ind);
                tempSubject.setGrade(newGrade);
                subjects.set(ind, tempSubject);
                break;
            }
            ind++;
        }
        throw new NoSuchElementException();
    }

    // * sets scholarship bounds for this semester

    public void setScholarshipBounds(double min, double max) {
        this.minScholarship = min;
        this.maxScholarship = max;
    }

    /*
     * string representation of this semester table
     */

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        for (Subject subject : subjects) {
            table.append(subject.toString()).append("\n");
        }
        return table.toString();
    }

    /*
     * get subjects string array
     */

    public String[] getSubjects() {
        String[] res = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            res[i] = subjects.get(i).getSubject();
        }

        return res;
    }

    /*
     * get teachers string array
     */

    public String[] getTeachers() {
        String[] res = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            res[i] = subjects.get(i).getTeacher();
        }

        return res;
    }

    /*
     * get grades int array
     */

    public int[] getGrades() {
        int[] res = new int[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            res[i] = subjects.get(i).getGrade();
        }

        return res;
    }

    /*
     * compute average grade of the semester
     * translates "pass" to 5 and "fail" to 2
     * if subject has not been graded yet, ignores and not counts it
     */

    public float getAverage() {
        int subjectAmount = 0;
        int sumGrades = 0;
        for (Subject sub : subjects) {
            if (sub.grade == -1) {
                continue;
            }
            subjectAmount++;
            if (sub.grade > 1) {
                sumGrades += sub.grade;
            } else if (sub.grade == 0) {
                sumGrades += 2;
            } else {
                sumGrades += 5;
            }
        }

        return (float) sumGrades / (float) subjectAmount;
    }

    /*
     * count percentage for each grade
     */

    public float[] countGradesPercentage() {
        float[] gradesPercentage = new float[4];
        int[] gradeCounts = {0, 0, 0, 0};
        int gradesAmount = subjects.size();
        for (Subject sub : subjects) {
            switch (sub.grade) {
                case 0, 2 -> gradeCounts[0]++;
                case 3 -> gradeCounts[1]++;
                case 4 -> gradeCounts[2]++;
                case 1, 5 -> gradeCounts[3]++;
            }
        }
        for (int i = 0; i < gradeCounts.length; i++) {      //compute percentage for each grade
            gradesPercentage[i] = (float) gradeCounts[i] / (float) gradesAmount;
        }

        return gradesPercentage;
    }

    /*
     * return scholarship of this semester
     * if there are grades <= 3 or fail, then there is no scholarship :(
     * if there are no satisfactory grades and good grades >= 2, then return minimal scholarship
     * if there are no satisfactory grades and good grades < 2, then return maximal scholarship
     */

    public double getScholarship() {
        int goodGrades = 0;
        for (Subject sub : subjects) {
            if (sub.grade < 4 && sub.grade != 1) {
                return 0;
            } else if (sub.grade == 4) {
                goodGrades++;
                if (goodGrades > 2) {
                    return minScholarship;
                }
            }
        }

        return maxScholarship;
    }
}
