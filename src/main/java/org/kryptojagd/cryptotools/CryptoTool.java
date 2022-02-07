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
 
public class CryptoTool extends Application {
 
    @Override public void start(Stage stage) throws IOException {
    	
    	// haeufigkeitsanalyse(stage);
    	vigenere(stage);
    	//karski(stage);

    }
 
    public static void main(String[] args) {
        launch(args);
    	//FrequencyAnalysis f = new FrequencyAnalysis();
    	// f.initialize();
    }
    
    public static void caesar(Stage stage) throws IOException {
    	stage.setResizable(false);
        stage.setTitle("Häufigkeitsanalyse");
        
        String testText = "EJFT JTU FJO CFJTQJFMUFYU EFS NJU FJOFN LMJDL WFSTDIMÜTTFMU XFSEFO LBOO. "
        		+ "IJFS LBOO BVDI FJO FJHFOFO UFYU IFSFJO HFTDISJFCFO, PEFS FJO HFIFJNDPEF AVN "
        		+ "FOUTDIMÜTTFMO IFSFJO LPQJFSU XFSEFO.";
        		
        
        FrequencyAnalysisController.setText(testText);
        //FrequencyAnalysisController2.setText(testText);
       
        // Parent root = FXMLLoader.load(getClass().getResource("vigenere.fxml"));
        Parent root = FXMLLoader.load(CryptoTool.class.getResource("frequencyAnalysis.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("frequencyAnalysis2.fxml"));
        Scene scene = new Scene(root);
        
        // Scene scene  = new Scene(bc,800,450);
        
        
        stage.setScene(scene);
        stage.show();
    	
    }
    
    public static void vigenere(Stage stage) throws IOException {
    	stage.setResizable(false);
        stage.setTitle("Häufigkeitsanalyse");
        
        String testText = "EJFT JTU FJO CFJTQJFMUFYU EFS NJU FJOFN LMJDL WFSTDIMÜTTFMU XFSEFO LBOO. "
        		+ "IJFS LBOO BVDI FJO FJHFOFO UFYU IFSFJO HFTDISJFCFO, PEFS FJO HFIFJNDPEF ZVN "
        		+ "FOUTDIMÜTTFMO IFSFJO LPQJFSU XFSEFO.";
        		
        
        FrequencyAnalysisController.setText(testText);
        //FrequencyAnalysisController2.setText(testText);
       
        Parent root = FXMLLoader.load(CryptoTool.class.getResource("vigenere.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("frequencyAnalysis.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("frequencyAnalysis2.fxml"));
        Scene scene = new Scene(root);
        
        // Scene scene  = new Scene(bc,800,450);
        
        
        stage.setScene(scene);
        stage.show();
    	
    }
    
    public void karski(Stage stage) throws IOException {
    	stage.setResizable(false);
        stage.setTitle("Häufigkeitsanalyse");
        
        String testText = "EJFT JTU FJO CFJTQJFMUFYU EFS NJU FJOFN LMJDL WFSTDIMÜTTFMU XFSEFO LBOO. "
        		+ "IJFS LBOO BVDI FJO FJHFOFO UFYU IFSFJO HFTDISJFCFO, PEFS FJO HFIFJNDPEF ZVN "
        		+ "FOUTDIMÜTTFMO IFSFJO LPQJFSU XFSEFO.";
        		
        
        FrequencyAnalysisController.setText(testText);
        //FrequencyAnalysisController2.setText(testText);
       
        Parent root = FXMLLoader.load(getClass().getResource("karskiText.fxml"));
        // Parent root = FXMLLoader.load(getClass().getResource("frequencyAnalysis.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("frequencyAnalysis2.fxml"));
        Scene scene = new Scene(root);
        
        // Scene scene  = new Scene(bc,800,450);
        
        
        stage.setScene(scene);
        stage.show();
    	
    }
}
