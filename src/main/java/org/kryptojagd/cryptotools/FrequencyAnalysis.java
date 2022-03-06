package org.kryptojagd.cryptotools;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

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
 * This class provides tools for frequency analysis.
 * 
 * @author Michail Petermann
 *
 */
public class FrequencyAnalysis {
	
  /**
   * Returns the relative letter frequency of the given text.
   * @param text whose relative letter frequency must be specified
   * @return the relative letter frequency of the given text as a linked hash map.
   */
  public static LinkedHashMap<String,Double> relativeFrequency(String text) {
    LinkedHashMap<String,Double> hashmap = new LinkedHashMap<String, Double>();
	text = removeCharacters(text);
	double length = (double) text.length();
	
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
   * @return the relative letter frequency of German texts as a hash map
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
	return germanLetterFrequency;
  }
	
  /**
   * Removes all characters that are not capital letters
   * @param text in which the non alphabetic and non capital characters will be removed.
   * @return text with only capital characters
   */
  private static String removeCharacters(String text) {
	
    text = text.replaceAll("[^A-Z]", "");
	return text;
  }
	
	/**
	 * Changes the color of the first columns of the bar chart to color0 and the second
	 * columns to color1.
	 * @param barChart
	 * @param chart0
	 * @param chart1
	 */
	public static void barChartColumnsColor(BarChart barChart, String color0, String color1) {
		   for(Node n: barChart.lookupAll(".default-color0.chart-bar")) {
	            n.setStyle(color0);
	        }
		 
	   for(Node n: barChart.lookupAll(".default-color1.chart-bar")) {
	            n.setStyle(color1);
	        }
	}
	
  /**
   * Returns the series for the bar chart.
   * @param textLetterFrequency the letter frequency of the given text
   * @param germanLetterFrequency the letter frequency of the German texts
   * @return array of type series of length 2
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
   * Sets letter labels on the given pane that indicate the German letter frequency
   * of a particular letter.
   * @param pane where the labels will be placed
   * @param set of the letters that will be placed in the labels
   * @param xStart x-coordinate of the first label
   * @param yStart y-coordinate of the labels
   * @param distance between the labels
   */
  public static void lettersLabels(Pane pane, Set<String> set, int xStart, int yStart, double distance) {
	int count = 0;
	for (String key : set ) {
		Label label = new Label(key);
		label.setStyle("-fx-font-size: 8pt; "
				+ "-fx-font-weight: bold;"
				+ "-fx-font-family: 'monospace'");
		pane.getChildren().add(label);
		label.relocate( xStart + distance * count, yStart );
		count++;
	}
	
  }
    
  /**
   * Returns a bar chart.
   * @param title of the bar chart
   * @param xLabel label of the x-axis
   * @param ylabel label of the y-axis
   * @param textLetterFrequency the relative letter frequency of the given text
   * @param germanLetterFrequency the relative letter frequency of German texts
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

	barChart.setStyle("-fx-font-family: 'monospace'");

	xAxis.setStyle("-fx-font-family: 'monospace'");

	yAxis.setStyle("-fx-font-family: 'monospace'");

	return barChart;
  }

}
