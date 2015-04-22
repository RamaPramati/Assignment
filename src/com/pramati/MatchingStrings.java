package com.pramati;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MatchingStrings {

	BufferedReader firstFileBufferedReader = null;
	BufferedReader secondFileBufferedReader = null;
	BufferedWriter outputFileBufferedWriter =  null;

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
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (firstFileBufferedReader != null)firstFileBufferedReader.close();
				if (secondFileBufferedReader != null)secondFileBufferedReader.close();
				if (outputFileBufferedWriter != null)outputFileBufferedWriter.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
