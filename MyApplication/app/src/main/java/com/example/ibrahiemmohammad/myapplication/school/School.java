package com.example.ibrahiemmohammad.myapplication.school;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;




public class School {
	
	public ArrayList<Grade> Grades;
	public String Name;
	public Phone Phone;
	public Address Address;

		
	public School(String name, Phone number, Address address){
		
		this(name,number);
		Address = address;
		
	}
	
	private School(String name, Phone number){
		
		this(name);
		Phone = number;
		//Name = name;
		
		
	}
	
	private School(String name){
		
		Name = name;
		
	}
	
	public Student getStudent(String studentID){
		
		if (studentID == null || studentID.isEmpty() || studentID.trim().length()==0){
			
			throw new IllegalArgumentException("studentID is null");
			
		}
		
		if (Grades == null){
			
			throw new IllegalArgumentException("Grade Object is null");
			
		}
		
		Iterator<Grade> gradeIterator = Grades.iterator();
		while (gradeIterator.hasNext()){
			
			Grade currentGrade = gradeIterator.next();
			Student matchedStudent = currentGrade.getStudent(studentID);
			if (matchedStudent != null){
				return matchedStudent;
			}
			
		}
		return null;
	}
	
	public HashMap<Student, Integer> getStudents(){
		
		HashMap<Student,Integer> studentList = new HashMap<Student,Integer>();
		
		if (Grades == null){
			
			return studentList;
			
		}
		
		Iterator<Grade> gradeIterator = Grades.iterator();
		while (gradeIterator.hasNext()){
			
			Grade currentGrade = gradeIterator.next();
			Iterator<Student> studentIterator = currentGrade.Students.iterator();
			while (studentIterator.hasNext()){
				studentList.put(studentIterator.next(), currentGrade.Number);
			}
			
		}
		return studentList;
	}
	
	public void print(){
		
		System.out.println("School Name: " + Name);
		Phone.print();
		Address.print();
		
		for(int number = 0; number < Grades.size(); number++){
			
			Grades.get(number).print();
			
		}
		
	}
	
		
}

