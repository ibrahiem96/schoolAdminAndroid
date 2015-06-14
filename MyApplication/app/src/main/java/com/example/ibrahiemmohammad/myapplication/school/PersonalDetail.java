package com.example.ibrahiemmohammad.myapplication.school;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.MessageFormat;


public class PersonalDetail implements Parcelable{
	
	public String FirstName;
	public String LastName;
	public String Middle;
	public Genders Gender;
	public Phone Phone;
	
	public String getFullName(){
		
		return FirstName + " " + LastName;
	}
	
	
	public PersonalDetail(){
		
	}
	
	public PersonalDetail(String firstName, String lastName, String middleName, Genders gender, Phone phone){
		
		FirstName = firstName;
		LastName = lastName;
		Middle = middleName;
		Gender = gender;
		Phone = phone;
		
	}
	
	public PersonalDetail createStudentDetails(String studentID, Genders gender){
		
		String firstName = "FN." + studentID;
		String lastName = "LN." + studentID;
		String middleName = "MN." + studentID;
		
		
		return new PersonalDetail(firstName, lastName, middleName, gender, new Phone("000-000-0000"));
		
		
	}
	
	public PersonalDetail createPersonalDetails(String firstName, String lastName, String middleName, String gender, Phone phone){
		
		return new PersonalDetail(firstName, lastName, middleName, Genders.valueOf(gender), phone);
		
	}
	
	public PersonalDetail createParentDetails(String studentID, ParentType type){
		
		String firstName = type + ".FN." + studentID;
		String lastName = type + ".LN." + studentID;
		String middleName = type + ".MN." + studentID;
		Genders gender = type == ParentType.Father ? Genders.Male : Genders.Female;
		
		
		return new PersonalDetail(firstName, lastName, middleName, gender, new Phone("000-000-0000"));
		
		
	}
	
	public void print(){
		
		System.out.println(MessageFormat.format("{0}/{1}/{2}/{3}", FirstName, LastName, Middle, Gender));
		
		Phone.print();
		
	}
	
	public String toString(){
		
		StringBuilder str = new StringBuilder();
		str.append(MessageFormat.format("{0},{1},{2},{3}", FirstName, LastName, Middle, Gender));
		str.append(",");
		str.append(Phone.toString());
		
		return str.toString();
	}

    public PersonalDetail(Parcel in){
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Middle = in.readString();
        this.Gender = Genders.valueOf(in.readString());
        this.Phone = (Phone)in.readValue(Phone.getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
