package com.pramati;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MatchingStrings {

	BufferedReader firstFileBufferedReader = null;
	BufferedReader secondFileBufferedReader = null;
	BufferedWriter outputFileBufferedWriter =  null;
	
	static Logger LOGGER = LoggerFactory.getLogger(CommonStringsBetweenFiles.class);

	public MatchingStrings(BufferedReader firstFileBufferedReader, BufferedReader secondFileBufferedReader, BufferedWriter outputFileBufferedWriter){
		this.firstFileBufferedReader=firstFileBufferedReader;
		this.secondFileBufferedReader=secondFileBufferedReader;
		this.outputFileBufferedWriter=outputFileBufferedWriter;
	}

	public void find() { 

		Set<String> firstFileStrings = new HashSet<>();
		
		try {

			String CurrentLineF1, CurrentLineF2;

			while ((CurrentLineF1 = firstFileBufferedReader.readLine()) != null) {
				String[] temp=CurrentLineF1.toLowerCase().split(" ");
				StringBuilder tempString=new StringBuilder().append(temp[0]).append(temp[temp.length-1]);
				firstFileStrings.add(tempString.toString());
			}

			while ((CurrentLineF2 = secondFileBufferedReader.readLine()) != null) {
				String[] temp=CurrentLineF2.toLowerCase().split(" ");
				StringBuilder tempString=new StringBuilder().append(temp[0]).append(temp[temp.length-1]);
				if(firstFileStrings.contains(tempString.toString()))
					outputFileBufferedWriter.write(CurrentLineF2+"\n");
					LOGGER.info("Found common string {}",CurrentLineF2);

			}

		} catch (IOException e) {
			LOGGER.error("Got {} Exception in MatchingStrings", e)
			e.printStackTrace();
		} finally {
			try {
				if (firstFileBufferedReader != null)firstFileBufferedReader.close();
				if (secondFileBufferedReader != null)secondFileBufferedReader.close();
				if (outputFileBufferedWriter != null)outputFileBufferedWriter.close();
			} catch (IOException ex) {
				LOGGER.error("Got {} Exception in MatchingStrings", e)
				ex.printStackTrace();
			}
		}

	}
}
