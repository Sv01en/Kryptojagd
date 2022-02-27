package org.kryptojagd.controls;

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
	void updateTimer(){}

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
