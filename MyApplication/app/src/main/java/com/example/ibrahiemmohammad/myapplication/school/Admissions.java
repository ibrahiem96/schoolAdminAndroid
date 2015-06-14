package com.example.ibrahiemmohammad.myapplication.school;

import java.io.InputStream;
import java.util.ArrayList;

import com.example.ibrahiemmohammad.myapplication.registration.GradesToRegister;
import com.example.ibrahiemmohammad.myapplication.registration.StudentRegistration;

public class Admissions {
	
	
	public static void main (String[] args) {
		
		//generateSchoolData();

		//loadSchoolData();
		
		loadFeeSchedule();

	}

	private static void loadSchoolData() {
		School school = new School("CPSA", new Phone("980-900-8999"), new Address("Bob", "dcc", States.IA, "9897"));
		
		new SchoolDataReader().readStudentFile(school, "StudentFile.csv", "C:\\Users\\Ibrahiem Mohammad\\workspace\\School\\Data");
		new SchoolDataReader().readParentFile(school, "ParentFile.csv", "C:\\Users\\Ibrahiem Mohammad\\workspace\\School\\Data");
		System.out.println("Data Load Completed");
	}

    public static School loadSchoolData(InputStream studentStream, InputStream parentStream) {
        School school = new School("CPSA", new Phone("980-900-8999"), new Address("Bob", "dcc", States.IA, "9897"));

        new SchoolDataReader().readStudentFile(school, studentStream);
        new SchoolDataReader().readParentFile(school, parentStream);
        return school;
    }

	private static void generateSchoolData() {
		School newSchool = SchoolFactory.createSchool(12,20);
		new SchoolDataWriter().writeStudentFile(newSchool, "StudentFile_20150124.csv", "C:\\Users\\Ibrahiem Mohammad\\workspace\\School\\Data");
		new SchoolDataWriter().writeParentFile(newSchool, "ParentFile_20150124.csv", "C:\\Users\\Ibrahiem Mohammad\\workspace\\School\\Data");
	}
	
	private static void loadFeeSchedule() {
		
		Registration registration = new Registration();
		registration.LoadFees("C:\\Users\\Ibrahiem Mohammad\\workspace\\School\\Data", "ReturningStudentFees.csv", "NewStudentFees.csv");
		System.out.println("Fee Load Complete!");
		
		ArrayList<GradesToRegister> grades = getGradeNumbers();
		StudentRegistration registerReturning = new StudentRegistration(registration);
		int totalFees = registerReturning.calculateFees(grades);
		System.out.println("Student Fees: " + totalFees);
	}

    public static Registration loadFeeSchedule(InputStream newStudentFeesStream, InputStream returningStudentFeesStream) {
        Registration registration = new Registration();
        registration.LoadFees(newStudentFeesStream, returningStudentFeesStream);
        return registration;
    }

	private static ArrayList<GradesToRegister> getGradeNumbers() {
		ArrayList<GradesToRegister> grades = new ArrayList<GradesToRegister>();
		grades.add(new GradesToRegister(1, false));
		grades.add(new GradesToRegister(8, false));
		grades.add(new GradesToRegister(10, true));
		grades.add(new GradesToRegister(12, false));
		grades.add(new GradesToRegister(9, true));
		return grades;
	}
	
}
