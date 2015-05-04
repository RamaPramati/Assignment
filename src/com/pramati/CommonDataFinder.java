package com.pramati;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class CommonDataFinder {

	private static final Logger LOGGER = Logger.getLogger(CommonDataFinder.class.getName());

	public Set<String> findCommonData(MatchType matchType, Set<String> firstFileStrings, Set<String> secondFileStrings) { 

		LOGGER.info("comparing data.....");
		Set<String> commonStrings = new HashSet<String>();
		Set<String> tempStrings = new HashSet<String>();
		Iterator<String> firstFileStringsIterator = firstFileStrings.iterator();
		Iterator<String> secondFileStringsIterator = secondFileStrings.iterator();
		String tempString = null;

		if(matchType == MatchType.firstAndLastNameMatch) {

			StringBuilder constructedString=new StringBuilder();

			while (firstFileStringsIterator.hasNext()) {
				tempString= firstFileStringsIterator.next().toString().toLowerCase();
				String[] tempStringForFirstFileStrings=tempString.split(" ");
				constructedString = constructedString.append(tempStringForFirstFileStrings[0]).append(tempStringForFirstFileStrings[tempStringForFirstFileStrings.length-1]);
				tempStrings.add(constructedString.toString());
			}

			while (secondFileStringsIterator.hasNext()) {
				tempString= secondFileStringsIterator.next().toString().toLowerCase();
				String[] tempStringForSecondFileStrings=tempString.split(" ");
				constructedString= constructedString.append(tempStringForSecondFileStrings[0]).append(tempStringForSecondFileStrings[tempStringForSecondFileStrings.length-1]);
				if(tempStrings.contains(constructedString.toString()))
					commonStrings.add(tempString);
			}

		}

		while (firstFileStringsIterator.hasNext()) {
			tempString= firstFileStringsIterator.next().toString().toLowerCase();
			tempStrings.add(tempString);
		}

		while (secondFileStringsIterator.hasNext()) {
			tempString= secondFileStringsIterator.next().toString().toLowerCase();
			if(tempStrings.contains(tempString))
				commonStrings.add(tempString);
		}    	

		return commonStrings; 
	}
}