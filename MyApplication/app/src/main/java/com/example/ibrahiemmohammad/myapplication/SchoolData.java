package com.example.ibrahiemmohammad.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;

import com.example.ibrahiemmohammad.myapplication.Helper.FileHelper;
import com.example.ibrahiemmohammad.myapplication.school.Admissions;
import com.example.ibrahiemmohammad.myapplication.school.Registration;
import com.example.ibrahiemmohammad.myapplication.school.School;
import com.example.ibrahiemmohammad.myapplication.school.SchoolDataReader;
import com.example.ibrahiemmohammad.myapplication.school.SchoolDataWriter;
import com.example.ibrahiemmohammad.myapplication.school.StudentSearch;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ibrahiem Mohammad on 5/31/2015.
 */
public class SchoolData {

    private static InputStream studentStream;
    private static InputStream parentStream;
    private static InputStream returningStudentFeesStream;
    private static InputStream newStudentFeesStream;

    private static OutputStream registeredStudentWriteStream;
    private static InputStream registeredStudentReadStream;

    private static Registration registration;
    private static School school;
    private static StudentSearch studentLib;


    public static void load(ActionBarActivity activity) {
        studentStream = activity.getResources().openRawResource(R.raw.studentfile);
        parentStream = activity.getResources().openRawResource(R.raw.parentfile);
        newStudentFeesStream = activity.getResources().openRawResource(R.raw.newstudentfees);
        returningStudentFeesStream = activity.getResources().openRawResource(R.raw.returningstudentfees);


        school = Admissions.loadSchoolData(studentStream, parentStream);
        registration = Admissions.loadFeeSchedule(newStudentFeesStream, returningStudentFeesStream);
        studentLib = new StudentSearch(getSchool());
    }

    public static void saveRegisteredData(ActionBarActivity activity, ArrayList<String> registeredStudents, int mode){

        try {
            registeredStudentWriteStream = activity.openFileOutput("registeredstudent.csv", mode);
            SchoolDataWriter.writeRegisteredStudentInfo(registeredStudents, registeredStudentWriteStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getRegisteredData(ActionBarActivity activity){

        try {
            registeredStudentReadStream = activity.openFileInput("registeredstudent.csv");
            return FileHelper.getData(registeredStudentReadStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    public static void deleteRegistrationData(String studentID, String grade, ActionBarActivity activity){
        String expression = studentID+", "+grade+","+ Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<String> registeredData = getRegisteredData(activity);
        ArrayList<String> saveList = new ArrayList<String>();
        for (String row : registeredData){
            if(!expression.equalsIgnoreCase(row)){
                saveList.add(row);
            }
        }
        saveRegisteredData(activity, saveList, Context.MODE_PRIVATE);
    }

    public static Boolean wasStudentRegistered(String studentID, String grade, ActionBarActivity activity){
        String expression = studentID+", "+grade+","+ Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<String> registeredData = getRegisteredData(activity);
        if (registeredData.size()==0){
            return false;
        }
        for (String item : registeredData) {
            if (expression.equalsIgnoreCase(item)){
                return true;
            }
        }
        return false;
    }

    public static Registration getRegistration() {
        return registration;
    }

    public static School getSchool() {
        return school;
    }

    public static StudentSearch getStudentLib() {
        return studentLib;
    }
}
