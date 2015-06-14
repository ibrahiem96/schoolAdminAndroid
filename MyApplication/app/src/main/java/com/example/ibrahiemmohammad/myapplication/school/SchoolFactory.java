package com.example.ibrahiemmohammad.myapplication.school;


public class SchoolFactory {

	public static School createSchool(int numberOfGrades, int numberOfStudentsInGrade) {
		
		School mySchool = new School("CPSA", createPhone(), createAddress());
		mySchool.Grades = new Grade().generateGrades(numberOfGrades, numberOfStudentsInGrade);
		mySchool.print();
		return mySchool;
	}

	private static Phone createPhone() {
		
		return new Phone("630-656-2610");
		
	}

	private static Address createAddress() {
		
		return new Address("331 West Madison Street", "Lombard", States.IL, "60148");
		
	}

}
