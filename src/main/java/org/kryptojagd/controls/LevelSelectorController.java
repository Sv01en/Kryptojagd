package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.LevelHandler;

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
        System.out.println("Level neu initialisiert!");
        ArrayList<Level> levels = ReadDirectory.initialize();
        ArrayList<Level> playedLevels = mainController.getLevelHandler().getPlayedLevels();
        for (Level playedLevel : playedLevels) {
            System.out.println(playedLevel.getId());
        }
        ArrayList<Button> buttons = new ArrayList<>();
        for (int i = 0; i < levels.size(); i++) {
            Button button = new Button("Level " + (i + 1));
            int finalI = i;
            button.setOnAction(event -> {
                try {
                    int countClearedLevels = mainController.getClearedLevels();
                    LevelHandler levelHandler = new LevelHandler(levels);
                    setMainController(
                            new MainController(mainController.getPresentationManager(), levels.get(finalI),
                                    levelHandler,
                                    countClearedLevels));
                    mainController.getLevelHandler().setPlayedLevels(playedLevels);
                    mainController.startLevelByPosition(finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            boolean isPlayable = false;
            for (Level playedLevel : playedLevels) {
                if (playedLevel.getId() == levels.get(i).getId()) {
                    isPlayable = true;
                }
            }
            if (unlockAllLevels) {
                button.setDisable(false);
            } else if (isPlayable) {
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
        for (Button button : buttons) {
            vbox.getChildren().add(button);
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

    /**
     * Unlock all levels.
     */
    static void unlockAllLevels() {
    	unlockAllLevels = true;
    }
}
