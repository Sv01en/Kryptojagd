package org.kryptojagd.controls;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import org.kryptojagd.cryptotools.FrequencyAnalysis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * Controller of the frequency analysis of Caesar crypto tool.
 * 
 * @author Michail Petermann
 *
 */
public class FrequencyAnalysisCaesarController {
  
  @FXML
  private AnchorPane pane;

  @FXML
  private Label label1;
    
  @FXML
  private TabPane tabPane;

  private static String text = "HIER SOLLTE DER KODIERTE TEXT STEHEN.";
  private static LinkedHashMap<String, Double> textLetterFrequency;
  private static LinkedHashMap<String, Double> germanLetterFrequency = 
    		FrequencyAnalysis.germanLetterFrequency();
  private BarChart<String,Number> barChart;
  private int positionBarChartX = 14;
  private int positionBarChartY = 130;
  private int widthBarChart = 1000;
  private int heightBarChart = 500;
        
  /**
    * Initializes the frequency analysis window.
    */
  @FXML
  public void initialize() {
    FrequencyAnalysis.lettersLabels(pane, germanLetterFrequency.keySet(), 105, 575,  34.7);
    label1.setText(text);
   
	textLetterFrequency = FrequencyAnalysis.relativeFrequency(text);
	updateChart();

  }   

    
  /**
   * Shifts the letters of the bar chart to the left.
   * 
   * @param event click on shift left button
   */
  @FXML
  void shiftLeft(ActionEvent event) {
    shift(false);
  }
    
  /**
    * Shifts the letters of the bar chart to the right.
    * @param event click on shift right button
    */
  @FXML
  void shiftRight(ActionEvent event) {
    shift(true);
  }
    
  /**
    * Shifts the letters of the bar chart to the right or left.
    * @param right if true, shifts to right, else to the left
    */
  private void shift(boolean right) {
    ArrayList<String> listString = new ArrayList<String>(textLetterFrequency.keySet());
    ArrayList<Double> listDouble = new ArrayList<Double>(textLetterFrequency.values());
    	
    if (right) {
      Collections.rotate(listString, 1);
      Collections.rotate(listDouble, 1);
    } else {
      Collections.rotate(listString, -1);
      Collections.rotate(listDouble, -1);
    }
    textLetterFrequency = new LinkedHashMap<String, Double>();
    
    for (int i = 0; i < listString.size(); i++) {
      textLetterFrequency.put(listString.get(i), listDouble.get(i));
    }
    pane.getChildren().remove(barChart);
    updateChart();	
  }
    
  /**
   * Updates the bar chart.
   */
  private void updateChart() {
    barChart = FrequencyAnalysis.getChart("Häufigkeitsanalyse", "Buchstaben (ohne Umlaute und ß)", 
    		"relative Häufigkeit in %", textLetterFrequency, germanLetterFrequency, true, Color.RED);
	barChart.setLegendVisible(false);
    barChart.relocate(positionBarChartX, positionBarChartY);
	barChart.setPrefSize(widthBarChart, heightBarChart);
	  FrequencyAnalysis.barChartColumnsColor(barChart, "-fx-bar-fill: red;", "-fx-bar-fill: black;");
    pane.getChildren().add(barChart);
  }
   
  /**
   * Sets the text that should be encoded.
   * @param text that should be encoded
   */
  public static void setText(String text) {
    FrequencyAnalysisCaesarController.text = text;
  }

}
