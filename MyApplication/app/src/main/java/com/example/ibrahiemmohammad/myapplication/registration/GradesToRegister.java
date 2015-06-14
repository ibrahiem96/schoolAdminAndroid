package com.example.ibrahiemmohammad.myapplication.registration;

public class GradesToRegister {

	private boolean isNew;
	
	private int gradeNum;
	
	public int getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(int gradeNum) {
		this.gradeNum = gradeNum;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public GradesToRegister(int gradenum, boolean isNew){
		
		this.gradeNum = gradenum;
		this.isNew = isNew;
	}
	
}
