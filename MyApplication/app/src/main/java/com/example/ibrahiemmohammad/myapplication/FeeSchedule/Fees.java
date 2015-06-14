package com.example.ibrahiemmohammad.myapplication.FeeSchedule;

public class Fees {
	
	public int GradeID;
	public int Registration;
	public int Administration;
	public int Minimumlab;
	public int Maximumlab;
	public int Book;
	public int Graduation;
	public int Placement;
	
	public Fees(int gradeID, int registration, int administration, int minimumlab, int maximumlab, int book, int graduation, int placement){
		
		GradeID = gradeID;
		Registration = registration;
		Administration = administration;
		Minimumlab = minimumlab;
		Maximumlab = maximumlab;
		Book = book;
		Graduation = graduation;
		Placement = placement;
		
	}

	public int getTotalFees(){
		
		int total = Registration + Placement;
		switch(GradeID){
		
		case -1:
		case 6:
		case 7:
		case 9:
		case 10:
		case 11:
			total = Registration + Placement;
			break;
			
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			total = Registration + Book + Placement;
			break;
			
		case 8:
		case 12:
			total = Registration + Graduation + Placement;
			break;
		
		
		}
		return total;
	}
	
	
}
