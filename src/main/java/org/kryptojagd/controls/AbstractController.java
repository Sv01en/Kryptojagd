package org.kryptojagd.controls;

/**
 * @author Michail Petermann
 */
public abstract class AbstractController {
	static MainController mainController;
	
	   public static void setMainController(MainController hs) {
	    	AbstractController.mainController = hs;
	    }
}
