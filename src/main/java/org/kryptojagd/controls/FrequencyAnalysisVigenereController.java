package org.kryptojagd.controls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kryptojagd.cryptotools.FrequencyAnalysis;
import org.kryptojagd.encryptionmethods.Beaufort;
import org.kryptojagd.encryptionmethods.Caesar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.kryptojagd.presentation.PresentationManager;

/**
 * Controller of the frequency analysis of the Vigenere crypto tool.
 * 
 * @author Michail Petermann, Bartosz Treyde
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
    * Triggers this function when the length of the code word is changed.
    * 
    * @param event change of the combo box
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
	 * Click explanation.
	 *
	 * @param event the event
	 * @throws IOException the io exception
	 */
	@FXML
	void clickExplanation(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		stage.setResizable(false);
		stage.setTitle("Vigenère-Tool Funktionsweise");
		Parent root = FXMLLoader.load(PresentationManager.class.getResource("CryptotoolExplanation.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

  /**
   * Initializes all necessary elements of the window.
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
   * Initializes the tab of the overview bar charts.
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
    * Update the i-th bar chart in the overview tab.
    * 
    * @param i number of bar chart that should be updated
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
   * Returns every n-th letter of a string. 
   * All non-alphabetic and not uppercase characters are ignored.
   * 
   * @param n number of every n-th letter that should be returned
   * @return every n-th letter as one string
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
   * Checks if the value in a text field is legal and decodes
   * every i-th character of the given text.
   * 
   * @param i number of text field
   * @param oldValue of the text field 
   * @param newValue of the text field
   */
  private void changedTextFieldValue(int i, String oldValue, String newValue) {
	
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
	if(!MainController.isBeaufortDecryption){
	labelDecodedText.setText(decodeNthText(i, s));
	} else {
		labelDecodedText.setText(decodeNthTextBeaufort(i, s));
	}
  }

  /**
   * Decodes every n-th letter of 'text' with Caesar using s.
   * 
   * @param n every n-th letter that should be decoded
   * @param s letter for Caesar decryption
   * @return decoded
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
	private String decodeNthTextBeaufort(int n, String s) {
		Beaufort beaufort = new Beaufort();
		final char[] ALPHABET = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
		String key = "" + ALPHABET[s.charAt(0) - 'A'];
		String decodedText = labelDecodedText.getText();
		Pattern p = Pattern.compile("[A-Z]");
		int count = 0;
		for (int i = 0; i < decodedText.length(); i++) {
			Matcher m = p.matcher(decodedText.subSequence(i, i+1));
			if (m.matches()) {
				if (count == n) {
					decodedText = decodedText.substring(0, i)
							+ beaufort.decode(text.substring(i, i+1), key)
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
   * Shifts columns of the i-th bar chart to to the right. 
   * 
   * @param rightShift if true, shifts to right, else to the left
   * @param i number of bar chart of the shift
   */
  private void shiftRight(boolean rightShift, int i) {
	frequencies.set(i, rotateHashMapRight(rightShift, frequencies.get(i)));
	((VBox) tabPane.getTabs().get(i).getContent()).getChildren().set(0, updateChart(i));
	updateOverview(i); 	
  }
    
  /**
   * Rotates the order of the hash map to the right.
   * 
   * @param shiftRight if true, shifts to the right, else to the left
   * @param map which values should be rotated
   * @return rotated hashmap
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
 * Returns a VBox with a bar chart.
 * 
 * @param i number of the bar chart that should be updated
 * @return the pane with the i-th updated chart
 */
 private AnchorPane updateChart(int i) {
    splittedText[i] = getEveryNthLetter(i);
    LinkedHashMap<String, Double> splittedTextFrequency = FrequencyAnalysis.relativeFrequency(splittedText[i]);
    frequencies.add(splittedTextFrequency);
	barCharts[i] = FrequencyAnalysis.getChart("Häufigkeitsanalyse (" + (i+1) + ")", 
			"Buchstaben (ohne Umlaute und ß)", "relative Häufigkeit in %", 
			frequencies.get(i), germanLetterFrequency, true, Color.RED);
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
   * Creates a HBox with the shift buttons.
   * 
   * @param i number of the pane of the shift buttons
   * @return HBox witht the shift buttons
   */
  private HBox createShiftButtons(int i) {
    HBox hb = new HBox();
    hb.setAlignment(Pos.CENTER);
    hb.setSpacing(15);
	//Label lbl1 = new Label("A");
	//Label lbl2 = new Label("→");
	//Label lbl3 = new Label("A");
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
	hb.getChildren().addAll(buttonShiftLeft, buttonShiftRight);
	return hb;
}
    
 /**
   * Sets the text that should be encoded.
   * @param text that should be encoded
   */
  public static void setText(String text) {
	FrequencyAnalysisVigenereController.text = text;
  }
   
}
