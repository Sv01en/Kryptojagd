package org.kryptojagd.controls.levels;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.kryptojagd.controls.AbstractController;
import org.kryptojagd.controls.cryptotool.CryptoToolController;
import org.kryptojagd.controls.MainController;
import org.kryptojagd.controls.resources.Messages;
import org.kryptojagd.fileprocessing.ReadDirectory;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.CountdownTimer;
import org.kryptojagd.level.PointSystem;
import org.kryptojagd.level.tasks.MultipleChoiceTask;

/**
 * The class controls a window of a multiple choice task.
 *
 * @author Sonja Kuklok, Michail Petermann, Sven Strasser, Leah Schlimm, Bartosz Treyde, Amelie Reichert
 */
public class MultipleChoiceController extends AbstractController {

    private final Level level = mainController.getCurrentLevel();
    private final MultipleChoiceTask task = (MultipleChoiceTask) level.getCurrentTask();

    /**
     * The Answers.
     */
    @FXML
    private Label answers;

    @FXML
    private Label timer = new Label();

    @FXML
    private Label questionField;

    @FXML
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    @FXML
    private Label score;

    private String a1;
    private String a2;
    private String a3;

    /**
     * Initializes a MultipleChoiceController
     *
     * Sets the Question of the MultipleChoiceTask
     * and sets their answers
     *
     */
    @FXML
    public void initialize() {
        String[] possibilities = task.getPossibilities();
        a1 = possibilities[0];
        a2 = possibilities[1];
        a3 = possibilities[2];
        answers.setText("A: " + a1 + "\n" + "B: " + a2 + "\n" + "C: " + a3);
        questionField.setText(task.getQuestion());
        answer1.setText("A");
        answer2.setText("B");
        answer3.setText("C");
        if (!CryptoToolController.isSystemHacked()) {
            score.setText("Punktestand: " + PointSystem.getScore());
        } else {
            score.setManaged(false);
        }
        new CountdownTimer(level.getTimeInSec());
        updateTimer(timer);
    }

    /**
     * when you click on the button answer1,
     * it proves the answer and switches the window
     *
     * @param event the event
     */
    @FXML
    void clickAnswer1(ActionEvent event) {
        clickAnswer(a1);
    }

    /**
     * when you click on the button answer2,
     * it proves the answer and switches the window
     *
     * @param event the event
     */
    @FXML
    void clickAnswer2(ActionEvent event) {
        clickAnswer(a2);
    }

    /**
     * when you click on the button answer3,
     * it proves the answer and switches the window
     *
     * @param event the event
     */
    @FXML
    void clickAnswer3(ActionEvent event) {
       clickAnswer(a3);
    }

    /**
     * Checks if the answer is correct.
     * @param answer the answer
     */
    private void clickAnswer(String answer) {
        level.proveTask(answer);
        String city = level.getCity();
        String css;
        if (level.getTask("cityTask").getTaskCompleted()) {
            css = ReadDirectory.CSS_FILES + city + ".css";
        } else {
            css = ReadDirectory.CSS_FILE_START;
        }
        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, css);
    }


    /**
     * Click menu.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void clickMenu(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", ReadDirectory.CSS_FILE_START);
    }

    /**
     * Click help opens the text based help.
     *
     * @param event the event
     */
    @FXML
    void clickHelp(ActionEvent event) {
        if (level.getCurrentTask().getName() != null && level.getCurrentTask().getName().equals("cityTask")) {
            mainController.getCurrentLevel().getCurrentTask().setHelpText(Messages.CITY_HELP_TEXT);
        }
        mainController.switchWindowWithCSS("HelpText.fxml", ReadDirectory.CSS_FILE_START);

    }
}
