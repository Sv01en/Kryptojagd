package org.kryptojagd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.kryptojagd.controls.MainController;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

/**
 * This is the main app of the software.
 *
 * @author Leah Schlimm, Amelie Reichert, Bartosz Treyde, Sven Strasser, 
 *     Michail Petermann, Sonja Kuklok
 *
 */
public class MainApp extends Application {

  private PresentationManager fw;
  private MainController hs;

  private int clearedLevels = 0;

  /**
   * This method starts the software and loads the 
   * presentation elements.
   * 
   */
  public void start(Stage stage) {

    ArrayList<Level> allLevels = null;
    try {
      allLevels = ReadDirectory.initialize();
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    Parent root;
    try {

      root = FXMLLoader.load(getClass().getResource("presentation/Startfenster.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("css/startwindow.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
      fw = new PresentationManager(stage);
      assert allLevels != null;
      //TODO: Structure of game, finish level, start next level
      hs = new MainController(fw, allLevels.get(0), allLevels, clearedLevels);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}