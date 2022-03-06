package org.kryptojagd.controls.cryptotool;

import java.io.IOException;

import org.kryptojagd.controls.AbstractController;
import org.kryptojagd.cryptotools.CryptoTool;
import org.kryptojagd.fileprocessing.ReadDirectory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller of the crypto tool.
 * 
 * @author Michail Petermann, Bartosz Treyde
 *
 */
public class CryptoToolController extends AbstractController {

  private static boolean systemHacked = false;

  @FXML
  private TextArea textArea;
    
  @FXML
  private Label messageLabel;

  @FXML
  void mainMenu(ActionEvent event) {
    mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
  }

  /**
   * Opens the perspective of the frequency analysis for the Vigenere encryption.
   * 
   * @param event click on the frequency analysis button for Caesar encryption
   */
  @FXML
  void vigenere(ActionEvent event) {
    String enteredText = textArea.getText();
    if (checktInput(enteredText)) {
	  try {
	    CryptoTool.vigenere(new Stage(), enteredText);
	   } catch (IOException e) {
		e.printStackTrace();
	   }
    }
  }
  
  /**
   * Checks if the input is null or 'HACK THE SYSTEM' that unlocks all levels.
   * 
   * @param text input that is checked.
   * @return return false, if input is null or 'HACK THE SYSTEM', else true
   */
  boolean checktInput(String text) {
    if (text.equals("")) {
      messageLabel.setText("Gib einen Text ein.");
      return false;
    } else if (text.contentEquals("HACK THE SYSTEM")) {
        systemHacked = true;
      messageLabel.setText("Du hast alle Level freigeschaltet!");
      mainController.unlockAllLevels();
      return false;
    } else {
      return true;
    }
  }

    /**
     * Is system hacked boolean.
     *
     * @return if system is hacked.
     */
    public static boolean isSystemHacked() {
        return systemHacked;
    }
}
