package com.example.ibrahiemmohammad.myapplication.Helper;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class FileHelper {
	
	public static boolean fileExists(String location){
		

		File filelocation = new File(location);
		if (!filelocation.exists()){
			
			System.out.println("Invalid Path: " + location);
			return false;
			
		}
		return true;
	}
	
	public static ArrayList<String> getData(File fileToRead) {
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

    public static ArrayList<String> getData(InputStream fileStream) {
        try {
            Reader reader = new InputStreamReader(fileStream);
            BufferedReader br = new BufferedReader (reader);

            String currentLine;
            ArrayList<String> lines = new ArrayList<String>();

            while ((currentLine = br.readLine()) !=null){

                lines.add(currentLine);

            }
            br.close();
            return lines;
        }

        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }


    public static void setData(ArrayList<String> rowsToWrite, OutputStream fileStream) {
        try {
            Writer writer = new OutputStreamWriter(fileStream);
            BufferedWriter bw = new BufferedWriter (writer);

            String currentLine;
            for (String row:rowsToWrite){
                bw.write(row);
                bw.newLine();
            }
            bw.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
