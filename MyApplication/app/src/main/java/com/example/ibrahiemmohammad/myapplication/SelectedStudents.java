package com.example.ibrahiemmohammad.myapplication;

import java.util.HashMap;

/**
 * Created by Ibrahiem Mohammad on 5/30/2015.
 */
public class SelectedStudents {
    private static HashMap<String, CheckedStudent> StudentList = new HashMap<String, CheckedStudent>();

    public static void add(String key, CheckedStudent value){
        getStudentList().put(key, value);
    }

    public static void remove(String key){
        getStudentList().remove(key);
    }

    public static HashMap<String, CheckedStudent> getStudentList() {
        return StudentList;
    }
}
