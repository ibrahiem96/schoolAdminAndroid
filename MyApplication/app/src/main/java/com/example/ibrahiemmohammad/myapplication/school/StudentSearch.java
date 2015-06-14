package com.example.ibrahiemmohammad.myapplication.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentSearch {

	private HashMap<Student,Integer> studentList; 
	
	public StudentSearch(School school){
		studentList = school.getStudents();
	}
	
	public HashMap<Student,Integer> findByFirstName(String firstName){
		
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Details.FirstName.equalsIgnoreCase(firstName)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
		}
		return matchingStudent;
	}
	
	public HashMap<Student,Integer> findByLastName(String lastName){
		
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Details.LastName.equalsIgnoreCase(lastName)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
		}
		return matchingStudent;
	}

	public HashMap<Student,Integer> findByFullName(String fullName){
		
		String[] names = fullName.split(" ");
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Details.LastName.equalsIgnoreCase(names[1]) && entry.getKey().Details.FirstName.equalsIgnoreCase(names[0])){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Details.LastName.equalsIgnoreCase(names[0]) && entry.getKey().Details.FirstName.equalsIgnoreCase(names[1])){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
		}
		return matchingStudent;
	}
	
	public HashMap<Student,Integer> findByPhoneNumber(String phone){
		
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Details.Phone.Cell.equalsIgnoreCase(phone)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Details.Phone.Home.equalsIgnoreCase(phone)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Details.Phone.Work.equalsIgnoreCase(phone)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
		}
		return matchingStudent;
	}

	public HashMap<Student,Integer> findByStreetName(String street){
		
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Contact.Street.equalsIgnoreCase(street)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
		}
		return matchingStudent;
	}
	
	public HashMap<Student,Integer> findByMotherName(String motherName){
		
		String[] names = motherName.split(" ");
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Parent2.Details.LastName.equalsIgnoreCase(names[1]) && entry.getKey().Parent2.Details.FirstName.equalsIgnoreCase(names[0])){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Parent2.Details.LastName.equalsIgnoreCase(names[0]) && entry.getKey().Parent2.Details.FirstName.equalsIgnoreCase(names[1])){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
		}
		return matchingStudent;
	}

	public HashMap<Student,Integer> findByFatherName(String fatherName){
		
		String[] names = fatherName.split(" ");
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Parent1.Details.LastName.equalsIgnoreCase(names[1]) && entry.getKey().Parent1.Details.FirstName.equalsIgnoreCase(names[0])){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Parent1.Details.LastName.equalsIgnoreCase(names[0]) && entry.getKey().Parent1.Details.FirstName.equalsIgnoreCase(names[1])){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
		}
		return matchingStudent;
	}
	
	public HashMap<Student,Integer> findByParentPhone(String parentPhone){
		
		
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Parent1.Details.Phone.Cell.equalsIgnoreCase(parentPhone)||entry.getKey().Parent2.Details.Phone.Cell.equalsIgnoreCase(parentPhone)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Parent1.Details.Phone.Home.equalsIgnoreCase(parentPhone)||entry.getKey().Parent2.Details.Phone.Home.equalsIgnoreCase(parentPhone)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
			else if (entry.getKey().Parent1.Details.Phone.Work.equalsIgnoreCase(parentPhone)||entry.getKey().Parent2.Details.Phone.Work.equalsIgnoreCase(parentPhone)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
		}
		return matchingStudent;
	}

	public HashMap<Student,Integer> findByParentWork(String parentWork){
		
		
		HashMap<Student,Integer> matchingStudent = new HashMap<Student,Integer>();
		for (Map.Entry<Student, Integer> entry : studentList.entrySet()){
			if (entry.getKey().Parent1.WorkName.equalsIgnoreCase(parentWork)||entry.getKey().Parent2.WorkName.equalsIgnoreCase(parentWork)){
				matchingStudent.put(entry.getKey(), entry.getValue());
			}
			
		}
		return matchingStudent;	}


	public String[][] findArrayByParentWork(String parentWork){
		
		String[][] result = new String[300][2];
		
		HashMap<Student,Integer> matchingStudent = findByParentWork(parentWork);
		
		int row = 0;
		int column = 0;
		for (Map.Entry<Student, Integer> entry : matchingStudent.entrySet()){
			result[row][column++] = entry.getKey().Id;
			result[row++][column] = entry.getValue().toString();
			column = 0;
		}
		return result;
	}

    public HashMap<Student, Integer> findStudent (String textToSearch, String searchBy){

        switch(searchBy){
            case "First Name":
                return findByFirstName(textToSearch);
            case "Last Name":
                return findByLastName(textToSearch);
            case "Full Name":
                return findByFullName(textToSearch);
            case "Phone":
                return findByPhoneNumber(textToSearch);
            case "Street Name":
                return findByStreetName(textToSearch);
            case "Mother's Name":
                return findByMotherName(textToSearch);
            case "Father's Name":
                return findByFatherName(textToSearch);
            case "Parent's Phone":
                return findByParentPhone(textToSearch);
            case "Parent's Work":
                return findByParentWork(textToSearch);
        }

        return new HashMap<Student, Integer>();

    }


}
