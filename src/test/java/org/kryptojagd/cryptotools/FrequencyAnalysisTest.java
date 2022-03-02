package org.kryptojagd.cryptotools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

public class FrequencyAnalysisTest {
	
	@Test
	void testrelativeFrequency() {
		String text = "A123!!BBBB???ÄÖÜ-CCZZZ";
		LinkedHashMap<String,Double> hashmap = new LinkedHashMap<String, Double>();
		hashmap.put("A", 10.0);
		hashmap.put("B", 40.0);
		hashmap.put("C", 20.0);
		for (char letter = 'D'; letter <= 'Y'; letter++) {
			hashmap.put(String.valueOf(letter), 0.0);
		}
		hashmap.put("Z", 30.0);
        assertEquals(hashmap, FrequencyAnalysis.relativeFrequency(text));
    } 

}
