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
import java.util.logging.Logger;

public class LineMatchFinder {

	private static Set<String> firstFileStrings = new HashSet<String>();
	private static Set<String> secondFileStrings = new HashSet<String>();
	private static Set<String> outputStrings = new HashSet<String>();
	private static final Logger LOGGER = Logger.getLogger(LineMatchFinder.class.getName());
	
	public static void main(String[] args) {
		final LineMatchFinder matchFinder = new LineMatchFinder();
		
		matchFinder.readDataFromFiles(firstFileStrings, "Input/firstFile.txt");
		matchFinder.readDataFromFiles(secondFileStrings, "Input/secondFile.txt");

		CommonDataFinder matchFound=new CommonDataFinder();
		outputStrings = matchFound.findCommonData(MatchType.exactCaseInSensitiveMatch,firstFileStrings, secondFileStrings);

		matchFinder.writeDataToFile(outputStrings, "Output/commonStrings.txt");
	}

	public void readDataFromFiles(Set<String> fileStrings, String fileName){

		BufferedReader fileBufferedReader = null;
		String currentLine;

		try	{

			LOGGER.info("Reading files.....");

			fileBufferedReader = new BufferedReader(new FileReader(fileName));

			while (fileBufferedReader.readLine() != null) {
				currentLine = fileBufferedReader.readLine();
				fileStrings.add(currentLine);
			}

		} catch (FileNotFoundException e) {
			LOGGER.severe("Got FileNotFoundException exception in readDataFromFiles method");
		} catch (IOException e) {
			LOGGER.severe("Got 1st level IOException exception in readDataFromFiles method");
		}finally {
			try {
				if (fileBufferedReader != null)fileBufferedReader.close();
			} catch (IOException ex) {
				LOGGER.severe("Got 2 nd level IOException exception in readDataFromFiles method");
			}
		}
	}

	public void writeDataToFile(Set<String> outputStrings, String fileName){
      
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
		}finally {
			try {
				if (outputFileBufferedWriter != null)outputFileBufferedWriter.close();
			} catch (IOException ex) {
				LOGGER.severe("Got 2 nd level IOException exception in readDataFromFiles method");
			}
		}
	}
}