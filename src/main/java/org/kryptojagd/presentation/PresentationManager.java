package org.kryptojagd.presentation;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Class that manages the current window and the window switching
 *
 * @author Leah Schlimm, Bartosz Treyde
 */
public class PresentationManager {
	
	private Stage stage;
	private boolean darkMode = false;

	/**
	 * Constructor of the PresentationManager
	 * @param primaryStage
	 */
	public PresentationManager(Stage primaryStage) {
		primaryStage.setResizable(false);
		this.stage = primaryStage;
	}

	/**
	 * Switches the window without a style and exits program if window is closed
	 * @param str window to switch to
	 */
	public void switchWindow(String str) {
		Parent root;
		try {

			root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(str)));
			// root = loader.load();
			Scene scene = new Scene(root);	
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

	/**
	 * Switches the window and applies a style to it and exits program if window is closed
	 * @param path window to switch to
	 * @param css style to apply
	 */
	public void switchWindowWithCSS(String path, String css) {
		try {
			String cssFile = "";
			if (darkMode) {
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
			Pane rootLayout = (Pane) loader.load();
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
}
