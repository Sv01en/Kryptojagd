package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.kryptojagd.level.Level;

import java.util.ArrayList;

/**
 * Controller for the level selector window
 * @author Leah Schlimm
 */
public class LevelSelectorController extends AbstractController {

    @FXML
    public BorderPane borderBox;

    /**
     * Initializes the level selector screen. Only the next not played level can be clicked at most
     */
    @FXML
    public void initialize() {
        ArrayList<Level> allLevels = mainController.getAllLevels();
        ArrayList<Button> buttons = new ArrayList<>();
        for (int i = 0; i < mainController.getAllLevelCount(); i++) {
            Button button = new Button("Level " + (i + 1));
            int finalI = i;
            button.setOnAction(event -> {
                setMainController(new MainController(mainController.getPresentationManager(), allLevels.get(finalI), allLevels, mainController.getClearedLevels()));
                System.out.println("Button " + finalI + " pressed!");
                mainController.runLevel();
            });
            if (i > mainController.getClearedLevels()) {
                button.setDisable(true);
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
        System.out.println("Gehe zurück zum Startmenü!");
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }
}
