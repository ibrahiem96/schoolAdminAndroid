package com.example.ibrahiemmohammad.myapplication;

import com.example.ibrahiemmohammad.myapplication.school.Student;

/**
 * Created by Ibrahiem Mohammad on 5/30/2015.
 */
public class CheckedStudent {
    private Student selectedStudent;

    public  Student getSelectedStudent() {
        return selectedStudent;
    }

    public  void setSelectedStudent(Student student) {
        selectedStudent = student;
    }

    private String selectedGrade;

    public  String getSelectedGrade() {
        return selectedGrade;
    }

    public  void setSelectedGrade(int grade) {
        selectedGrade = Integer.toString(grade);
    }

    private boolean isNew;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }
}
