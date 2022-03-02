package org.kryptojagd.cryptotools;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
 
/**
 * 
 * @author mp
 *
 */
public class CryptoTool extends Application {
 
    @Override public void start(Stage stage) throws IOException {
    	
    }
    
    /**
     * 
     * @param stage
     * @param text verschlüsselter Text
     * @throws IOException
     */
    public static void caesar(Stage stage, String text) throws IOException {
    	
    	text = modifyText(text);
    	stage.setResizable(false);
        stage.setTitle("Häufigkeitsanalyse (Cäsar-Verschlüsselung)");
        FrequencyAnalysisCaesarController.setText(text);

        Parent root = FXMLLoader.load(CryptoTool.class.getResource("FrequencyAnalysisCaesar.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    	
    }
    
    /**
     * 
     * @param stage
     * @param text
     * @throws IOException
     */
    public static void vigenere(Stage stage, String text) throws IOException {
    	
    	text = modifyText(text);
    	stage.setResizable(false);
        stage.setTitle("Häufigkeitsanalyse (Vigenere-Verschlüsselung)");
        
        		
        
        FrequencyAnalysisVigenereController.setText(text);
       
        Parent root = FXMLLoader.load(CryptoTool.class.getResource("FrequencyAnalysisVigenere.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    	
    }
    
    /**
     * 
     * @param text
     * @return
     */
    private static String modifyText(String text) {
    	char ss = 'ß';
    	char replace = 'ɞ';
    	text = text.replace(ss, replace);
    	text = text.toUpperCase().replaceAll("Ä", "AE").replaceAll("Ü", "UE").replaceAll("Ö", "OE");
    	return text.replace(replace, ss);
    }
}
