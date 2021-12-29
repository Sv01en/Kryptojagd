package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.CountdownTimer;

import javax.swing.*;

/**
 * @author Michael, Sven Strasser
 * @version 1.0
 */
public class QuizController extends AbstractController{

    private CountdownTimer countdownTimer = new CountdownTimer(20);

    @FXML
    private Button antwort1;

    @FXML
    private Button antwort2;

    @FXML
    private Button antwort3;

    @FXML
    private Label timer;

    @FXML
    void klickAntwort1(ActionEvent event) {
    	System.out.println("Antwort 1 wurde ausgewäht!");
    	mainController.switchWindow("Levelabschluss.fxml");
    }

    @FXML
    void klickAntwort2(ActionEvent event) {
    	System.out.println("Antwort 2 wurde ausgewäht!");
    }

    @FXML
    void klickAntwort3(ActionEvent event) {
    	System.out.println("Antwort 3 wurde ausgewäht!");
    }

    /**
     * Updates the timer in the corresponding window.
     *
     * This does not currently work.
     *
     * @author Sven Strasser
     */
    @FXML
    void updateTimer() {
        //TODO: Does not work yet. Does not update the timer label yet
        timer.textProperty().bind(countdownTimer.getActuelValue());
        System.out.println("Test");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                actionEvent -> countdownTimer.countdownTimer()),
                new KeyFrame(Duration.seconds(CountdownTimer.DURATION)));

        timeline.setCycleCount(CountdownTimer.DURATION);
        timeline.play();

    }

}
