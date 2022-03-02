package org.kryptojagd.cryptotools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kryptojagd.encryptionmethods.Caesar;
import org.kryptojagd.encryptionmethods.Vigenere;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * 
 * @author Michail Petermann
 *
 */
public class FrequencyAnalysisVigenereController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label encodedTextLabel;
    
    @FXML 
    private TabPane tabPane;
    
    @FXML
    private HBox hboxKey;
    
    @FXML
    private Button explanationButton;
    
    @FXML
    private Label labelDecodedText;
    
    @FXML
    private ComboBox<Integer> lengthCodeComboBox;
    
    @FXML
    private Label hintLabel;
    
    private BarChart[] barCharts;
    private BarChart[] overviewBarCharts;
    
    private static String text = "HIER SOLLTE DER KODIERTE TEXT STEHEN.";
    
    private String[] splittedText;
    
    private static ArrayList<LinkedHashMap<String, Double>> frequencies;
    
    private static LinkedHashMap<String, Double> germanLetterFrequency = 
    		FrequencyAnalysis.germanLetterFrequency();
    
    
    private Tab overviewTab = new Tab("Übersicht");
    
    private int widthBarChartInTab = 980;
    private int heightBarChartInTab = 440;
    private int widthVBoxInTab = 1020;
    private int heightVBoxInTab = 530;
    private int spacingVBoxInTab = 20;

    /**
     * 
     * @param event
     */
    @FXML
    void lengthCodeChanged(ActionEvent event) {
    	
    	frequencies = new ArrayList<LinkedHashMap<String, Double>>();
    	splittedText = new String[lengthCodeComboBox.getValue()];
    	initializeTextFields();
    	initializeCharts();
    	initializeOverview();
    }

    /**
     * 
     */
	@FXML
    public void initialize() {
    
    	encodedTextLabel.setText(text);
    	labelDecodedText.setText(text);
    	
    	ObservableList<Integer> options = 
    		    FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8, 9);
    	lengthCodeComboBox.setItems(options);  	   	

    }
    
	/**
	 * 
	 */
    private void initializeOverview() {
    	
		GridPane gridpane = new GridPane();
		
		for(int i = 0; i < lengthCodeComboBox.getValue(); i++) {   	
	    	int row = i / 2;
	    	int column = i % 2;
	    	gridpane.add(overviewBarCharts[i], column, row);
		}
    	
		ScrollPane scroll = new ScrollPane();
		scroll.setContent(gridpane);
		overviewTab.setContent(scroll);
    	tabPane.getTabs().add(overviewTab);
    }
    
    /**
     * 
     * @param i
     */
    private void updateOverview(int i) {
    	GridPane gridPane = (GridPane) ((ScrollPane) overviewTab.getContent()).getContent();
    	
    	int row = i / 2;
    	int column = i % 2;
    	Node result = null;
        for (Node node : gridPane.getChildren() ) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
            	result = node;           	
                break;
            }
        }
        gridPane.getChildren().remove(result);
    	gridPane.add(overviewBarCharts[i], column, row);
    }
    
    /**
     * Initializes all bar charts.
     */
    private void initializeCharts() {
    	
    	int length = lengthCodeComboBox.getValue();
    	barCharts = new BarChart[length];
    	overviewBarCharts = new BarChart[length];
    	
    	tabPane.getTabs().removeAll(tabPane.getTabs());
    	
    	for (int i = 0; i < length; i++) {
        	VBox vbox = new VBox();
        	vbox.setPrefSize(widthVBoxInTab, heightVBoxInTab);
    		vbox.setSpacing(spacingVBoxInTab);
    		vbox.getChildren().addAll(updateChart(i), createShiftButtons(i));
    		
    		Tab tab = new Tab( "Diagramm " + (i +1) );
    		tab.setContent(vbox);
    		tabPane.getTabs().add(tab);
    	}		
    }
    
    /**
     * Updates the number of text fields, when length of key is changed.
     */
    private void initializeTextFields() {
    	
    	hboxKey.getChildren().removeAll(hboxKey.getChildren());
    	for (int i = 0; i < lengthCodeComboBox.getValue(); i++) {
    		// for listener of text field
    		final int a = i;
    		TextField textFieldKey = new TextField("A");
    		textFieldKey.setMaxWidth(40);
    		textFieldKey.textProperty().addListener((observable, oldValue, newValue) -> changedTextFieldValue(a, oldValue, newValue));
    		textFieldKey.setOnMouseClicked(new EventHandler() {

				@Override
				public void handle(Event event) {
					textFieldKey.selectAll();
					
				}
    			
    		}); 		
    		hboxKey.getChildren().add(textFieldKey);
    	}
    	
    }
    
    /**
     * 
     * @param n
     * @return
     */
    private String getEveryNthLetter(int n) {
    	
    	String str = "";
    	String codedText = text.replaceAll("[^A-Z]", "");
    	
    	for (int i = n; i < codedText.length(); i += lengthCodeComboBox.getValue()) {
          str += codedText.substring(i, i+1);
    	}
    	
    	return str;
    }
    
    /**
     * 
     * @param i
     * @param oldValue
     * @param newValue
     */
    private void changedTextFieldValue(int i, String oldValue, String newValue) {
    	
    	// only capital letters allowed
    	Pattern p = Pattern.compile("[A-Z]");
    	Matcher m = p.matcher(newValue);
    	 
    	if (m.matches()) {
    		updateDecodedText(i); 	
    		hintLabel.setText("");
    	} else {
    		TextField f = (TextField) hboxKey.getChildren().get(i);
    		f.setText(oldValue);
    		
    		Node toRemove = null;
    		for (Node node : hboxKey.getChildren()) {
    			if (node instanceof Label) {
    				toRemove = node;
    			}
    		}
    		if (toRemove != null) {
    			hboxKey.getChildren().remove(toRemove);
    		}
    		
    		hintLabel.setText("Es sind nur Großbuchstaben (ohne Umlaute und ß) erlaubt.");
    	}	
    }
    
    /**
     * Reads the entered key from the text fields and decodes the given text with it.
     */
    private void updateDecodedText(int i) {
    	String s = ((TextField) hboxKey.getChildren().get(i)).getText();
    	labelDecodedText.setText(decodeNthText(i, s));
    }
    
    /**
     * 
     * @param n
     * @param s
     * @return
     */
    private String decodeNthText(int n, String s) {
    	
    	Caesar caesar = new Caesar();
    	String decodedText = labelDecodedText.getText();
    	
    	Pattern p = Pattern.compile("[A-Z]");
    	
    	int count = 0;
    	
    	for (int i = 0; i < decodedText.length(); i++) {
    		Matcher m = p.matcher(decodedText.subSequence(i, i+1));
    		
    		if (m.matches()) {
    			if (count == n) {
        			decodedText = decodedText.substring(0, i) 
        					+ caesar.decode(text.substring(i, i+1), s.charAt(0) - 'A') 
        					+ decodedText.substring(i+1);
        		}
    			count++;
    			if (count == lengthCodeComboBox.getValue()) {
    				count = 0;
    			}
    		}
    	}
    	return decodedText;
    }
    
    /**
     * 
     * @param rightShift
     * @param i
     */
    private void shiftRight(boolean rightShift, int i) {
    	frequencies.set(i, rotateHashMapRight(rightShift, frequencies.get(i)));
    	((VBox) tabPane.getTabs().get(i).getContent()).getChildren().set(0, updateChart(i));
    	updateOverview(i); 	
    }
    
    /**
     * 
     * @param shiftRight
     * @param map
     * @return
     */
    private LinkedHashMap<String, Double> rotateHashMapRight(boolean shiftRight, LinkedHashMap<String, Double> map) {
    	ArrayList<String> listString = new ArrayList<String>(map.keySet());
    	ArrayList<Double> listDouble = new ArrayList<Double>(map.values());
    	
    	if (shiftRight) {
    		Collections.rotate(listString, 1);
        	Collections.rotate(listDouble, 1);
    	} else {
    		Collections.rotate(listString, -1);
        	Collections.rotate(listDouble, -1);
    	}
    	
    	LinkedHashMap<String, Double> newMap = new LinkedHashMap<String, Double>();
    	
    	for (int i = 0; i < listString.size(); i++) {
    		newMap.put(listString.get(i), listDouble.get(i));
    	}
    	return newMap;
    }
    
    /**
     * Return a VBox with a bar chart.
     * @param i
     * @return
     */
    private AnchorPane updateChart(int i) {
    	
        splittedText[i] = getEveryNthLetter(i);
        LinkedHashMap<String, Double> splittedTextFrequency = FrequencyAnalysis.relativeFrequency(splittedText[i]);
        frequencies.add(splittedTextFrequency);
		
		barCharts[i] = FrequencyAnalysis.getChart("Häufigkeitsanalyse (" + (i+1) + ")", "Buchstaben (ohne Umlaute und ß)", 
				"relative Häufigkeit in %", frequencies.get(i), germanLetterFrequency, true, Color.RED);
		barCharts[i].setLegendVisible(false);
		barCharts[i].setPrefSize(widthBarChartInTab, heightBarChartInTab);
		FrequencyAnalysis.barChartColumnsColor(barCharts[i], "-fx-bar-fill: red;", "-fx-bar-fill: black;");
		
		
		overviewBarCharts[i] = FrequencyAnalysis.getChart("", "", "", frequencies.get(i), germanLetterFrequency, false, Color.BLACK);
		overviewBarCharts[i].setLegendVisible(false);
		overviewBarCharts[i].setPrefSize(515, 250);
		FrequencyAnalysis.barChartColumnsColor(overviewBarCharts[i], "-fx-bar-fill: red;", "-fx-bar-fill: black;");
		
		AnchorPane pane = new AnchorPane();
		pane.setPrefSize(widthBarChartInTab + 10, heightBarChartInTab);
		pane.getChildren().add(barCharts[i]);
		FrequencyAnalysis.lettersLabels(pane, germanLetterFrequency.keySet(), 88, 390, 34.25);
		
		return pane;
    }

    /**
     * 
     * @param i
     * @return
     */
    private HBox createShiftButtons(int i) {
 
    	HBox hb = new HBox();
    	hb.setAlignment(Pos.CENTER);
    	hb.setSpacing(15);
		
		Label lbl1 = new Label("A");
		Label lbl2 = new Label("→");
		Label lbl3 = new Label("A");
    	
    	final int a = i;
		Button buttonShiftLeft = new Button("<<");
		buttonShiftLeft.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event event) {
				shiftRight(false, a);
				String s = lbl3.getText();
		        char letter = s.charAt(0);
		        if (letter == 'A') {
		        	lbl3.setText("Z");
		        } else {
		        	lbl3.setText(Character.toString(--letter));
		        }
			}
		});
		
		Button buttonShiftRight = new Button(">>");
		buttonShiftRight.setOnMouseClicked(new EventHandler() {
			@Override
			public void handle(Event event) {
				shiftRight(true, a);	
				
				String s = lbl3.getText();
		        char letter = s.charAt(0);
		        if (letter == 'Z') {
		        	lbl3.setText("A");
		        } else {
		        	lbl3.setText(Character.toString(++letter));
		        }
			}
		});
		
		hb.getChildren().addAll(buttonShiftLeft, lbl1, lbl2, lbl3, buttonShiftRight);
		return hb;
    }

	/**
	 * Adds spaces between the strings with the same structure as a given text
	 *
	 * @param stringsWithSpaces is a text with spaces between words
	 * @param stringsWithout is a text without spaces between words
	 * @return stringWithout text with spaces in the same place as the spaces in the given text
	 */
    public static String addSpacesAs(String stringsWithSpaces, String stringsWithout) {
    	StringBuilder newString = new StringBuilder();
    	String[] tokens = stringsWithSpaces.split(" ");
    	int lastWordIndex = tokens.length - 1;
		int beginIndex = 0;
		int endIndex = 0;

		for (int i = 0; i < lastWordIndex; i++) {
			beginIndex = endIndex;
			endIndex = tokens[i].length() + endIndex;

			String currentWord = stringsWithout.substring(beginIndex, endIndex);
			newString.append(currentWord).append(" ");
		}
		String lastWord = stringsWithout.substring(endIndex);
		newString.append(lastWord);

		return newString.toString();
	}
    
    /**
     * Sets the text that should be encoded.
     * @param text that should be encoded
     */
    public static void setText(String text) {
    	FrequencyAnalysisVigenereController.text = text;
    }
   
}
