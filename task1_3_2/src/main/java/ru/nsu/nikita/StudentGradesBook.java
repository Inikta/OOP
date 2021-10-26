package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/*
 * consists of semesters (pages of book)
 * and different information about student (name, group, faculty, diploma)
 */

public class StudentGradesBook {
    private final String student;
    private final String faculty;
    private final String groupID;
    private final int diplomaGrade;

    private final ArrayList<Semester> semesters;

    /*
     * constructor for ready data
     * throws exception if diploma grade is wrong
     */

    public StudentGradesBook(String studentName, String faculty, String group,
                             int diplomaGrade, Semester[] semestersArray) throws IllegalArgumentException {
        this.student = studentName;
        this.faculty = faculty;
        this.groupID = group;
        this.diplomaGrade = diplomaGrade;
        if (diplomaGrade < 2 || diplomaGrade > 5) {
            throw new IllegalArgumentException();
        }
        this.semesters = new ArrayList<>();
        Collections.addAll(this.semesters, semestersArray);
    }

    /*
     * constructor for separated data
     * throws exception if diploma grade is wrong
     * throws exception if data table was built incorrectly
     * sets scholarships for each semester
     */

    public StudentGradesBook(String studentName, String faculty, String group, int diplomaGrade, int semAmount,
                             String[][] subjects, String[][] teachers, int[][] grades,
                             double[] minScholarships, double[] maxScholarships) throws IncorrectTableException {
        this.student = studentName;
        this.faculty = faculty;
        this.groupID = group;
        this.diplomaGrade = diplomaGrade;
        if (diplomaGrade < 2 || diplomaGrade > 5) {
            throw new IllegalArgumentException();
        }

        if (subjects.length != grades.length || subjects.length != teachers.length) {
            throw new IncorrectTableException("Table is constructed incorrectly.");
        }

        this.semesters = new ArrayList<>();
        for (int i = 0; i < semAmount; i++) {
            this.semesters.add(new Semester(subjects[i], teachers[i], grades[i],
                    minScholarships[i], maxScholarships[i]));
        }
    }

    /*
     * add subject to semNum semester
     */

    public void addSubject(int semNum, String subject, String teacher, int grade) {
        Semester currentSemester = semesters.get(semNum - 1);
        currentSemester.add(subject, teacher, grade);
        semesters.set(semNum - 1, currentSemester);
    }

    /*
     * add new semester to the end of book
     */

    public void addSemester(String[] subjects, String[] teachers, int[] grades,
                            double min, double max) throws IncorrectTableException {
        if (subjects.length != grades.length || subjects.length != teachers.length) {
            throw new IncorrectTableException("Table is constructed incorrectly.");
        }
        semesters.add(new Semester(subjects, teachers, grades, min, max));
    }

    /*
     * add new semester at semNum index
     */

    public void addSemester(int semNum, Semester newSem) throws IllegalArgumentException {
        if (semNum > semesters.size() + 1) {
            throw new IllegalArgumentException();
        }
        semesters.add(semNum - 1, newSem);
    }

    /*
     * remove subject in semester numbered as semNum
     */

    public void remove(int semNum, String subject) {
        Semester currentSemester = semesters.get(semNum - 1);
        currentSemester.remove(subject);
        semesters.set(semNum - 1, currentSemester);
    }

    /*
     * remove semester at index
     */

    public void remove(int semNum) {
        semesters.remove(semNum - 1);
    }

    /*
     * set grade for a subject in semNum semester
     */

    public void setGrade(int semNum, String subject, int newGrade) throws NoSuchElementException {
        Semester currentSemester = semesters.get(semNum - 1);
        currentSemester.setGrade(subject, newGrade);
        semesters.set(semNum - 1, currentSemester);
    }

    /*
     * get average grade of the semester
     */

    public float getAverageGrade(int semNum) {
        return semesters.get(semNum - 1).getAverage();
    }

    /*
     * get average grade of all studying
     */

    public float getAverageGrade() {
        float sum = 0;
        for (Semester sem : semesters) {
            sum += sem.getAverage();
        }

        return sum / (float) semesters.size();
    }

    /*
     * get scholarship of semNum semester
     */

    public double getScholarship(int semNum) {
        return semesters.get(semNum - 1).getScholarship();
    }

    /*
     * get scholarship of the last semester
     */

    public double getScholarship() {
        return semesters.get(semesters.size() - 1).getScholarship();
    }

    /*
     * get subjects array of string arrays for each semester
     */

    public String[][] getSubjects() {
        String[][] res = new String[semesters.size()][];
        for (int i = 0; i < semesters.size(); i++) {
            res[i] = semesters.get(i).getSubjects();
        }

        return res;
    }

    /*
     * get teachers array of string arrays for each semester
     */

    public String[][] getTeachers() {
        String[][] res = new String[semesters.size()][];
        for (int i = 0; i < semesters.size(); i++) {
            res[i] = semesters.get(i).getTeachers();
        }

        return res;
    }

    /*
     * get grades array of int arrays for each semester
     */

    public int[][] getGrades() {
        int[][] res = new int[semesters.size()][];
        for (int i = 0; i < semesters.size(); i++) {
            res[i] = semesters.get(i).getGrades();
        }

        return res;
    }

    /*
     * check all three red diploma criteria (see task description)
     */

    public boolean redDiplomaStatus() {
        float[] gradesCriteria = semesters.get(semesters.size() - 1).countGradesPercentage();

        boolean fineGradesCriteria = gradesCriteria[3] >= 0.75;

        boolean satisfactoryGradeCriteria = !(gradesCriteria[0] > 0) && !(gradesCriteria[1] > 0);

        return fineGradesCriteria && satisfactoryGradeCriteria && diplomaGrade == 5;
    }

    /*
     * count grades percentage for all of semesters
     */

    private float[][] countGradesPercentage() {
        float[][] gradesPercentage = new float[semesters.size()][4];
        for (int i = 0; i < semesters.size(); i++) {
            gradesPercentage[i] = semesters.get(i).countGradesPercentage();
        }

        return gradesPercentage;
    }

    /*
     * print the whole book
     */

    public void printBook() {
        System.out.println("Student: " + student);
        System.out.println("Faculty: " + faculty);
        System.out.println("Group: " + groupID);
        for (int i = 0; i < semesters.size(); i++) {
            printSemester(i);
            System.out.print("\n---------------------------------------------\n");
        }
        System.out.print("=============================================\n");
    }

    /*
     * print the chosen semester
     */

    public void printSemester(int semNum) {
        System.out.println("Semester: " + semNum);
        System.out.println(semesters.get(semNum).toString());
        System.out.println();
        System.out.println("Average of grades: " + semesters.get(semNum).getAverage());

    }
}
