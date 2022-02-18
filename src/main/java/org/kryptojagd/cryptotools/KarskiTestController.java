package org.kryptojagd.cryptotools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
//import javafx.scene.web.WebEngine;
//import javafx.scene.web.WebView;

public class KarskiTestController {
	

    @FXML
    private TextField textField2;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Button button1;

    @FXML
    private TextField textField1;

	
	@FXML
	public void initialize() {
		String str = "ECT JUU FKO UGYU JJFT XFTEFP OVT HSQTTDVDJTUCCFP WFTXFPEFV IBNMP "
				+ "YFMV TDJPFP EBU FT FJDJ HJDU";
		//Text text1=new Text("Some Text");
		//text1.setStyle("-fx-font-weight: bold");

		Text text2=new Text(str);
		text2.setStyle("-fx-font-weight: regular");
		
		textFlow.getChildren().addAll(text2);//, text2);
		
	}
	
	public void mark() {
		String txt = "";
		for (Node node : textFlow.getChildren()) {
			Text text = (Text) node;
			System.out.println(text.getText());
			txt += text.getText();
		}
		String s = textField1.getText();
		
		textFlow.getChildren().clear();
		
		// s = "L([ ]*?O)([ ]*?R)([ ]*?E)([ ]*?M)";
		// System.out.println(s);
		
		
		Pattern pattern = Pattern.compile(s);
		Matcher matcher = pattern.matcher(txt);
		
		int textStart = 0;
		
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			Text text1 = new Text(txt.substring(textStart, start));
			// text1.setStyle("-fx-font-weight: regular");
			System.out.println(txt.substring(textStart, start).replace(" ", "").length());
			Text text2 = new Text(txt.substring(start, end));
			text2.setStyle("-fx-font-weight: bold; -fx-fill: red");
			
			textFlow.getChildren().addAll(text1, text2);
			textStart = end;
		}
		
		if (textStart < txt.length()) {
			Text text1 = new Text(txt.substring(textStart));
			text1.setStyle("-fx-font-weight: regular");
			textFlow.getChildren().addAll(text1);
		}
		
		button1.setText("Hallo");
		
		
		
	}
	
	
}
