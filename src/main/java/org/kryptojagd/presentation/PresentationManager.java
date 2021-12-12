package org.kryptojagd.presentation;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



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

}
