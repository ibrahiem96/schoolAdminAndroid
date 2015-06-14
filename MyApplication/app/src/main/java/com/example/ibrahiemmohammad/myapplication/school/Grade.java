package com.example.ibrahiemmohammad.myapplication.school;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.example.ibrahiemmohammad.myapplication.Helper.IntegerHelper;

public class Grade {

	public int Number;
	public ArrayList<Student> Students;

	public ArrayList<Grade> generateGrades(int numberOfGrades, int numberOfStudents) {

		ArrayList<Grade> grades = new ArrayList<Grade>();

		for (int grade = 1; grade <= numberOfGrades; grade++) {

			grades.add(generateGrade(grade, numberOfStudents));
		
		}
		
		return grades;
	}

	private static Grade generateGrade(int gradeNumber, int students){
		
		Grade grade = new Grade();
		grade.Number = gradeNumber;
		grade.Students = new Student().generateStudents(students, gradeNumber);
		
		return grade;
		
	}
	
	public ArrayList<Grade> createGrades(ArrayList<String> gradeData) {
		
		if (gradeData == null || gradeData.size()==0){
			
			System.out.println("Grade data not found");
			return new ArrayList<Grade>();
			
		}
		
		HashSet<Integer> grades = getUniqueGrades(gradeData);
		ArrayList<Grade> gradeList = new ArrayList<Grade>();
		
		
		Iterator<Integer> gradeIterator = grades.iterator();	
	
		while (gradeIterator.hasNext()){
			
			Grade grade = new Grade();
			grade.Number = gradeIterator.next();
			grade.Students = new ArrayList<Student>();
			fillGrade(grade, gradeData);
			gradeList.add(grade);
			
		}
		
		return gradeList;
	}
	
	private static HashSet<Integer> getUniqueGrades(ArrayList<String> dataValues){
		
		HashSet<Integer> grades = new HashSet<Integer>();
		
		for (int i = 0; i < dataValues.size(); i++){
			
			Integer gradeNumber = getGradeNumber(dataValues.get(i));
			
			if (gradeNumber == -1){
				
				System.out.println("Invalid Grade:" + " " + dataValues.get(i));
				continue;
				
			}
			
			grades.add(gradeNumber);
			
		}
		return grades;
	}
	
	private static void fillGrade(Grade grade, ArrayList<String> gradeData){
		
		Iterator<String> iterator = gradeData.iterator();
		
		while (iterator.hasNext()){
		
			String dataRow = iterator.next();
			Integer gradeNumber = getGradeNumber(dataRow);
			
			if (gradeNumber == -1){
				
				continue;
				
			}
			
			if (grade.Number == gradeNumber){
				
				Student student = new Student().createStudent(dataRow.split(","));
				
				if (student == null){
					
					System.out.println("Unable to create student for data: " + dataRow);
					continue;
				}
				
				grade.Students.add(student);
			}

			
		}
				
		
	}
	
	private static Integer getGradeNumber(String dataRow){
		
		String[] columns = dataRow.split(",");
		
		return IntegerHelper.isInteger(columns[0]) ? Integer.parseInt(columns[0]) : -1;
		
		
	}
	
	public Student getStudent(String studentID){
		
		if (studentID == null || studentID.isEmpty() || studentID.trim().length()==0){
			
			throw new IllegalArgumentException("studentID is null");
			
		}
		
		if (Students==null){
			
			throw new IllegalArgumentException("Students Object is null");

		}
		
		Iterator<Student> studentIterator = Students.iterator();
		while (studentIterator.hasNext()){
			Student currentStudent = studentIterator.next();
			if (currentStudent.Id.equalsIgnoreCase(studentID)){
				return currentStudent;
			}
			
		}
		return null;
	}
	
	public void print(){
		
		System.out.println("Grade Number: " + Number);
		
		for (int number = 0; number < Students.size(); number++){
			
			Students.get(number).print(); 
			
		}
		
	}
	
	public String toString(){
		
		return MessageFormat.format("{0}", Number);
			
	}
	
	
}