package org.kryptojagd.steuerung;

import java.util.List;

import org.kryptojagd.level.Level;
import org.kryptojagd.praesentation.Fensterverwaltung;

public class Hauptsteuerung {
	
	private Fensterverwaltung fw;
	
	private List<Level> levelListe;
	
	public Hauptsteuerung(Fensterverwaltung fw) {
		this.fw = fw;
		AbstractController.setzeHauptsteuerung(this);
	}
	
	public void wechsleFenster(String str) {
		fw.wechsleFenster(str);
	}
	
	private void initialisiereLevel() {
		
	}
	

}
