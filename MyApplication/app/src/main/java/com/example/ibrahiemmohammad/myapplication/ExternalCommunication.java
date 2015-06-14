package com.example.ibrahiemmohammad.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

import com.example.ibrahiemmohammad.myapplication.school.Student;

import java.util.Calendar;

/**
 * Created by Ibrahiem Mohammad on 6/13/2015.
 */
public class ExternalCommunication {
    public static void sendText(Student student, String grade, Activity activity){

        String text = student.Details.getFullName()+" is registered for grade "+grade+" and year "+ Calendar.getInstance().get(Calendar.YEAR);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(student.Details.Phone.Cell, null, text, null, null);


    }

}
