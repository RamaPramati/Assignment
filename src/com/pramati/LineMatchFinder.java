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
	static Set<String> commonStrings = new HashSet<String>();
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public static void main(String[] args) {
		try {
			MyLogger.setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		readDataFromFiles();

		CommonDataFinder matchFound=new CommonDataFinder(firstFileStrings, secondFileStrings);
		commonStrings = matchFound.findCommonData();

		writeDataToFile();
	}

	public static void readDataFromFiles(){
	    
		LOGGER.setLevel(Level.INFO);
		   
		BufferedReader firstFileBufferedReader = null;
		BufferedReader secondFileBufferedReader = null;		
		String CurrentLine;

		try	{
			
			LOGGER.info("Reading files.....");

			firstFileBufferedReader = new BufferedReader(new FileReader("Input/firstFile.txt"));
			secondFileBufferedReader = new BufferedReader(new FileReader("Input/secondFile.txt"));

			while ((CurrentLine = firstFileBufferedReader.readLine()) != null) {
				firstFileStrings.add(CurrentLine);
			}

			while ((CurrentLine = secondFileBufferedReader.readLine()) != null) {
				secondFileStrings.add(CurrentLine);
			}

		} catch (FileNotFoundException e) {
			LOGGER.severe("Got FileNotFoundException exception in readDataFromFiles method");
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.severe("Got 1st level IOException exception in readDataFromFiles method");
			e.printStackTrace();
		}finally {
			try {
				if (firstFileBufferedReader != null)firstFileBufferedReader.close();
				if (secondFileBufferedReader != null)secondFileBufferedReader.close();
			} catch (IOException ex) {
				LOGGER.severe("Got 2 nd level IOException exception in readDataFromFiles method");
				ex.printStackTrace();
			}
		}
	}

	public static void writeDataToFile(){
      
		BufferedWriter outputFileBufferedWriter =  null;
		Iterator<String> commonStringsIterator=commonStrings.iterator();		

		try {
		    LOGGER.info("Writing to file.....");

			outputFileBufferedWriter =  new BufferedWriter(new FileWriter(new File("Output/commonStrings.txt")));

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