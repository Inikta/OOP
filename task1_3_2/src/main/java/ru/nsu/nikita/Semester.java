package ru.nsu.nikita;

public class Semester {
    private final Subject[] subjects;
    public double minScholarship;
    public double maxScholarship;

    public Semester(Subject[] subs, double min, double max) {       //constructor for ready Subject array
        this.subjects = subs;
        setScholarshipBounds(min, max);
    }

    public Semester(String[] subjectsStr, int[] grades, String[] teachers,          //constructor for separated data
                    double min, double max) throws IncorrectTableException {
        this.subjects = new Subject[Math.max(Math.max(subjectsStr.length, grades.length), teachers.length)];
        for (int i = 0; i < subjects.length; i++) {
            try {                                                           //checks if arrays are of the same size or grades are correct
                this.subjects[i] = new Subject(teachers[i], subjectsStr[i], grades[i]);
            } catch (IndexOutOfBoundsException | IllegalArgumentException badData) {               //if not throw exception
                throw new IncorrectTableException("Table array or its data is constructed incorrectly.");
            }
        }

        setScholarshipBounds(min, max);         //set scholarship bounds for this semester
    }

    public void setScholarshipBounds(double min, double max) {
        this.minScholarship = min;
        this.maxScholarship = max;
    }

    public String[] semesterToStringArray() {           //string array representation of this semester table
        String[] table = new String[subjects.length];
        for (int i = 0; i < subjects.length; i++) {
            table[i] = subjects[i].subjectToString();
        }
        return table;
    }

    public float getAverage() {         //compute average grade of the semester
        int subjectAmount = 0;
        int sumGrades = 0;
        for (Subject sub : subjects) {
            subjectAmount++;
            if (sub.grade > 1) {        //differential grade
                sumGrades += sub.grade;
            } else if (sub.grade == 0) {  //pass-or-fail grade
                sumGrades += 2;
            } else {
                sumGrades += 5;
            }
        }

        return (float) sumGrades / (float) subjectAmount;
    }

    public float[] countGradesPercentage() {            //count percentage for each grade
        float[] gradesPercentage = new float[4];
        int[] gradeCounts = {0, 0, 0, 0};
        int gradesAmount = subjects.length;
        for (Subject sub : subjects) {                  //counters for each grade
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

    public double getScholarship() {        //return scholarship of this semester
        int goodGrades = 0;
        double scholarship = 0;
        for (Subject sub : subjects) {
            if (sub.grade < 4 && sub.grade != 1) {            //if there are grades <= 3 or fail, then there is no scholarship :(
                scholarship = 0;
                return scholarship;
            } else if (sub.grade == 4) {     //if there are no satisfactory grades and good grades >= 2, then return minimal scholarship
                goodGrades++;
                if (goodGrades > 2) {
                    scholarship = minScholarship;
                    return scholarship;
                }
            }
        }
        if (goodGrades <= 2) {          //if there are no satisfactory grades and good grades < 2, then return maximal scholarship
            scholarship = maxScholarship;
        }

        return scholarship;
    }
}
