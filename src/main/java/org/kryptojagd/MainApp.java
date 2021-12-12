package org.kryptojagd;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.kryptojagd.controls.MainController;
import org.kryptojagd.presentation.PresentationManager;


public class MainApp extends Application {

	private PresentationManager fw;	
	private MainController hs;
	
    public void start(Stage stage) {
        
    	Parent root;
		try {
			

			
			root = FXMLLoader.load(getClass().getResource("praesentation/Startfenster.fxml"));
			
			System.out.println(getClass().toString());
			Scene scene = new Scene(root);		

			stage.setScene(scene);

			
			stage.show();
			fw = new PresentationManager(stage);
			hs = new MainController(fw);
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    public static void main(String[] args) {
        launch(args);
    }

}