package com.example.ibrahiemmohammad.myapplication;

import com.example.ibrahiemmohammad.myapplication.school.Student;

/**
 * Created by Ibrahiem Mohammad on 5/10/2015.
 */
public class CurrentSelection {

    private static Student selectedStudent;

    public static Student getSelectedStudent() {
        return selectedStudent;
    }

    public static void setSelectedStudent(Student student) {
        selectedStudent = student;
    }

    private static String selectedGrade;

    public static String getSelectedGrade() {
        return selectedGrade;
    }

    public static void setSelectedGrade(int grade) {
        selectedGrade = Integer.toString(grade);
    }
}
