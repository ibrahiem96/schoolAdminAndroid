package com.example.ibrahiemmohammad.myapplication.school;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.MessageFormat;


public class Address implements Parcelable{
	
	public String Street;
	public String City;
	public States State;
	public String ZipCode;

	public Address(){
		
	}
	
	public Address(String street, String city, States states, String zipcode){
		
		Street = street;
		City = city;
		State = states;
		ZipCode = zipcode;
		
	}

	public Address createAddress(String street, String city, String state, String zipcode){
		
		return new Address(street, city, States.valueOf(state), zipcode);
		
	}

    public String getAddress1(){
        return Street;
    }

    public String getAddress2(){
        return City + " " + State.toString() + " " + ZipCode;
    }

	public void print(){
		
		System.out.println(Street);
		System.out.println(City + " " + State + " " + ZipCode);
				
	}
	
	public String toString(){
		
		return MessageFormat.format("{0},{1},{2},{3}", Street, City, State, ZipCode);
			
	}

    public Address(Parcel in){
        this.Street = in.readString();
        this.City = in.readString();
        this.State = States.valueOf(in.readString());
        this.ZipCode = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.Street, this.City, this.State.toString(), this.ZipCode});
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>(){
        public Address createFromParcel(Parcel in){
            return new Address(in);
        }
        public Address[] newArray(int size){
            return new Address[size];
        }
    };
}
