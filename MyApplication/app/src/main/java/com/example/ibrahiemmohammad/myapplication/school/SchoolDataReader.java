package com.example.ibrahiemmohammad.myapplication.school;
import com.example.ibrahiemmohammad.myapplication.Helper.FileHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;


public class SchoolDataReader {
	
	public void readParentFile(School school, String parentFileName, String location){
		
		if (school == null){
			System.out.println("School object null");
			return;
		}
		
		File filelocation = new File(location);
		if (!fileExists(location)){
			System.out.println("File location does not exist: " + location);
			return;
		}
		
		File parentfile = new File(filelocation, parentFileName);
		
		if (!parentfile.exists()){
			System.out.println("Parent file not found: " + parentFileName);
			return;
		}
		

		ArrayList<String> parentData = getData(parentfile);
		fillParentInfo(school, parentData);
	}

    public void readParentFile(School school, InputStream parentStream){

        if (school == null){
            System.out.println("School object null");
            return;
        }

        ArrayList<String> parentData = FileHelper.getData(parentStream);
        fillParentInfo(school, parentData);
    }
	
	public static void fillParentInfo(School school, ArrayList<String> parentData){
		
		if (parentData == null || parentData.size() == 0){
			System.out.println("Parent data not found");
			return;
		}
		
		Iterator<String> iterator = parentData.iterator();
		while (iterator.hasNext()){
			
			String line = iterator.next();
			String[] rowData = line.split(",");
			String studentID = rowData[0];
			Student student = school.getStudent(studentID);
			if (student == null){
				System.out.println(MessageFormat.format("Student not found for ID: {0}", studentID));
			}
			else {
				student.addParentInfo(rowData);
			}
		}
		
	}
	
	public void readStudentFile(School school, String studentFileName, String location){
			
		if (school == null){
			System.out.println("School object null");
			return;
		}
		
		File filelocation = new File(location);
		if (!fileExists(location)){
			System.out.println("File location does not exist: " + location);
			return;
		}
		
		File studentfile = new File(filelocation, studentFileName);
		
		if (!studentfile.exists()){
			System.out.println("Student file not found: " + studentFileName);
			return;
		}
		
		
		ArrayList<String> students = getData(studentfile);
		
		
		school.Grades = new ArrayList<Grade>();
		school.Grades = new Grade().createGrades(students);
						
	}

    public void readStudentFile(School school, InputStream studentStream){

        if (school == null){
            System.out.println("School object null");
            return;
        }

        ArrayList<String> students = FileHelper.getData(studentStream);
        school.Grades = new ArrayList<Grade>();
        school.Grades = new Grade().createGrades(students);

    }

    private static boolean fileExists(String location){
		

		File filelocation = new File(location);
		if (!filelocation.exists()){
			
			System.out.println("Invalid Path: " + location);
			return false;
			
		}
		return true;
	}
	
	private static ArrayList<String> getData(File fileToRead) {
		try {
			
			BufferedReader br = new BufferedReader (new FileReader(fileToRead.getCanonicalFile()));
			
			String currentline;
			ArrayList<String> lines = new ArrayList<String>();
			
			while ((currentline = br.readLine()) !=null){
				
				lines.add(currentline);
				
			}
			br.close();
			return lines;
		}
		
		catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}

	
}

// HW: read and understand schoolDataReader
