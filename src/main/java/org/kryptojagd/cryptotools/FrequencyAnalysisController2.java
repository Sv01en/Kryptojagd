package org.kryptojagd.cryptotools;

import java.io.IOException;
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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FrequencyAnalysisController2 {
	
	@FXML
    private AnchorPane pane;

    @FXML
    private Label label1;
    
    @FXML
    private ComboBox<String> sort_choices;
    
    private static String text = "Kodierter Text";
    
    private static HashMap<String, Integer> hashmap;
    
    //@FXML
    //private GridPane table;
    
    @FXML
    public void initialize() {
    	
        	
    	label1.setText(text);
    	ObservableList<String> options = 
    		    FXCollections.observableArrayList(
    		        "alphabetisch",
    		        "absteigend"
    		    );
    	sort_choices.setItems(options);
    	
    	sort_choices.getSelectionModel().select(0);
    	
    	final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        final CategoryAxis xAxis2 = new CategoryAxis();
        final NumberAxis yAxis2 = new NumberAxis();
        
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        
        final BarChart<String,Number> bc2 = 
                new BarChart<String,Number>(xAxis2,yAxis2);
//        
//        final BarChart<String,Number> bc3 = 
//                new BarChart<String,Number>(xAxis,yAxis);
//        
//        final BarChart<String,Number> bc4 = 
//                new BarChart<String,Number>(xAxis,yAxis);
//        
        bc.setTitle("Häufigkeitsanalyse");
        xAxis.setLabel("Buchstaben");       
        yAxis.setLabel("Häufigkeit");      
        
        bc2.setTitle("Häufigkeitsanalyse");
        xAxis2.setLabel("Buchstaben");       
        yAxis2.setLabel("Häufigkeit"); 
        
        XYChart.Series series1 = new XYChart.Series(); 
        XYChart.Series series2 = new XYChart.Series();
        
		hashmap = null;//FrequencyAnalysis.absoluteFrequency(text);
		
		for (String key : hashmap.keySet()) {
        	series1.getData().add(new XYChart.Data(key, hashmap.get(key)));
        	series2.getData().add(new XYChart.Data(key, hashmap.get(key)));
        }
		
		
		bc.getData().addAll(series1);
		bc2.getData().addAll(series2);
//		bc3.getData().addAll(series1);
//		bc4.getData().addAll(series1);

	    bc.setLegendVisible(false);
		bc.relocate(5, 100);
		
		bc.setPrefSize(400, 200);
    	
		bc2.setLegendVisible(false);
		bc2.relocate(420, 100);
			
		bc2.setPrefSize(400, 200);
		
		pane.getChildren().add(bc);
		pane.getChildren().add(bc2);

    }
    
    @FXML
    void comboboxItemSelected(ActionEvent event) {
    	String selection = sort_choices.getValue();
    	
    	if(selection.equals("alphabetisch")) {
    		hashmap = sortByComparator(true);
    	} else {
    		hashmap = sortByComparator(false);
    	}
    	updateChart();
    }
    
    @FXML
    void showGermanFrequency(ActionEvent event) {
    	try {
			FXMLLoader.load(getClass().getResource("germanLetterFrequency.fxml"));
			 Parent root2 = FXMLLoader.load(getClass().getResource("germanLetterFrequency.fxml"));
		     Scene scene = new Scene(root2);
		     Stage stage = new Stage(); 
		     
		        stage.setScene(scene);
		        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private static HashMap<String, Integer> sortByComparator(boolean alphabetic) {
    	List<Entry<String, Integer>> list = new LinkedList<>(hashmap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> alphabetic ? 
        		( o1.getKey().compareTo(o2.getKey()) == 0 ? 
        				o1.getValue().compareTo(o2.getValue()) : o1.getKey().compareTo(o2.getKey()) ) 
        		
        		: ( o2.getValue().compareTo(o1.getValue()) == 0 ? 
        				o2.getKey().compareTo(o1.getKey()) : o2.getValue().compareTo(o1.getValue())) );
        
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
    
    private void updateChart() {
    	Node nodeToRemove = null;
    	for (Node node : this.pane.getChildren()) {
    		if (node instanceof BarChart<?,?>) {
    			nodeToRemove = node;
    		}
    	}
    	if (nodeToRemove != null) {
    		this.pane.getChildren().remove(nodeToRemove);
    	}
    	
    	final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        
        bc.setTitle("Häufigkeitsanalyse");
        xAxis.setLabel("Buchstaben");       
        yAxis.setLabel("Häufigkeit");        
        
        XYChart.Series series1 = new XYChart.Series(); 
        		
		for (String key : hashmap.keySet()) {
        	series1.getData().add(new XYChart.Data(key, hashmap.get(key)));
        }
		
		
		
		bc.getData().addAll(series1);
	    bc.setLegendVisible(false);
		bc.relocate(5, 100);
		
		bc.setPrefSize(1000, 500);
    	
		pane.getChildren().add(bc);

    	
    
    	
    }
    
    public static void setText(String text) {
    	FrequencyAnalysisController2.text = text;
    }

}
