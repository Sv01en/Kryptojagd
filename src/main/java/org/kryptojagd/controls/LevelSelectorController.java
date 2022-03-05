package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import java.util.ArrayList;

/**
 * Controller for the level selector window
 *
 * @author Leah Schlimm, Sven Strasser
 * @version 1.0
 */
public class LevelSelectorController extends AbstractController {

    /**
     * The Border box.
     */
    @FXML
    private BorderPane borderBox;
    
    private static boolean unlockAllLevels = false;

    /**
     * Initializes the level selector screen. Only the next not played level can be clicked at most
     */
    @FXML
    public void initialize() throws Exception {
        ArrayList<Level> levels = mainController.getLevelHandler().getAllLevels();
        ArrayList<Button> buttons = new ArrayList<>();
        for (int i = 0; i < levels.size(); i++) {
            Button button = new Button("Level " + (i + 1));
            int finalI = i;
            button.setOnAction(event -> {
                try {
                    mainController.startLevelByPosition(finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            if (mainController.getLevelHandler().checkPlayable(levels.get(i)) || unlockAllLevels) {
                button.setDisable(false);
            } else {
                button.setDisable(true);
            }

            if (i == 0) {
                button.setDisable(false);
            }
            
            buttons.add(button);
        }
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        for (int i = 0; i < buttons.size(); i++) {
            vbox.getChildren().add(buttons.get(i));
        }

        borderBox.setCenter(vbox);
    }

    /**
     * Handles press on back button
     * @param event that is received
     */
    @FXML
    void clickBack(ActionEvent event) {
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }
    
    static void unlockAllLevels() {
    	unlockAllLevels = true;
    }
}
