package com.pramati;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonDataFinder {
	
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	Set<String> firstFileStrings;
	Set<String> secondFileStrings;
	
	public CommonDataFinder(Set<String> firstFileStrings, Set<String> secondFileStrings){
		this.firstFileStrings = new HashSet<String>(firstFileStrings);
		this.secondFileStrings = new HashSet<String>(secondFileStrings);
	}

	public Set<String> findCommonData() { 
	    LOGGER.setLevel(Level.INFO);
	    LOGGER.info("comparing data.....");

	    Set<String> tempStrings = new HashSet<String>();
		Set<String> commonStrings = new HashSet<String>();

		Iterator<String> firstFileStringsIterator = firstFileStrings.iterator();
		Iterator<String> secondFileStringsIterator = secondFileStrings.iterator();

		while (firstFileStringsIterator.hasNext()) {
			String tempString= firstFileStringsIterator.next().toString();
			String[] tempStringForFirstFileStrings=tempString.toLowerCase().split(" ");
			StringBuilder constructedStringForFirstFileStrings=new StringBuilder().append(tempStringForFirstFileStrings[0]).append(tempStringForFirstFileStrings[tempStringForFirstFileStrings.length-1]);
			tempStrings.add(constructedStringForFirstFileStrings.toString());
		}

		while (secondFileStringsIterator.hasNext()) {
			String tempString= secondFileStringsIterator.next().toString();
			String[] tempStringForSecondFileStrings=tempString.toLowerCase().split(" ");
			StringBuilder constructedStringForSecondFileStrings=new StringBuilder().append(tempStringForSecondFileStrings[0]).append(tempStringForSecondFileStrings[tempStringForSecondFileStrings.length-1]);
			if((tempStrings.contains(constructedStringForSecondFileStrings.toString())))
				commonStrings.add(tempString);
		}

		return commonStrings; 
	}
}