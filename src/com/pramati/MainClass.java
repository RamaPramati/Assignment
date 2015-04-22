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

public class MainClass {

	static Set<String> firstFileStrings = new HashSet<String>();
	static Set<String> secondFileStrings = new HashSet<String>();
	static Set<String> commonStrings = new HashSet<String>();

	public static void main(String[] args) {

		readDataFromFiles();

		CommonDataFinder matchFound=new CommonDataFinder(firstFileStrings, secondFileStrings);
		commonStrings = matchFound.findCommonData();

		writeDataToFile();

	}

	public static void readDataFromFiles(){

		BufferedReader firstFileBufferedReader = null;
		BufferedReader secondFileBufferedReader = null;		
		String CurrentLine;

		try	{

			firstFileBufferedReader = new BufferedReader(new FileReader("Input/firstFile.txt"));
			secondFileBufferedReader = new BufferedReader(new FileReader("Input/secondFile.txt"));

			while ((CurrentLine = firstFileBufferedReader.readLine()) != null) {
				firstFileStrings.add(CurrentLine);
			}

			while ((CurrentLine = secondFileBufferedReader.readLine()) != null) {
				secondFileStrings.add(CurrentLine);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (firstFileBufferedReader != null)firstFileBufferedReader.close();
				if (secondFileBufferedReader != null)secondFileBufferedReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void writeDataToFile(){

		BufferedWriter outputFileBufferedWriter =  null;
		Iterator<String> commonStringsIterator=commonStrings.iterator();		

		try {
			outputFileBufferedWriter =  new BufferedWriter(new FileWriter(new File("Output/commonStrings.txt")));

			while (commonStringsIterator.hasNext()) {
				outputFileBufferedWriter.write(commonStringsIterator.next().toString()+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (outputFileBufferedWriter != null)outputFileBufferedWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}