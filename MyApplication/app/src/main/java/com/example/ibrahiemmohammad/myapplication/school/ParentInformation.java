package com.example.ibrahiemmohammad.myapplication.school;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.MessageFormat;

import com.example.ibrahiemmohammad.myapplication.Helper.IntegerHelper;


public class ParentInformation implements Parcelable{
	
	public PersonalDetail Details;
	public ParentType Type;
	public Phone WorkPhone;
	public Address WorkAddress;
	public String WorkName;
	public int AnnualIncome;

	public ParentInformation(){
		
	}
	
	public ParentInformation(PersonalDetail detail, ParentType type, Phone workPhone, Address workAddress, String workName, int annualIncome){
		
		Details = detail;
		Type = type;
		WorkPhone = workPhone;
		WorkAddress = workAddress;
		WorkName = workName;
		AnnualIncome = annualIncome;
	}
	
	public ParentInformation createParentInformation(ParentType type, String studentID){
		
		return new ParentInformation(new PersonalDetail().createParentDetails(studentID, type)
				                     , type
				                     , new Phone("111-111-1111")
		                             , new Address("WorkStreet1", "WorkCity1", States.IL, "WorkZipCode1")
		                             , "Company1"
		                             , 78000);
		
	}
	
	public ParentInformation createParentInformation(String[] parentData){
		
		if (parentData == null || parentData.length < 18){
			
			System.out.println("Parent Data Incomplete");
			return null;
		}
		
		Phone phone = new Phone(parentData[5], parentData[6], parentData[7]);
		PersonalDetail detail = new PersonalDetail().createPersonalDetails(parentData[1], parentData[2], parentData[3], parentData[4], phone);
		Phone workPhone = new Phone(parentData[15], parentData[16], parentData[17]);
		Address workAddress = new Address(parentData[11], parentData[12], States.valueOf(parentData[13]), parentData[14]);
		Integer salary= 0;
		if (IntegerHelper.isInteger(parentData[10].trim())){
			salary = Integer.parseInt(parentData[10].trim());
		}	

		return new ParentInformation(detail
				                     , ParentType.valueOf(parentData[8])
				                     , workPhone
		                             , workAddress
		                             , parentData[9]
		                             , salary);
		
	}
	
	
	
	public void print(){
		Details.print();
		
		System.out.println(MessageFormat.format("{0}/{1}/${2}", Type, WorkName, AnnualIncome));
		
		WorkPhone.print();
		WorkAddress.print();
		
		
		
	}
	
	public String toString(){
		
		StringBuilder str = new StringBuilder();
		str.append(Details.toString());
		str.append(",");
		str.append(MessageFormat.format("{0},{1},{2, number, ###}", Type, WorkName, AnnualIncome));
		str.append(",");
		str.append(WorkAddress.toString());
		str.append(",");
		str.append(WorkPhone.toString());
		
		return str.toString();
			
	}

    public ParentInformation(Parcel in){
        this.Details = (PersonalDetail)in.readValue(Details.getClass().getClassLoader());
        this.Type = ParentType.valueOf(in.readString());
        this.WorkName = in.readString();
        this.AnnualIncome = in.readInt();
        this.WorkAddress = (Address)in.readValue(WorkAddress.getClass().getClassLoader());
        this.WorkPhone = (Phone)in.readValue(WorkPhone.getClass().getClassLoader());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.Details);
        dest.writeString(this.Type.toString());
        dest.writeString(this.WorkName);
        dest.writeInt(this.AnnualIncome);
        dest.writeValue(this.WorkAddress);
        dest.writeValue(this.WorkPhone);
    }

    public static final Parcelable.Creator<ParentInformation> CREATOR = new Parcelable.Creator<ParentInformation>(){
        public ParentInformation createFromParcel(Parcel in){
            return new ParentInformation(in);
        }
        public ParentInformation[] newArray(int size){
            return new ParentInformation[size];
        }
    };
}