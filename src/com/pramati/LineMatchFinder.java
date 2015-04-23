package com.pramati;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineMatchFinder {

	static Set<String> firstFileStrings = new HashSet<String>();
	static Set<String> secondFileStrings = new HashSet<String>();
	static Set<String> outputStrings = new HashSet<String>();
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) {
		try {
			MyLogger.setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		readDataFromFiles(firstFileStrings, "Input/firstFile.txt");
		readDataFromFiles(secondFileStrings, "Input/secondFile.txt");

		CommonDataFinder matchFound=new CommonDataFinder(firstFileStrings, secondFileStrings);
		outputStrings = matchFound.findCommonData();

		writeDataToFile(outputStrings, "Output/commonStrings.txt");
	}

	public static void readDataFromFiles(Set<String> fileStrings, String fileName){
	    
		LOGGER.setLevel(Level.INFO);
		   
		BufferedReader fileBufferedReader = null;
		String CurrentLine;

		try	{
			
			LOGGER.info("Reading files.....");

			fileBufferedReader = new BufferedReader(new FileReader(fileName));

			while ((CurrentLine = fileBufferedReader.readLine()) != null) {
				fileStrings.add(CurrentLine);
			}

		} catch (FileNotFoundException e) {
			LOGGER.severe("Got FileNotFoundException exception in readDataFromFiles method");
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.severe("Got 1st level IOException exception in readDataFromFiles method");
			e.printStackTrace();
		}finally {
			try {
				if (fileBufferedReader != null)fileBufferedReader.close();
			} catch (IOException ex) {
				LOGGER.severe("Got 2 nd level IOException exception in readDataFromFiles method");
				ex.printStackTrace();
			}
		}
	}

	public static void writeDataToFile(Set<String> outputStrings, String fileName){
      
		BufferedWriter outputFileBufferedWriter =  null;
		Iterator<String> commonStringsIterator=outputStrings.iterator();		

		try {
		    LOGGER.info("Writing to file.....");

			outputFileBufferedWriter =  new BufferedWriter(new FileWriter(new File(fileName)));

			while (commonStringsIterator.hasNext()) {
				outputFileBufferedWriter.write(commonStringsIterator.next().toString()+"\n");
			}
		} catch (IOException e) {
			LOGGER.severe("Got 1st level IOException exception in readDataFromFiles method");
			e.printStackTrace();
		}finally {
			try {
				if (outputFileBufferedWriter != null)outputFileBufferedWriter.close();
			} catch (IOException ex) {
				LOGGER.severe("Got 2 nd level IOException exception in readDataFromFiles method");
				ex.printStackTrace();
			}
		}
	}
}