package com.example.ibrahiemmohammad.myapplication.school;
import com.example.ibrahiemmohammad.myapplication.Helper.FileHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class SchoolDataWriter {
	
public void writeStudentFile(School school, String studentFileName, String location){
		
	if (!isValidSchool(school)){
		return;
	}		
		File filelocation = new File(location);
		if (!filelocation.exists()){
			
			System.out.println("Invalid Path: " + location);
			return;
			
		}
		
		File studentfile = new File(filelocation, studentFileName);
		
		try {
			
			PrintWriter writer = new PrintWriter(studentfile.getCanonicalPath(), "UTF-8");
			for (int i = 0; i < school.Grades.size(); i++){
				
				Grade currentGrade = school.Grades.get(i);
				for (int s = 0; s < currentGrade.Students.size(); s++){
					
					Student currentStudent = currentGrade.Students.get(s);
					writer.print(currentGrade.toString());
					writer.print(",");
					writer.println(currentStudent.toString());
					
				}
				
			}
		
			writer.close();
			
		}
		catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

private static boolean isValidSchool(School school) {
	if (school == null){
		
		System.out.println("School object null");
		return false;
		
	}
	return true;
}


	public void writeParentFile(School school, String parentFileName, String location){
		
		if (!isValidSchool(school)){
			return;
		}
		
		File filelocation = new File(location);
		if (!filelocation.exists()){
			
			System.out.println("Invalid Path: " + location);
			return;
			
		}
		
		File parentfile = new File(filelocation, parentFileName);
			
		try {
			
			PrintWriter writer = new PrintWriter(parentfile.getCanonicalPath(), "UTF-8");
			for (int i = 0; i < school.Grades.size(); i++){
				
				Grade currentGrade = school.Grades.get(i);
				for (int s = 0; s < currentGrade.Students.size(); s++){
					
					Student currentStudent = currentGrade.Students.get(s);
					writeParentInfo(writer, currentStudent.Id, currentStudent.Parent1);
					writeParentInfo(writer, currentStudent.Id, currentStudent.Parent2);
					
					
				}
				
			}
		
			writer.close();
			
		}
		catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void writeParentInfo(PrintWriter writer, String currentStudentID, ParentInformation parentInfo) {
		writer.print(currentStudentID);
		writer.print(",");
		writer.println(parentInfo.toString());
	}

    public static void writeRegisteredStudentInfo(ArrayList<String> studentList, OutputStream registeredStream){
        FileHelper.setData(studentList, registeredStream);
    }

}
