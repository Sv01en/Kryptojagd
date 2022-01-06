package org.kryptojagd.presentation;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kryptojagd.Main;


public class PresentationManager {
	
	private Stage stage;
	
	public PresentationManager(Stage primaryStage) {
		this.stage = primaryStage;
	}
	
	public void switchWindow(String str) {
		Parent root;
		try {
			
			root = FXMLLoader.load(getClass().getResource(str));
			// root = loader.load();
			Scene scene = new Scene(root);	
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchWindowWithCSS(String path, String css) {
		try {
			System.out.println(getClass().toString());
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			BorderPane rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
