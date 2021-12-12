package org.kryptojagd.controls;

import java.util.List;

import org.kryptojagd.level.Level;
import org.kryptojagd.presentation.PresentationManager;

public class MainController {
	
	private PresentationManager fw;
	
	private List<Level> levelListe;
	
	public MainController(PresentationManager fw) {
		this.fw = fw;
		AbstractController.setMainController(this);
	}
	
	public void switchWindow(String str) {
		fw.switchWindow(str);
	}
	
	private void initiliazeLevel() {
		
	}
	

}
