package org.kryptojagd.cryptotools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
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
    
    @FXML
    private Button buttonShiftLeft;

    @FXML
    private Button buttonShiftRight;
    
    @FXML
    private Label labelShift;
    
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
     * 
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
     * @param event
     */
    @FXML
    void shiftLeft(ActionEvent event) {
    	String s = labelShift.getText();
        char letter = s.charAt(0);
        if (letter == 'A') {
        	labelShift.setText("Z");
        } else {
        	labelShift.setText(Character.toString(--letter));
        }
        shift(false);
    }
    
    /**
     * Shifts the letters of the bar chart to the right.
     * @param event
     */
    @FXML
    void shiftRight(ActionEvent event) {
        String s = labelShift.getText();
        char letter = s.charAt(0);
        if (letter == 'Z') {
        	labelShift.setText("A");
        } else {
        	labelShift.setText(Character.toString(++letter));
        }
        shift(true);
    }
    
    /**
     * Shifts the letters of the bar chart to the right or left.
     * @param right
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
     * 
     */
    private void updateChart() {
    	
    	barChart = FrequencyAnalysis.getChart("Häufigkeitsanalyse", "Buchstaben (ohne Umlaute und ß)", "relative Häufigkeit in %", 
    			textLetterFrequency, germanLetterFrequency, true, Color.RED);
		
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
