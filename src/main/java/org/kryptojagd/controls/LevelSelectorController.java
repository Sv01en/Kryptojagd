package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kryptojagd.level.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectorController extends AbstractController {

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
            });
            buttons.add(button);
        }
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        for (int i = 0; i < buttons.size(); i++) {
            vbox.getChildren().add(buttons.get(i));
        }

        Button backBtn = new Button("Zurück");
        backBtn.setOnAction(event -> clickBack(event));

        BorderPane pane = (BorderPane) mainController.getStage().getScene().getRoot();
        System.out.println(pane);
        pane.setOpaqueInsets(new Insets(10, 10, 10, 10));
        pane.setPadding(new Insets(20, 20, 20, 20));
        pane.setAlignment(backBtn, Pos.BOTTOM_CENTER);

        pane.setBottom(backBtn);
        pane.setCenter(vbox);

        mainController.getStage().setScene(new Scene(pane));
    }

    @FXML
    void clickBack(ActionEvent event) {
        System.out.println("Gehe zurück zum Startmenü!");
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }

    @FXML
    void clickLevel1(ActionEvent event) {
    }
}
