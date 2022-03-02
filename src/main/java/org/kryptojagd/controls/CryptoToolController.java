package org.kryptojagd.controls;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kryptojagd.cryptotools.CryptoTool;
import org.kryptojagd.fileprocessing.ReadDirectory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CryptoToolController extends AbstractController {

    @FXML
    private TextArea textArea;
    
    @FXML
    private Label messageLabel;

    @FXML
    void caesar(ActionEvent event) {
    	String enteredText = textArea.getText();
    	if (checktInput(enteredText)) {
	    	try {
				CryptoTool.caesar(new Stage(), enteredText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void mainMenu(ActionEvent event) {
    	mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    @FXML
    void vigenere(ActionEvent event) {
    	String enteredText = textArea.getText();
    	if (checktInput(enteredText)) {
	    	try {
				CryptoTool.vigenere(new Stage(), enteredText);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    boolean checktInput(String text) {
    	
    	if (text.equals("")) {
    		messageLabel.setText("Gib einen Text ein.");
    		return false;
    	} else if (text.contentEquals("HACK THE SYSTEM")) {
    		messageLabel.setText("Du hast alle Level freigeschaltet!");
    		mainController.unlockAllLevels();
    		return false;
    	} else {
        	return true;
    	}
    }

}
