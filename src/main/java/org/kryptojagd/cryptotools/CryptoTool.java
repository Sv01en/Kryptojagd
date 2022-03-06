package org.kryptojagd.cryptotools;

import java.io.IOException;

import org.kryptojagd.controls.FrequencyAnalysisCaesarController;
import org.kryptojagd.controls.FrequencyAnalysisVigenereController;
import org.kryptojagd.controls.MainController;
import org.kryptojagd.presentation.PresentationManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
/**
 * Manages the crypto tool.
 * @author Michail Petermannn
 *
 */
public class CryptoTool extends Application {
 
    @Override public void start(Stage stage) throws IOException {
    	
    }
    
  /**
   * Opens the perspective of the frequency analysis for Caesar encryption. 
   * 
   * @param stage for the frequency analysis
   * @param text string that should be analysed
   * @throws IOException
   */
  public static void caesar(Stage stage, String text) throws IOException {
    	
    text = modifyText(text);
    stage.setResizable(false);
    stage.setTitle("Häufigkeitsanalyse (Cäsar-Verschlüsselung)");
    FrequencyAnalysisCaesarController.setText(text);
    Parent root = FXMLLoader.load(PresentationManager.class.getResource("FrequencyAnalysisCaesar.fxml"));
    Scene scene = new Scene(root);
    scene.getRoot().setStyle("-fx-font-family: 'monospace'");
    stage.setScene(scene);
    stage.show(); 	
  }
    
  /**
   * Open the perspective of the frequency analysis for Vigenere encryption. 
   * 
   * @param stage for the frequency analysis
   * @param text string that should be analysed
   * @throws IOException
   */
   public static void vigenere(Stage stage, String text) throws IOException {

     text = modifyText(text);
     stage.setResizable(false);
     stage.setTitle("Häufigkeitsanalyse (Vigenere-Verschlüsselung)");
     FrequencyAnalysisVigenereController.setText(text);
     Parent root = FXMLLoader.load(PresentationManager.class.getResource("FrequencyAnalysisVigenere.fxml"));
     Scene scene = new Scene(root);
     scene.getRoot().setStyle("-fx-font-family: 'monospace'");
     stage.setScene(scene);
     stage.show();
    	
   }
    
    /**
     * Puts all the string input to uppercase keeping 'ß'.
     * 
     * @param text input text that can contain any kind of characters
     * @return input text whose lowercase letters are changed to uppercase.
     */
    private static String modifyText(String text) {
    	char ss = 'ß';
    	char replace = 'ɞ';
    	text = text.replace(ss, replace);
    	text = text.toUpperCase();
    	return text.replace(replace, ss);
    }
}
