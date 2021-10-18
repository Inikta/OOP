package ru.nsu.nikita;

public class StudentGradesBook {        //consists of semesters (pages of book) and different information about student
    private final String student;
    private final String faculty;
    private final String groupID;
    private final int diplomaGrade;

    private final Semester[] semesters;

    public StudentGradesBook(String studentName, String faculty, String group,  //constructor for ready data
                             int diplomaGrade, Semester[] semestersArray) throws IllegalArgumentException {
        this.student = studentName;
        this.faculty = faculty;
        this.groupID = group;
        this.diplomaGrade = diplomaGrade;
        if (diplomaGrade < 2 || diplomaGrade > 5) {
            throw new IllegalArgumentException();
        }
        this.semesters = semestersArray;
    }

    public StudentGradesBook(String studentName, String faculty, String group, int diplomaGrade, int semAmount,     //constructor for separated data
                             String[][] subjects, int[][] grades, String[][] teachers,
                             double[] minScholarships, double[] maxScholarships) throws IncorrectTableException, IllegalArgumentException {
        this.student = studentName;
        this.faculty = faculty;
        this.groupID = group;
        this.diplomaGrade = diplomaGrade;
        if (diplomaGrade < 2 || diplomaGrade > 5) {
            throw new IllegalArgumentException();
        }
        this.semesters = new Semester[semAmount];
        for (int i = 0; i < semAmount; i++) {
            try {
                this.semesters[i] = new Semester(subjects[i], grades[i], teachers[i],   //throw exception if data is incorrect
                        minScholarships[i], maxScholarships[i]);
            } catch (IncorrectTableException incorrectTable) {
                throw new IncorrectTableException("Table is constructed incorrectly.");
            }
        }
    }

    public float getAverageGrade(int semNum) {  //get average grade of the semester
        return semesters[semNum - 1].getAverage();
    }

    public float getAverageGrade() {    //compute average grade for all of semesters
        float sum = 0;
        for (Semester sem : semesters) {
            sum += sem.getAverage();
        }

        return sum / (float) semesters.length;
    }

    public double getScholarship(int semNum) {   //get scholarship for semester
        return semesters[semNum - 1].getScholarship();
    }   //for any semester

    public double getScholarship() {                                //the last one by default
        return semesters[semesters.length - 1].getScholarship();
    }


    public boolean redDiplomaStatus() {        //check red diploma criteria
        float[] gradesCriteria = semesters[semesters.length - 1].countGradesPercentage();

        boolean fineGradesCriteria = gradesCriteria[3] >= 0.75;

        boolean satisfactoryGradeCriteria = !(gradesCriteria[0] > 0) && !(gradesCriteria[1] > 0);

        return fineGradesCriteria && satisfactoryGradeCriteria && diplomaGrade == 5;
    }

    private float[][] countGradesPercentage() {     //count grades percentage for all of semesters
        float[][] gradesPercentage = new float[semesters.length][4];
        for (int i = 0; i < semesters.length; i++) {
            gradesPercentage[i] = semesters[i].countGradesPercentage();
        }

        return gradesPercentage;
    }

    public void printBook() {           //print whole book
        System.out.println("Student: " + student);
        System.out.println("Faculty: " + faculty);
        System.out.println("Group: " + groupID);
        for (int i = 0; i < semesters.length; i++) {
            printSemester(i);
        }
    }

    public void printSemester(int semNum) {     //print page (chosen semester) of the book
        System.out.println("Semester: " + (semNum + 1));
        String[] table = semesters[semNum].semesterToStringArray();
        for (String line : table) {
            System.out.println(line);
        }
        System.out.println();
        System.out.println("Average of grades: " + semesters[semNum].getAverage());

    }
}
