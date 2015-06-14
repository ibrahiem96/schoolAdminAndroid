package com.example.ibrahiemmohammad.myapplication.school;

import com.example.ibrahiemmohammad.myapplication.FeeSchedule.FeesLoader;
import com.example.ibrahiemmohammad.myapplication.FeeSchedule.GradeFees;

import java.io.InputStream;

public class Registration {

	public GradeFees ReturningStudentFee;
	public GradeFees NewStudentFee;
	
	public void LoadFees(String fileLocation, String returningStudentFileName, String newStudentFileName){
		
		ReturningStudentFee = new FeesLoader().getGradeFees(returningStudentFileName, fileLocation);
		
		NewStudentFee = new FeesLoader().getGradeFees(newStudentFileName, fileLocation);
		
		
	}

    public void LoadFees(InputStream newStudentFeesStream, InputStream returningStudentFeesStream){

        ReturningStudentFee = new FeesLoader().getGradeFees(returningStudentFeesStream);

        NewStudentFee = new FeesLoader().getGradeFees(newStudentFeesStream);


    }
	
}
