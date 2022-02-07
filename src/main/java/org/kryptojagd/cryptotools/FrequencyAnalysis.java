package org.kryptojagd.cryptotools;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class FrequencyAnalysis {

	
	public static HashMap<String,Double> absoluteFrequency(String text) {
		
		HashMap<String,Double> hashmap = new HashMap<String, Double>();
		text = removeCharacters(text);
		
		char c = 'A';
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			hashmap.put(String.valueOf(letter), 0.0);
		}
		
		for (char letter : text.toCharArray()) {
			double a = hashmap.get(String.valueOf(letter)) + 1.0;
			hashmap.put(String.valueOf(letter), a);
		}
		
		return hashmap;
	}
	
public static LinkedHashMap<String,Double> relativeFrequency(String text) {
		
		LinkedHashMap<String,Double> hashmap = new LinkedHashMap<String, Double>();
		text = removeCharacters(text);
		double length = (double) text.length();
		
		char c = 'A';
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			hashmap.put(String.valueOf(letter), 0.0);
		}
		
		for (char letter : text.toCharArray()) {
			double a = hashmap.get(String.valueOf(letter)) + (1 / length) * 100;
			hashmap.put(String.valueOf(letter), a);
		}
		
		return hashmap;
	}
	
	
	
	public static LinkedHashMap<String, Double> germanLetterFrequency() {
		LinkedHashMap<String,Double> germanLetterFrequency = new LinkedHashMap<String, Double>();
		
    	germanLetterFrequency.put("E", 17.4);
    	germanLetterFrequency.put("N", 9.78);
    	germanLetterFrequency.put("I", 7.55);
    	germanLetterFrequency.put("S", 7.27);
    	germanLetterFrequency.put("R", 7.0);
    	germanLetterFrequency.put("A", 6.51);
    	germanLetterFrequency.put("T", 6.15);
    	germanLetterFrequency.put("D", 5.08);
    	germanLetterFrequency.put("H", 4.76);
    	germanLetterFrequency.put("U", 4.35);
    	germanLetterFrequency.put("L", 3.44);
    	germanLetterFrequency.put("C", 3.06);
    	germanLetterFrequency.put("G", 3.01);
    	germanLetterFrequency.put("M", 2.53);
    	germanLetterFrequency.put("O", 2.51);
    	germanLetterFrequency.put("B", 1.89);
    	germanLetterFrequency.put("W", 1.89);
    	germanLetterFrequency.put("F", 1.66);
    	germanLetterFrequency.put("K", 1.21);
    	germanLetterFrequency.put("Z", 1.13);
    	germanLetterFrequency.put("P", 0.79);
    	germanLetterFrequency.put("V", 0.67);
    	// germanLetterFrequency.put("ß", 0.31);
    	germanLetterFrequency.put("J", 0.27);
    	germanLetterFrequency.put("Y", 0.04);
    	germanLetterFrequency.put("X", 0.03);
    	germanLetterFrequency.put("Q", 0.02);
		
		return germanLetterFrequency;
	}
	
	private static String removeCharacters(String text) {
		
		text = text.replaceAll("[^A-Z]", "");
		return text;
	}

}
