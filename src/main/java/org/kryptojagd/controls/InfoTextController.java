package org.kryptojagd.controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * @author Leah Schlimm
 */
public class InfoTextController extends AbstractController {

	@FXML
	public Label label;

	@FXML
	public void initialize() {
		String text = "Du und deine Kollegen Bob und Alice wurdet von der nationalen Informatikervereinigung (NIV) beauftragt, die letzten vier antiken Floppy-Disks zu finden und sie sicher in das historische Computermuseum zu bringen.\nDie Herausforderung dabei ist, dass die Floppy-Disks auf der ganzen Welt verteilt sind. Aber ihr drei seid die besten Spürnasen weit und breit und scheut euch deshalb natürlich nicht davor, diese Herausforderung anzunehmen.\nDie NIV hat im Darknet einen verschlüsselten Hinweis zu dem Fundort der ersten Floppy-Disk entdeckt und euch diesen weitergeleitet...\n\nStarte das Spiel und finde die Floppy-Disks!";

		label.setText(text);
	}

	/**
	 * Handles button press event
	 * @param event that is received
	 */
	 @FXML
	 void schliesseInfotext(ActionEvent event) {
		 System.out.println("Es wurde auf Schließen geklickt!");
		 mainController.switchWindowWithCSS("Startfenster.fxml", "../css/startwindow.css");
	 }
}
