package com.example.ibrahiemmohammad.myapplication.FeeSchedule;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.example.ibrahiemmohammad.myapplication.Helper.FileHelper;
import com.example.ibrahiemmohammad.myapplication.Helper.IntegerHelper;


public class FeesLoader {
	
	public GradeFees getGradeFees(String filename, String filelocation){
		
		if(!FileHelper.fileExists(filelocation)){
			
			System.out.println("File Location Does Not Exist");
			return null;
		}
		
		File feeFile = new File(filelocation, filename);
		
		if (!feeFile.exists()){
			System.out.println("Student file not found: " + filename);
			return null;
		}
		
		ArrayList<String> feeData = FileHelper.getData(feeFile);
		
		
		return fillGradeFees(feeData);
	}

    public GradeFees getGradeFees(InputStream gradeFeesStream){
        ArrayList<String> feeData = FileHelper.getData(gradeFeesStream);
        return fillGradeFees(feeData);
    }

	private GradeFees fillGradeFees(ArrayList<String> feeData) {
		Iterator<String> feeIterator = feeData.iterator();
		
		GradeFees gradefees = new GradeFees();
		gradefees.FeeList = new HashMap<Integer,Fees>();
		
		while (feeIterator.hasNext()){
			
			String row = feeIterator.next();
			String[] dataValues = row.split(",");
			if (dataValues.length < 7){
				
				System.out.println("Incomplete fee data: " + row);
				continue;
				
			}
			
			boolean badData = false;
			for (int i = 0; i < dataValues.length; i++){
			
				if (!IntegerHelper.isInteger(dataValues[i])){
				
					System.out.println("Non-integer Data for column: " + (i+1) + " " + row);
					badData = true;
					break;
				
				}
				
			}
			
			if (badData){
				continue;
			}
				
				
				
			int gradeNumber = Integer.parseInt(dataValues[0]);
			Fees fee = new Fees(gradeNumber
					, Integer.parseInt(dataValues[1])
					, Integer.parseInt(dataValues[2])
					, Integer.parseInt(dataValues[3])
					, Integer.parseInt(dataValues[4])
					, Integer.parseInt(dataValues[5])
					, Integer.parseInt(dataValues[6])
					, Integer.parseInt(dataValues[7]));
			
			gradefees.FeeList.put(gradeNumber, fee);
				
			}
		return gradefees;
	}
	
	}
	 