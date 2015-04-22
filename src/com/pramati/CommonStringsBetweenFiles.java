package com.pramati;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CommonStringsBetweenFiles {

	public static void main(String[] args) {

		try {
			BufferedReader firstFileBufferedReader = new BufferedReader(new FileReader("Input/firstFile.txt"));
			BufferedReader secondFileBufferedReader = new BufferedReader(new FileReader("Input/secondFile.txt"));
			BufferedWriter outputFileBufferedWriter =  new BufferedWriter(new FileWriter(new File("Output/commonStrings.txt")));
			MatchingStrings matchFound=new MatchingStrings(firstFileBufferedReader, secondFileBufferedReader, outputFileBufferedWriter );
			matchFound.find();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
