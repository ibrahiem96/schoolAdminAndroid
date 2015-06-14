package com.example.ibrahiemmohammad.myapplication.registration;

import java.util.ArrayList;
import java.util.Iterator;

import com.example.ibrahiemmohammad.myapplication.school.Registration;
import com.example.ibrahiemmohammad.myapplication.FeeSchedule.Fees;
public class StudentRegistration {
	
	private Registration feeSchedules;
	private int labFee;

	public StudentRegistration(Registration registration){
		
		feeSchedules = registration;
		
	}
	
	public int calculateFees(ArrayList<GradesToRegister> grades){
		
		Iterator<GradesToRegister> iterator = grades.iterator();
		
		int totalFamilyFees = 0;
		int adminFee = 0;
		while (iterator.hasNext()){
			
			GradesToRegister gradeInfo = iterator.next();
			Fees fees = getFeeForGrade(gradeInfo);
			totalFamilyFees += fees.getTotalFees();
			adminFee = fees.Administration;
			
			labFee = getLabFees(getLabFee(), gradeInfo.getGradeNum(), fees);
		}
		
		return totalFamilyFees + adminFee + getLabFee();
	}
	
	private Fees getFeeForGrade(GradesToRegister gradeInfo){
		
		if (gradeInfo.isNew() || gradeInfo.getGradeNum()==-1){
			return feeSchedules.NewStudentFee.FeeList.get(gradeInfo.getGradeNum());
		}
		
		return feeSchedules.ReturningStudentFee.FeeList.get(gradeInfo.getGradeNum());
	}

	private int getLabFees(int labFee, int gradeNum, Fees fees) {
		if (gradeNum >= 9 && gradeNum <= 12){
			
			if (labFee > 0){
				
				labFee = fees.Maximumlab;
			}
			else {
				labFee = fees.Minimumlab;		
			}
			
		}
		return labFee;
	}

    public int getLabFee() {
        return labFee;
    }
}
