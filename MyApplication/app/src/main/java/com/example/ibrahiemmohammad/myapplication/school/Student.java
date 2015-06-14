package com.example.ibrahiemmohammad.myapplication.school;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Student implements Parcelable{

	public String Id;
	public PersonalDetail Details;
	public ParentInformation Parent1;
	public ParentInformation Parent2;
	public Address Contact;

    public boolean isNew;


    public Student(){

    }

	public ArrayList<Student> generateStudents(int numberOfStudents, int gradeNumber){
		
		ArrayList<Student> students = new ArrayList<Student>();
		
		for(int studentNumber = 1; studentNumber <= numberOfStudents; studentNumber++){
			
			Student student = generateStudent(studentNumber, gradeNumber, getStudentGender(numberOfStudents, studentNumber));
			students.add(student);
					
			
		}
		
		return students;
		
	}
	
	private static Genders getStudentGender(int numberOfStudents, int currentStudentID){
		
		int median = numberOfStudents/2;
		
		if (currentStudentID <= median){
			return Genders.Male;
		}
		return Genders.Female;
	}
	
	

	private static Student generateStudent(int studentNumber, int gradeNumber, Genders gender){
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		Student student = new Student();
		student.Id = MessageFormat.format("S{0}_G{1}_Y{2, number,####}", studentNumber, gradeNumber, year);
		
		student.Details = new PersonalDetail().createStudentDetails(student.Id, gender);
		student.Parent1 = new ParentInformation().createParentInformation(ParentType.Father, student.Id);
		student.Parent2 = new ParentInformation().createParentInformation(ParentType.Mother, student.Id);	
		student.Contact = new Address("Street1", "City1", States.IL, "ZipCode1");
		
		return student;
		
	}
	
	public Student createStudent(String[] dataValues){
		
		if (dataValues == null || dataValues.length < 14){
			
			System.out.println("Incomplete student data found");
			return null;
			
		}
		
		
		Student student = new Student();
		student.Id = dataValues[1];
		
		Phone phone = new Phone(dataValues[6], dataValues[7], dataValues[8]);
		student.Contact = new Address().createAddress(dataValues[9], dataValues[10], dataValues[11], dataValues[12]);
		student.Details = new PersonalDetail().createPersonalDetails(dataValues[2], dataValues[3], dataValues[4], dataValues[5], phone);
        student.isNew = Boolean.valueOf(dataValues[13]);
		
		return student;
				
	}
	
	public void addParentInfo(String[] parentData){
		
		if (parentData == null || parentData.length < 18){
			
			System.out.println("No parent data found");
			return;
			
		}
		
		ParentInformation parent = new ParentInformation().createParentInformation(parentData);
		
		if (parent == null){
			return;
		}
		
		if (parent.Type == ParentType.Father){
			
			Parent1 = parent;
			
		}
		else {
			
			Parent2 = parent;

		}
	}
	
	public void print(){
		
		System.out.println("Student ID: " + Id);
		Details.print();
		Parent1.print();
		Parent2.print();
		Contact.print();
		
	}
	
	public String toString(){
		
		StringBuilder str = new StringBuilder();
		str.append(MessageFormat.format("{0},", Id));
		str.append(Details.toString());
		str.append(",");
		str.append(Contact.toString());
		
		return str.toString();
			
	}

    public Student(Parcel in){
        this.Id = in.readString();
        this.Details = (PersonalDetail)in.readValue(Details.getClass().getClassLoader());
        this.Parent1 = (ParentInformation)in.readValue(Parent1.getClass().getClassLoader());
        this.Parent2 = (ParentInformation)in.readValue(Parent2.getClass().getClassLoader());
        this.Contact = (Address)in.readValue(Contact.getClass().getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Id);
        dest.writeValue(this.Details);
        dest.writeValue(this.Parent1);
        dest.writeValue(this.Parent2);
        dest.writeValue(this.Contact);
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>(){
        public Student createFromParcel(Parcel in){
            return new Student(in);
        }
        public Student[] newArray(int size){
            return new Student[size];
        }
    };
}

