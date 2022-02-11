package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kryptojagd.cryptotools.CryptoTool;
import org.kryptojagd.level.Level;
import org.kryptojagd.level.tasks.DecryptionTask;

import java.io.IOException;

/**
 * The class controls a window of a decryption task.
 *
 * @author Michail Petermann, Sven Strasser, Leah Schlimm, Bartosz Treyde
 */
public class DecryptionController extends AbstractController {

    private Level level = mainController.getCurrentLevel();
    private DecryptionTask task = (DecryptionTask) level.getCurrentTask();

    @FXML
    public BorderPane borderPane;

    @FXML
    public HBox hBox;

    @FXML
    public VBox vBox;

    @FXML
    public Button home;

    @FXML
    public Label question;

    @FXML
    private Label timer = new Label();

    @FXML
    private Label encryptedPuzzleText = new Label();

    @FXML
    private Button procedure1;

    @FXML
    private Button procedure2;

    @FXML
    private Button procedure3;

    /**
     * Initializes a DecryptionController either with the city question or the decryption task
     */
    @FXML
    public void initialize() {
        if (!task.getTaskCompleted()) {
            level.startCountdown();
            String[] possibleChoice = task.getPossibilities();
            String plaintext = task.getPlainText();
            encryptedPuzzleText.setText(level.getEncryptionMethod().encode(plaintext));
            procedure1.setText(possibleChoice[0]);
            procedure2.setText(possibleChoice[1]);
            procedure3.setText(possibleChoice[2]);
        } else if (!task.isEncryptionTaskCompleted()) {
            String plaintext = task.getPlainText();
            encryptedPuzzleText.setText(level.getEncryptionMethod().encode(plaintext));

            TextField textField = new TextField();
            textField.setMinWidth(600);
            textField.setMinHeight(45);

            Button sendButton = new Button();
            sendButton.setMnemonicParsing(false);
            sendButton.setText("Senden");
            sendButton.setOnAction(this::clickSend);

            HBox newHbox = new HBox(20);
            newHbox.setAlignment(Pos.CENTER);
            newHbox.setMinHeight(100);
            newHbox.setMinWidth(700);

            newHbox.getChildren().add(textField);
            newHbox.getChildren().add(sendButton);

            Button cryptoTool = new Button();
            cryptoTool.setMnemonicParsing(false);
            cryptoTool.setText("Kryptotool");
            cryptoTool.setOnAction(this::clickCrypto);

            if (task.getPossibilities()[task.getCorrectAnswerEncryption()].startsWith("Rückwärtsverschlüsselung")) {
                question.setText("Entschlüssel die ersten vier Wörter des Textes (inklusive Hallo)!");
                cryptoTool.setDisable(true);
            } else if (task.getPossibilities()[task.getCorrectAnswerEncryption()].startsWith("Cäsar")) {
                question.setText("Entschlüssel die ersten vier Wörter des Textes (inklusive Hallo)!");
            } else {
                question.setText("Gib das Schlüsselwort ein, mit dem der Text verschlüsselt wurde!");
            }

            vBox.getChildren().remove(hBox);
            vBox.getChildren().add(newHbox);
            vBox.getChildren().add(cryptoTool);
            vBox.setAlignment(Pos.CENTER);
        } else {
            System.out.println("Jetzt bin ich hier");
            level.setCityShowing(true);
            String[] cities = task.getAnswerOptionsCity();
            String questionStr = task.getCityQuestion();
            question.setText(questionStr);
            encryptedPuzzleText.setText(task.getPlainText());
            procedure1.setText(cities[0]);
            procedure2.setText(cities[1]);
            procedure3.setText(cities[2]);
        }

        updateTimer();
    }

    /**
     * Handles press on first button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure1(ActionEvent event) {
        clickAnswer(procedure1);
    }

    /**
     * Handles press on second button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure2(ActionEvent event) {
        clickAnswer(procedure2);
    }

    /**
     * Handles press on third button
     *
     * @param event that is received
     */
    @FXML
    void clickProcedure3(ActionEvent event) {
        clickAnswer(procedure3);
    }

    @FXML
    private void clickSend(ActionEvent event) {
        //TODO Add functionality here

        task.setEncryptionFinished();
        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, "../css/startwindow.css");
    }

    @FXML
    private void clickCrypto(ActionEvent event) {
        if (task.getPossibilities()[task.getCorrectAnswerEncryption()].startsWith("Cäsar")) {
            try {
                CryptoTool.caesar(new Stage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                CryptoTool.vigenere(new Stage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if the answer is correct.
     * @param procedure Button which is clicked on
     */
    private void clickAnswer(Button procedure){
        if (!level.getCurrentTask().getTaskCompleted()) {
            mainController.decryptionTaskSucceeded = level.proveTask(
                    procedure.getText());
        } else {
            mainController.cityTaskFinished = level.proveCityTask(procedure.getText());
        }

        mainController.switchWindowWithCSS(MainController.TASK_FINISHED_FXML, "../css/startwindow.css");
    }

    /**
     * Updates the {@link DecryptionController#timer} every second in the corresponding fxml-file.
     */
    @FXML
    @Override
    void updateTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        time.stop();
        KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.setText(Integer.toString(level.getTimeInSec()));
            if (level.getTimeInSec() <= 0) {
                mainController.switchWindowWithCSS("TimeOver.fxml", "../css/startwindow.css");
                time.stop();
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    @FXML
    public void clickMenu(ActionEvent actionEvent) {
        mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
    }
}
