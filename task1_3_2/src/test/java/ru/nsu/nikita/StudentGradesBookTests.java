package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentGradesBookTests {

    public String name;
    public String faculty;
    public String group;
    public int diplomaGrade;
    public int semAmount;
    public String[][] subjects;
    public int[][] grades;
    public String[][] teachers;
    public double[] minMoney;
    public double[] maxMoney;
    public Semester[] testSem;

    @BeforeEach
    void initThings() throws IncorrectTableException {
        name = "Surname_Name_Pat(Mat)ronym";
        faculty = "FIT";
        group = "ABC123.4";
        diplomaGrade = 5;
        semAmount = 1;
        subjects = new String[][]{{"Math", "Prog", "English", "Chilling Science"}};
        grades = new int[][]{{3, 4, 5, 1}};
        teachers = new String[][]{{"Abra", "Ka", "Da", "Bruh"}};
        minMoney = new double[]{3000};
        maxMoney = new double[]{7500};
        testSem = new Semester[]{new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0])};
    }

    @Test
    void illegalStudentInformationTests() {
        //fine books
        Assertions.assertDoesNotThrow(() -> new StudentGradesBook(name, faculty, group, diplomaGrade, testSem));
        Assertions.assertDoesNotThrow(() -> new StudentGradesBook(
                name, faculty, group, diplomaGrade, semAmount,
                subjects, grades, teachers, minMoney, maxMoney));

        diplomaGrade = 6;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StudentGradesBook(name, faculty, group, diplomaGrade, testSem)); //wrong diplomaGrade value (6))
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new StudentGradesBook(
                        name, faculty, group, diplomaGrade, semAmount, //wrong diplomaGrade value (6)
                        subjects, grades, teachers, minMoney, maxMoney));
    }

    @Test
    void incorrectDataTableTests() throws IncorrectTableException {
        subjects = new String[][]{{"Math", "Prog", "English"}};
        Assertions.assertThrows(IncorrectTableException.class,
                () -> new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]));
    }

    @Test
    void redDiplomaStatusTests() throws IncorrectTableException {
        int[] gradesPack0 = {5, 5, 3, 5};       //satisfactoryGrade criteria
        grades[0] = gradesPack0;
        Semester initSem = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem;
        StudentGradesBook book0 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertFalse(book0.redDiplomaStatus());

        int[] gradesPack1 = {5, 5, 5, 5};       //all conditions passed
        grades[0] = gradesPack1;
        initSem = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem;
        StudentGradesBook book1 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertTrue(book1.redDiplomaStatus());

        int[] gradesPack2 = {4, 5, 5, 5};       //all conditions passed
        grades[0] = gradesPack2;
        initSem = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem;
        StudentGradesBook book2 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertTrue(book2.redDiplomaStatus());

        int[] gradesPack3 = {4, 4, 5, 5};       //gradesPercentage criteria
        grades[0] = gradesPack3;
        initSem = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem;
        StudentGradesBook book3 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertFalse(book3.redDiplomaStatus());

        int[] gradesPack4 = {5, 5, 5, 5};       //diplomaGrade criteria
        grades[0] = gradesPack4;
        diplomaGrade = 4;
        initSem = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem;
        StudentGradesBook book4 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertFalse(book4.redDiplomaStatus());
    }

    @Test
    void averageGradeTests() throws IncorrectTableException {
        //grades = [3, 4, 5, 1] -> average: (3+4+5+5)/4 = 4.25
        grades = new int[2][4];

        StudentGradesBook book0 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertEquals(4.25, book0.getAverageGrade());

        int[] gradesPack1 = {3, 4, 5, 0};       //with fail -> average: (3+4+5+2)/4 = 3.5
        grades[0] = gradesPack1;
        diplomaGrade = 4;
        Semester initSem = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem;
        StudentGradesBook book1 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertEquals(3.5, book1.getAverageGrade());

        testSem = new Semester[2];
        int[] gradesPack21 = {5, 4, 5, 3};       //average: (5+4+5+3+3+4+5+2)/8 = 3.875
        int[] gradesPack22 = {3, 4, 5, 0};
        grades[0] = gradesPack21;
        grades[1] = gradesPack22;
        Semester initSem1 = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem1;
        Semester initSem2 = new Semester(subjects[0], grades[1], teachers[0], minMoney[0], maxMoney[0]);
        testSem[1] = initSem2;
        StudentGradesBook book2 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertEquals(4.25, book2.getAverageGrade(1));     //1st semester average
        Assertions.assertEquals(3.875, book2.getAverageGrade());                //global average

    }

    @Test
    void scholarshipTests() throws IncorrectTableException {
        //grades = [3, 4, 5, 1]
        StudentGradesBook book0 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertEquals(0, book0.getScholarship());

        testSem = new Semester[2];
        grades = new int[2][4];
        int[] gradesPack11 = {5, 4, 5, 5};
        int[] gradesPack12 = {4, 4, 4, 1};
        grades[0] = gradesPack11;
        grades[1] = gradesPack12;
        Semester initSem1 = new Semester(subjects[0], grades[0], teachers[0], minMoney[0], maxMoney[0]);
        testSem[0] = initSem1;
        Semester initSem2 = new Semester(subjects[0], grades[1], teachers[0], minMoney[0], maxMoney[0]);
        testSem[1] = initSem2;
        StudentGradesBook book1 = new StudentGradesBook(name, faculty, group, diplomaGrade, testSem);
        Assertions.assertEquals(maxMoney[0], book1.getScholarship(1));     //1st semester scholarship
        Assertions.assertEquals(minMoney[0], book1.getScholarship());                //last semester scholarship
    }
}
