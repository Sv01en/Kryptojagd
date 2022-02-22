package org.kryptojagd.cryptotools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

public class VigenereBreakController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label label1;
    
    @FXML 
    private TabPane tabPane;
    
    @FXML
    private HBox hboxKey;
    
    @FXML
    private Button hintButton;
    
    @FXML
    private Label labelEncodedText;
    
    @FXML
    private ComboBox<Integer> lengthCodeComboBox;
    
    private BarChart[] barCharts;
    
//    private static String text = "HALLOICHBINEINKLEINERBLINDTEXTUNDZWARSCHONSOLANGE"
//    		+ "ICHDENKENKANNESWARNICHTLEICHTZUVERSTEHENWASESBEDEUTETEINBLINDERTEXT"
//    		+ "ZUSEINMANERGIBTKEINENSINN";
//           WIRKLICH KEINEN SINN MAN WIRD ZUSAMMENHANGSLOS EINGESCHOBEN "
//    		+ "UND RUMGEDREHT UND OFTMALS GAR NICHT ERST GELESEN ABER BIN ICH ALLEIN DESHALB EIN "
//    		+ "SCHLECHTERER TEXT ALS ANDERE NA GUT ICH WERDE NIE IN DEN BESTSELLERLISTEN STEHEN ABER "
//    		+ "ANDERE TEXTE SCHAFFEN DAS AUCH NICHT UND DARUM STÖRT ES MICH NICHT BESONDERS BLIND ZU SEIN "
//    		+ "UND SOLLTEN SIE DIESE ZEILEN NOCH IMMER LESEN SO HABE ICH ALS KLEINER BLINDTEXT ETWAS GESCHAFFT"
//    		+ " WOVON ALL DIE RICHTIGEN UND WICHTIGEN TEXTE MEIST NUR TRAEUMEN";
    
    private static String text = "AEDEHMUAUMFXBRCEXMFXKFDBGHLXQXMGWDOTKWUAHRKHEEFZXMUAWIF"
    		+ "DXRCTGRWLPEJGBGZMEIAVAXRNOIJLMIZXGASLXWTXWIMMXXWBGFDBGHWKMIPMSYKXBRETGIJZBFLDXMFXGWAGG";
    
   
    // TODO überflüssig
    private String[] splittedText;
    
    private static ArrayList<LinkedHashMap<String, Double>> frequencies;
    
    // private HBox hbox;
    
    private static LinkedHashMap<String, Double> germanLetterFrequency = 
    		FrequencyAnalysis.germanLetterFrequency();

    @FXML
    void lengthCodeChanged(ActionEvent event) {
    	
    	frequencies = new ArrayList<LinkedHashMap<String, Double>>();
    	splittedText = new String[lengthCodeComboBox.getValue()];
    	updateTextFields();
    	updateCharts();
    }
    
    @FXML
    public void initialize() {
    	
    	germanLetterFrequency = FrequencyAnalysisController.sortAlphabetic(true, germanLetterFrequency);
    	
    	label1.setText(text);
    	labelEncodedText.setText(text);
    	
    	ObservableList<Integer> options = 
    		    FXCollections.observableArrayList(2, 3, 4, 5, 6, 7, 8, 9, 10);
    	lengthCodeComboBox.setItems(options);
    	
    	
    	
    	ArrayList<BarChart<String, Number>> barChartList = new ArrayList<BarChart<String, Number>>(); 

    }
    
    private void updateCharts() {
    	
    	// hbox.getChildren().removeAll(hbox.getChildren());
    	barCharts = new BarChart[lengthCodeComboBox.getValue()];
    	tabPane.getTabs().removeAll(tabPane.getTabs());
    	
    	for (int i = 0; i < lengthCodeComboBox.getValue(); i++) {
    		VBox vbox = updateChart(i);
    		Tab tab = new Tab("Diagramm " + (i +1));
    		tab.setContent(vbox);
    		tabPane.getTabs().add(tab);
    	}
    	
    	Tab tab = new Tab("Übersicht");
		tabPane.getTabs().add(tab);
		
		System.out.println(barCharts.length);
		
		BarChart<String,Number> bc = new BarChart<String,Number>(barCharts[0].getXAxis(),barCharts[0].getYAxis());
		bc.setPrefSize(200, 200);
		
		GridPane gridpane = new GridPane();
    	gridpane.add(new Label("Hi"), 0, 0); // Spalte, Zeile
    	gridpane.add(bc, 0, 1);
    	gridpane.add(new Label("Salut"), 0, 2);
    	gridpane.add(new Label("Hey"), 1, 0);
    	
    	tab.setContent(gridpane);
    	
    	
    }
    
    private void updateTextFields() {
    	
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
    
    private String getNthText(int n) {
    	
    	String str = "";
    	String codedText = text.replace(" ", "");
    	
    	for (int i = n; i < codedText.length(); i += lengthCodeComboBox.getValue()) {
          str += codedText.substring(i, i+1);
    	}
    	
    	// System.out.println(str);
    	return str;
    }
    
    private void changedTextFieldValue(int i, String oldValue, String newValue) {
    	
    	// only capital letters allowed
    	Pattern p = Pattern.compile("[A-Z]");
    	Matcher m = p.matcher(newValue);
    	// System.out.println("NewValue:" + newValue + "End");
    	 
    	if (m.matches()) {
    		
    		// TODO 
//    		Caesar caesar = new Caesar();
//    		int c = newValue.toCharArray()[0] - 'A';
//    		splittedText[i] = caesar.decode(splittedText[i], c);
    		Vigenere vigenere = new Vigenere();
    		// vigenere.decode(oldValue, key);
    		// 
    		updateDecodedText();
    		
    	} else {
    		TextField f = (TextField) hboxKey.getChildren().get(i);
    		f.setText(oldValue);
    		//f.selectAll();
    		
    		Node toRemove = null;
    		for (Node node : hboxKey.getChildren()) {
    			if (node instanceof Label) {
    				toRemove = node;
    			}
    		}
    		if (toRemove != null) {
    			hboxKey.getChildren().remove(toRemove);
    		}
    		
    		Label l = new Label("  Es sind nur Großbuchstaben erlaubt.");
    		l.setAlignment(Pos.BASELINE_CENTER);
    		hboxKey.getChildren().add(l);
    	}
    	
    		
    	
    }
    
    private void updateDecodedText() {
    	
    	String str = "";
    	
    	for (Node n : hboxKey.getChildren()) {
    		if (n instanceof TextField) {
    			str += ((TextField) n).getText(); 
    		}
    	}
    	
    	Vigenere vigenere = new Vigenere();
    	str = vigenere.decode(text, str);
    	labelEncodedText.setText(str);
    }
    
    private AnchorPane letterLabels(AnchorPane pane) {

    	int count = 0;
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			Label label123 = new Label(Character.toString(letter));
 			label123.setStyle("-fx-font-size: 8pt; "
 					+ "-fx-font-weight: bold}");
 			pane.getChildren().add(label123);
 			label123.relocate(88 + 34.3*count, 390);
 			count++;
		}
    	
    	return pane;
    }
    
    public void removeLettersLabels(AnchorPane pane) {
    	// TODO Schöner machen
    	
    	ArrayList<Node> nodesToRemove = new ArrayList<Node>();
    	
    	for (Node n : pane.getChildren()) {
    		if (n.getStyle().equals("-fx-font-size: 8pt; "+ "-fx-font-weight: bold}")) {
    			nodesToRemove.add(n);
    		}
    	}
    	
    	for (Node n : nodesToRemove) {
    		pane.getChildren().remove(n);
    	}
    	
    	nodesToRemove = null;
    	
    	// Todo Ende   
    }
    
    private void shiftRight(boolean rightShift, int i) {
    	VBox v = (VBox) tabPane.getTabs().get(i).getContent();
    	AnchorPane a = (AnchorPane) v.getChildren().get(0);
    	frequencies.set(i, rotateHashMapRight(rightShift, frequencies.get(i)));
    	// hbox.getChildren().set(i, updateChart(i));
    	tabPane.getTabs().get(i).setContent(updateChart(i));
    	
    }
    
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
    	System.out.println(newMap);
    	return newMap;
    }
    
    private VBox updateChart(int i) {
    	
    	VBox vbox = new VBox();
    	vbox.setPrefSize(1020, 530);
		vbox.setSpacing(20);

		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        barCharts[i] = 
            new BarChart<String,Number>(xAxis,yAxis);
        
        barCharts[i].setTitle("Häufigkeitsanalyse (" + (i +1) + ")");
        xAxis.setLabel("Buchstaben");       
        yAxis.setLabel("Häufigkeit");  
        
        xAxis.setTickLabelFill(Color.RED);
        
        splittedText[i] = getNthText(i);
        LinkedHashMap<String, Double> hashmap = FrequencyAnalysis.relativeFrequency(splittedText[i]);
        frequencies.add(hashmap);
       
        XYChart.Series series1 = new XYChart.Series(); 
        XYChart.Series series2 = new XYChart.Series();
        
        series1.setName("deutschsprachige Texte");
		series2.setName("vorliegender Text");
		
		ArrayList<String> list1String = new ArrayList<String>();
        ArrayList<Double> list1Double = new ArrayList<Double>();
		
		for (String key : frequencies.get(i).keySet()) {
			list1String.add(key);
			list1Double.add(frequencies.get(i).get(key));
        }
        
        ArrayList<String> list2String = new ArrayList<String>();
        ArrayList<Double> list2Double = new ArrayList<Double>();
        		
		for (String key : germanLetterFrequency.keySet()) {
        	// series1.getData().add(new XYChart.Data(key, germanLetterFrequency.get(key)));
			list2String.add(key);
			list2Double.add(germanLetterFrequency.get(key));
        }
		
		for (int j = 0; j < list1String.size(); j++) {
			series1.getData().add(new XYChart.Data(list1String.get(j) + "\n\n", list1Double.get(j)));
			series2.getData().add(new XYChart.Data(list1String.get(j) + "\n\n", list2Double.get(j)));
		}
     
		
		barCharts[i].getData().addAll(series1, series2);
		barCharts[i].setLegendVisible(false);
		barCharts[i].setPrefSize(980, 440);
		
		for(Node n:barCharts[i].lookupAll(".default-color0.chart-bar")) {
	            n.setStyle("-fx-bar-fill: red;");
	        }
	  
 	    for(Node n:barCharts[i].lookupAll(".default-color1.chart-bar")) {
 	            n.setStyle("-fx-bar-fill: black;");
 	        }
		
 	    final int a = i;
		Button buttonShiftLeft = new Button("<<");
		buttonShiftLeft.setOnMouseClicked(new EventHandler() {

			@Override
			public void handle(Event event) {
				
				shiftRight(false, a);
				
				
				
			}
		
		});
		
		Button buttonShiftRight = new Button(">>");
		buttonShiftRight.setOnMouseClicked(new EventHandler() {

			@Override
			public void handle(Event event) {
				shiftRight(true, a);	
			}
		
		});
		
		
		
		Label lbl1 = new Label("A");
		Label lbl2 = new Label("→");
		Label lbl3 = new Label("A");
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(15);
		hb.getChildren().addAll(buttonShiftLeft, lbl1, lbl2, lbl3, buttonShiftRight);
		
		AnchorPane pane = new AnchorPane();
		pane.setPrefSize(990, 440);
		pane.getChildren().add(barCharts[i]);
		pane = letterLabels(pane);
		vbox.getChildren().addAll(pane, hb);
		return vbox;
    }
   
}
