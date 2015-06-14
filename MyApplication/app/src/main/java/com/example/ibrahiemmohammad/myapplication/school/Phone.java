package com.example.ibrahiemmohammad.myapplication.school;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.MessageFormat;


public class Phone implements Parcelable{

	public String Home;
	public String Work;
	public String Cell;


	public Phone(String phone) {
	
	Home = Work = Cell = phone;
	
	}
	
	public Phone(String home, String work, String cell){
		
		Home = home;
		Work = work;
		Cell = cell;
	}
	
	public void print(){
		
		System.out.println(MessageFormat.format("Home:{0}/Work:{1}/Cell:{2}", Home, Work, Cell));
		
	}
	
	public String toString(){
		
		return MessageFormat.format("{0},{1},{2}", Home, Work, Cell);
			
	}

    public Phone(Parcel in){
        String[] data = new String[3];
        in.readStringArray(data);
        this.Home = data[0];
        this.Work = data[1];
        this.Cell = data[2];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.Home, this.Work, this.Cell});
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>(){
        public Phone createFromParcel(Parcel in){
            return new Phone(in);
        }
        public Phone[] newArray(int size){
            return new Phone[size];
        }
    };
}