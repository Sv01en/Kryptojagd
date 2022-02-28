package org.kryptojagd;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kryptojagd.controls.MainController;
import org.kryptojagd.cryptotools.VigenereBreakController;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

/**
 * This is the main app of the software.
 *
 * @author Leah Schlimm, Amelie Reichert, Bartosz Treyde, Sven Strasser,      Michail Petermann, Sonja Kuklok
 */
public class MainApp extends Application {

  private PresentationManager fw;
  private MainController hs;

  private int playableLevels = 0;

  /**
   * This method starts the software and loads the
   * presentation elements.
   *
   * @param stage the Startstage
   *
   */
  public void start(Stage stage) {

    ArrayList<Level> allLevels = null;
    try {
      allLevels = ReadDirectory.initialize();

      String text1 = "Fog tuh rpt jscuqvwüugswvs Hpzh";
      String text2 = "DasistderverschlüsselteText";
      System.out.println(VigenereBreakController.addSpacesAs(text1, text2));

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    Parent root;
    try {
     
      root = FXMLLoader.load(getClass().getResource("presentation/Startfenster.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("css/startwindow.css").toExternalForm());
      stage.getIcons().add(new Image("img/Icon.png"));
      stage.setScene(scene);
      stage.show();
      fw = new PresentationManager(stage);
      assert allLevels != null;
      hs = new MainController(fw, allLevels.get(0), allLevels, playableLevels);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}
