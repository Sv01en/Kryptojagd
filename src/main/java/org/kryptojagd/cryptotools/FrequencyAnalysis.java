package org.kryptojagd.cryptotools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * 
 * @author Michail Petermann
 *
 */
public class FrequencyAnalysis {
	
	/**
	 * Returns the relative letter frequency of the given text.
	 * @param text
	 * @return
	 */
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
	
	
	/**
	 * Returns the relative letter frequency of German texts.
	 * @return
	 */
	public static LinkedHashMap<String, Double> germanLetterFrequency() {
		
		LinkedHashMap<String,Double> germanLetterFrequency = new LinkedHashMap<String, Double>();
		
		germanLetterFrequency.put("A", 6.51);
		germanLetterFrequency.put("B", 1.89);
    	germanLetterFrequency.put("C", 3.06);
    	germanLetterFrequency.put("D", 5.08);
		germanLetterFrequency.put("E", 17.4);
    	germanLetterFrequency.put("F", 1.66);
    	germanLetterFrequency.put("G", 3.01);
    	germanLetterFrequency.put("H", 4.76);
    	germanLetterFrequency.put("I", 7.55);
    	germanLetterFrequency.put("J", 0.27);
    	germanLetterFrequency.put("K", 1.21);
    	germanLetterFrequency.put("L", 3.44);
    	germanLetterFrequency.put("M", 2.53);    	
		germanLetterFrequency.put("N", 9.78);
		germanLetterFrequency.put("O", 2.51);
		germanLetterFrequency.put("P", 0.79);
		germanLetterFrequency.put("Q", 0.02);
		germanLetterFrequency.put("R", 7.0);
    	germanLetterFrequency.put("S", 7.27);
    	germanLetterFrequency.put("T", 6.15);
    	germanLetterFrequency.put("U", 4.35);
    	germanLetterFrequency.put("V", 0.67);
    	germanLetterFrequency.put("W", 1.89);
    	germanLetterFrequency.put("X", 0.03);
    	germanLetterFrequency.put("Y", 0.04);
    	germanLetterFrequency.put("Z", 1.13);
    	// germanLetterFrequency.put("ß", 0.31);

		return germanLetterFrequency;
	}
	
	/**
	 * Removes all characters that are not capital letters
	 * @param text
	 * @return
	 */
	private static String removeCharacters(String text) {
		
		text = text.replaceAll("[^A-Z]", "");
		return text;
	}
	
	public static void barChartColumnsColor(BarChart barChart, String chart0, String chart1) {
		   for(Node n: barChart.lookupAll(".default-color0.chart-bar")) {
	            n.setStyle(chart0);
	        }
		 
	   for(Node n: barChart.lookupAll(".default-color1.chart-bar")) {
	            n.setStyle(chart1);
	        }
	}
	
	/**
	 * Returns the series for the bar chart.
	 * @param textLetterFrequency
	 * @param germanLetterFrequency
	 * @return
	 */
	private static Series[] getSeries(LinkedHashMap<String, Double> textLetterFrequency, 
			LinkedHashMap<String, Double> germanLetterFrequency, boolean xAxisTicksData1) {
		
		XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        
        series1.setName("deutschsprachige Texte");
		series2.setName("vorliegender Text");
		
		ArrayList<String> list1String = new ArrayList<String>();
        ArrayList<Double> list1Double = new ArrayList<Double>();
		
		for (String key : textLetterFrequency.keySet()) {
			list1String.add(key);
			list1Double.add(textLetterFrequency.get(key));
        }
        
        ArrayList<String> list2String = new ArrayList<String>();
        ArrayList<Double> list2Double = new ArrayList<Double>();
        		
		for (String key : germanLetterFrequency.keySet()) {
			list2String.add(key);
			list2Double.add(germanLetterFrequency.get(key));
        }	
		
		if (xAxisTicksData1) {
			for (int i = 0; i < list1String.size(); i++) {
				series1.getData().add(new XYChart.Data(list1String.get(i) + "\n\n", list1Double.get(i)));
				series2.getData().add(new XYChart.Data(list1String.get(i) + "\n\n", list2Double.get(i)));
			}
		} else {
			for (int i = 0; i < list1String.size(); i++) {
				series1.getData().add(new XYChart.Data(list2String.get(i) + "\n\n", list1Double.get(i)));
				series2.getData().add(new XYChart.Data(list2String.get(i) + "\n\n", list2Double.get(i)));
			}
		}
		
		Series[] series = {series1, series2};
		return series;
	}
	
    /**
     * Sorts the given hash map in alphabetic or decreasing order.
     * @param alphabetic Indicates whether the hash map should be sorted in alphabetic or decreasing order.
     * @param hashmap that should be ordered 
     * @return The sorted hash map
     */
    public static LinkedHashMap<String, Double> sortAlphabetic(boolean alphabetic, HashMap<String, Double> hashmap) {
    	List<Entry<String, Double>> list = new LinkedList<>(hashmap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> alphabetic ? 
        		( o1.getKey().compareTo(o2.getKey()) == 0 ? 
        				o1.getValue().compareTo(o2.getValue()) : o1.getKey().compareTo(o2.getKey()) ) 
        		
        		: ( o2.getValue().compareTo(o1.getValue()) == 0 ? 
        				o2.getKey().compareTo(o1.getKey()) : o2.getValue().compareTo(o1.getValue())) );
        
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
    
    /**
     * 
     * @param pane
     * @param yStart
     */
    public static void lettersLabels(Pane pane, Set<String> set, int xStart, int yStart, double distance) {
    	
		int count = 0;
		for (String key : set ) {
			Label label = new Label(key);
 			label.setStyle("-fx-font-size: 8pt; "
 					+ "-fx-font-weight: bold}");
 			pane.getChildren().add(label);
 			label.relocate( xStart + distance * count, yStart );
 			count++;
		}
    	
    }
    
    /**
     * 
     * @param title
     * @param xLabel
     * @param ylabel
     * @param textLetterFrequency
     * @param germanLetterFrequency
     * @return
     */
    public static BarChart getChart(String title, String xLabel, String ylabel, 
    		LinkedHashMap<String, Double> textLetterFrequency, LinkedHashMap<String, 
    		Double> germanLetterFrequency, boolean xAxisTicksData1, Color tickColor) {

    	CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
    	
        BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
        
    	barChart.setTitle(title);
        xAxis.setLabel(xLabel); 
        
        yAxis.setLabel(ylabel);     
        xAxis.setTickLabelFill(tickColor);
    	
    	Series[] series = FrequencyAnalysis.getSeries(textLetterFrequency, germanLetterFrequency, xAxisTicksData1);
        
		barChart.getData().addAll(series[0], series[1]);
		
	    return barChart;
    }

}
