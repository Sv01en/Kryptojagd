package org.kryptojagd.controls;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.kryptojagd.fileprocessing.ReadDirectory;

/**
 * @author Michail Petermann
 */
public abstract class AbstractController {
	/**
	 * The Main controller.
	 */
	static MainController mainController;

	/**
	 * Updates the timer in the corresponding window.
	 */
	void updateTimer(Label timer) {
		timer.setText(setCountdownFormat(mainController.getCurrentLevel().getTimeInSec()));
		Timeline time = new Timeline();
		time.setCycleCount(Timeline.INDEFINITE);
		time.stop();
		KeyFrame frame = new KeyFrame(Duration.seconds(1), actionEvent -> {
			timer.setText(setCountdownFormat(mainController.getCurrentLevel().getTimeInSec()));
			if (mainController.getCurrentLevel().getTimeInSec() <= 0) {
				mainController.getCurrentLevel().setFirstTryTimer(false);
				mainController.switchWindowWithCSS("TimeOver.fxml", ReadDirectory.CSS_FILE_START);
				time.stop();
			}
		});
		time.getKeyFrames().add(frame);
		time.playFromStart();
	}

	/**
	 * Returns the time in a better readable format.
	 * @param timeInSeconds given seconds as an integer
	 * @return the given seconds in the format mm:ss
	 */
	 String setCountdownFormat(int timeInSeconds) {
		int minutes = (timeInSeconds % 3600) / 60;
		int seconds = timeInSeconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * Sets main controller.
	 *
	 * @param hs the hs
	 */
	public static void setMainController(MainController hs) {
	    	AbstractController.mainController = hs;
	    }
}
