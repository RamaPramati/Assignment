package com.pramati;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pramati.webcrawler.Crawler;

public class CommonStringsBetweenFiles {
	
	static Logger LOGGER = LoggerFactory.getLogger(CommonStringsBetweenFiles.class);

	public static void main(String[] args) {

		try {
			LOGGER.info("Finding common strings between two files");
			BufferedReader firstFileBufferedReader = new BufferedReader(new FileReader("Input/firstFile.txt"));
			BufferedReader secondFileBufferedReader = new BufferedReader(new FileReader("Input/secondFile.txt"));
			BufferedWriter outputFileBufferedWriter =  new BufferedWriter(new FileWriter(new File("Output/commonStrings.txt")));
			MatchingStrings matchFound=new MatchingStrings(firstFileBufferedReader, secondFileBufferedReader, outputFileBufferedWriter );
			matchFound.find();
		} catch (FileNotFoundException e) {
			LOGGER.error("Got {} Exception in CommonStringsBetweenFiles", e)
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Got {} Exception in CommonStringsBetweenFiles", e)
			e.printStackTrace();
		}
	}
}
