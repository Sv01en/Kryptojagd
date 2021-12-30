package org.kryptojagd.controls;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.level.CountdownTimer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Michael Petermann, Sven Strasser
 * @version 1.0
 */
public class QuizController extends AbstractController {

    private CountdownTimer countdownTimer = new CountdownTimer(20);

    @FXML
    private Button antwort1;

    @FXML
    private Button antwort2;

    @FXML
    private Button antwort3;

    @FXML
    private Label timer = new Label();

    public QuizController() {
        updateTimer();
    }

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
     * <p>
     * Updating now works. Timer function does not work at the moment.
     *
     * @author Sven Strasser
     */
    @FXML
    void updateTimer() {
        DateFormat timeFormat = new SimpleDateFormat( "mm:ss" );
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            timer.setText(timeFormat.format(countdownTimer.getActuelValueAsLong()));
                            System.out.println(countdownTimer.getActuelValueAsLong());
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
