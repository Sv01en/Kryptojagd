package org.kryptojagd.controls;

import java.util.ResourceBundle;

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
	 * Sets main controller.
	 *
	 * @param hs the hs
	 */
	public static void setMainController(MainController hs) {
	    	AbstractController.mainController = hs;
	    }
}
