package org.kryptojagd.presentation;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class that manages the current window and the window switching
 *
 * @author Leah Schlimm, Bartosz Treyde
 */
public class PresentationManager {
	
	private final Stage stage;
	private boolean darkMode = false;
	private boolean blindMode = false;

	/**
	 * Constructor of the PresentationManager
	 * @param primaryStage
	 */
	public PresentationManager(Stage primaryStage) {
		primaryStage.setResizable(false);
		this.stage = primaryStage;
	}

	/**
	 * Switches the window and applies a style to it and exits program if window is closed
	 * @param path window to switch to
	 * @param css style to apply
	 */
	public void switchWindowWithCSS(String path, String css) {
		try {
			String cssFile = "";
			if (blindMode) {
				cssFile = "/org/kryptojagd/css/startwindow_accessible.css";
			} else if (darkMode) {
				char[] cssArr = css.toCharArray();
				String tmp = "";
				for (int i = 0; i < cssArr.length - 4; i++) {
					tmp = tmp + cssArr[i];
				}
				tmp = tmp + "_dark.css";
				cssFile = tmp;
			} else {
				cssFile = css;
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Pane rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
			stage.setScene(scene);
			stage.show();
			stage.setOnCloseRequest(event -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void toggleDarkmode() {
		this.darkMode = !this.darkMode;
	}

	public void toggleBlindMode() {
		this.blindMode = !blindMode;
	}
}
