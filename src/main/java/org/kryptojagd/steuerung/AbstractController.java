package org.kryptojagd.steuerung;

public abstract class AbstractController {
	static Hauptsteuerung hs;
	
	   public static void setzeHauptsteuerung(Hauptsteuerung hs) {
	    	AbstractController.hs = hs;
	    }
}
