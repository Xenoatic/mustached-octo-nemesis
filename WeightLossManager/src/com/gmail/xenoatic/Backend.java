package com.gmail.xenoatic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Backend {
	
	/** This is the File we will use for output	 */
	File ourFile;
	
	/** This Constructor sets up the file location
	 */
	public Backend(String fileLocation) {
		this.ourFile  = new File(fileLocation); //TODO this might not work as intended
	}
	
	/** This method writes to the file specified.
	 *  It appends currenttime : weight to the file
	 *
	 * 
	 * @param InputOutput file we are outputting to
	 * @param weight the users current weight
	 * @throws IOException
	 */
	public void fileIO(File InputOutput, double weight) throws IOException {
		long time; //current time in milliseconds
		
		// Getting the current time
		Calendar cal = Calendar.getInstance();
		time = cal.getTimeInMillis();
		
		//create the buffered Reader and writers for file operations
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(this.ourFile, true)));
		
		//appending time : weight to the bottom of file.
		out.println(time + " : " + weight);
		
		//closing the writer
		out.close();
	}
	/** This gets the graph dataset from file
	 * @return dataset
	 * @throws IOException 
	 */
	public XYDataset getDataset() throws IOException {
		String curLine;
		String spLine[];
		Double data[] = new Double[2];
		
		//sets up the series to not have duplicates and autosort
		 XYSeries series1 = new XYSeries("weight", true, false);
        //retrieve the fileset into a 2d array
        
		 BufferedReader reader = new BufferedReader(new FileReader(this.ourFile));
        
		 while((curLine = reader.readLine()) != null) {
			//then split the file by : and remove spaces (somesorta delimiation)
			 spLine = curLine.split(" : ");
			 
			 //TODO needs to parse String into a number and make sure it's a number
			 //without crashing
			 try{
		        	data[0] = Double.parseDouble(spLine[0]);
		        	data[1] = Double.parseDouble(spLine[1]);
		        }catch(NumberFormatException nfe){
		            System.err.println("File parsed incorrectly \r"
		            		+ "possibly string instead of double?");
		     	}
			 //adding the data to the series
			 series1.add(data[0], data[1]);
		 }

        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
        //TODO Check the dates of the datasets
        
        //close reader
        reader.close();
		
		return dataset;
	}
	
	/**
	 * for testing only
	 * @param args unused
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		Backend b = new Backend("C:/InputOutput.txt");
		b.getDataset();
	}
	
}

