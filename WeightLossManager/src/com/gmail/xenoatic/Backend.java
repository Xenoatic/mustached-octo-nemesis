package com.gmail.xenoatic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Backend {
	
	/** This is the File we will use for output	 */
	File ourFile;
	
	/** 
	 * Default Constructor
	 */
	public Backend(String fileLocation) {
		this.ourFile  = new File(fileLocation);
		System.out.println("IHATEYOU");
	}
	
	/**
	 * Appends to the bottom of the file, and also prints
	 * file to console
	 * 
	 * @param InputOutput file we are outputting to
	 * @param weight the users current weight
	 * @throws IOException
	 */
	public void fileIO(File InputOutput, double weight) throws IOException {
		String Str1;  //for string to be used by the buffered reader
		String time; //going to be the time of day
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/YY HH:mm:ss");
		time = sdf.format(cal.getTime());//formats the time it's getting based on how SDF was initialized
		
		//create the buffered Reader and writers for file operations
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.ourFile, true)));
		BufferedReader br = new BufferedReader(new FileReader(this.ourFile));
		
		//appending time : weight to the bottom of file.
		out.println(time + " : " + weight);
		
		//this prints the file to the console
		while ((Str1 = br.readLine()) != null) {
			System.out.println(Str1);
		}
		System.out.println(time + " : " + weight); //shows the console what we wrote to the file
		
		//closing the writers
		br.close();
		out.close();
	}
}

