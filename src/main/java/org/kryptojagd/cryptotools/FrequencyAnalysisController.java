package org.kryptojagd.cryptotools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The type Frequency analysis controller.
 */
public class FrequencyAnalysisController {
	
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
    
    private static String text = "Kodierter Text";
    
    private static LinkedHashMap<String, Double> textLetterFrequency;
    
    private static LinkedHashMap<String, Double> germanLetterFrequency = 
    		FrequencyAnalysis.germanLetterFrequency();
        
    //@FXML
    //private GridPane table;
    
    // TODO Design kann überarbeitet werden 
    
    // TODO Falls übergebener Text eine bestimmte Länge überschreitet, soll der restliche Text im Label durch ... dargestellt werden.
    
    // TODO Siehe unten

	/**
	 * Initialize.
	 */
	@FXML
    public void initialize() {
    	
    	germanLetterFrequency = sortAlphabetic(true, germanLetterFrequency);
    	
    	lettersLabels(pane, 575);
 	   
        
    	label1.setText(text);
   
		textLetterFrequency = FrequencyAnalysis.relativeFrequency(text);
		
		
		updateChart();

    }   

    
    /**
     * 
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
        // shift(true);
        shift(false);
    }
    
    /**
     * 
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
        // shift(false);
        shift(true);
    }
    
    /**
     * 
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
    	
    	updateChart();
    	
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
        list.sort((o1, o2) -> alphabetic
				? (o1.getKey().compareTo(o2.getKey()) == 0
				? o1.getValue().compareTo(o2.getValue()) : o1.getKey().compareTo(o2.getKey()))
        		
        		: (o2.getValue().compareTo(o1.getValue()) == 0
				? o2.getKey().compareTo(o1.getKey()) : o2.getValue().compareTo(o1.getValue())));
        
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }

	/**
	 * Letters labels.
	 *
	 * @param pane   the pane
	 * @param yStart the y start
	 */
	public static void lettersLabels(Pane pane, int yStart) {
    	
		int count = 0;
		for (String key : germanLetterFrequency.keySet()) {
			Label label123 = new Label(key);
 			label123.setStyle("-fx-font-size: 8pt; "
 					+ "-fx-font-weight: bold}");
 			pane.getChildren().add(label123);
 			label123.relocate(105 + 34.7 * count, yStart);
 			count++;
		}
    	
    }

	/**
	 * Remove letters labels.
	 */
	public void removeLettersLabels() {
    	// TODO Schöner machen
    	
    	ArrayList<Node> nodesToRemove = new ArrayList<Node>();
    	
    	for (Node n : pane.getChildren()) {
    		if (n.getStyle().equals("-fx-font-size: 8pt; " + "-fx-font-weight: bold}")) {
    			nodesToRemove.add(n);
    		}
    	}
    	
    	for (Node n : nodesToRemove) {
    		pane.getChildren().remove(n);
    	}
    	
    	nodesToRemove = null;
    	
    	// Todo Ende   
    }
    
    private void updateChart() {
    	
    	// TODO Schöner lösen.
    	
    	Node nodeToRemove = null;
    	
    	for (Node node : this.pane.getChildren()) {
    		if (node instanceof BarChart<?, ?>) {
    			nodeToRemove = node;
    		}
    	}
    	
    	if (nodeToRemove != null) {
    		this.pane.getChildren().remove(nodeToRemove);
    	}
    	
    	// Todo Ende
    	
    	final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
//        final BarChart<String,Number> bc = 
//            new BarChart<String,Number>(xAxis, yAxis);
    	
        BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        
    	bc.setTitle("Häufigkeitsanalyse");
        xAxis.setLabel("Buchstaben (ohne Umlaute und ß)"); 
        
        yAxis.setLabel("relative Häufigkeit in %");     
        
        // #########################
        
        xAxis.setTickLabelFill(Color.RED);
        // xAxis.setStyle("-fx-bar-fill: red;"
        	//	+ "-fx-font-weight: bold");
     
        
        // ##################
        
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
        	// series1.getData().add(new XYChart.Data(key, germanLetterFrequency.get(key)));
			list2String.add(key);
			list2Double.add(germanLetterFrequency.get(key));
        }
        
	
		
		for (int i = 0; i < list1String.size(); i++) {
			series1.getData().add(new XYChart.Data(list1String.get(i) + "\n\n", list1Double.get(i)));
			series2.getData().add(new XYChart.Data(list1String.get(i) + "\n\n", list2Double.get(i)));
		}
	
		bc.getData().addAll(series1, series2);
	    bc.setLegendVisible(false);
		bc.relocate(14, 130);
		
		bc.setPrefSize(1000, 500);
    	
		// pane.getChildren().add(bc);
		
//		bc.setStyle(" -fx-background-color: rgba(0,168,355,0.05);\n" + 
//				"    -fx-border-color: b \n" + 
//				"        transparent rgba(0,168,355,0.3);\n" + 
//				"    -fx-background-radius: 0;\n" + 
//				"    -fx-background-position: left center;" + 
//				"	  -fx-bar-fill: rgb(0,0,255);");
		
		bc.setStyle("-fx-background-radius: white");
		
		 for (Node n:bc.lookupAll(".default-color0.chart-bar")) {
	            n.setStyle("-fx-bar-fill: red;");
	        }
	   //second bar color
	   for (Node n:bc.lookupAll(".default-color1.chart-bar")) {
	            n.setStyle("-fx-bar-fill: black;");
	        }
	   
	   for (Node n : bc.lookupAll("Label.chart-legend-item")) {
		   Label l = (Label) n;
		   // System.out.println(l.getText());
	   }
			 
	  
	 
	  
	  
	   pane.getChildren().add(bc);
 

    }
    
    /**
     * Sets the text that should be encoded.
     * @param text that should be encoded
     */
    public static void setText(String text) {
    	FrequencyAnalysisController.text = text;
    }

}
