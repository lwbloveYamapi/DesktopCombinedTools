package com.edu;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class calcu extends Application {
	Text text = new Text("");
 
	public void start(Stage pr) {
		text.setFill(Color.BLACK);
		text.setFont(Font.font("黑体", 30));
		String[] number = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		Button[] b = new Button[10];
		String[] m = { "Mc", "Mr", "M+", "M-", "Ms" };
		Button[] m1 = new Button[5];
		String[] operate= {"/","X","-","+","="};
		Button[] o=new Button[5];
		GridPane gdpane = new GridPane();
		for (int i = 0; i < 5; i++) {
			m1[i] = new Button(m[i]);
			m1[i].setPrefSize(64, 40);
			m1[i].setStyle("-fx-base: lightblue");
		}
		for(int i=0;i<5;i++) {
			o[i]=new Button(operate[i]);
			o[i].setPrefSize(80, 40);
			String s=operate[i].toString();
			o[i].setOnAction(e->add(s));
		}
		
		Button zc = new Button("%");
		Button ce = new Button("ce");
		ce.setOnAction(e->add("ce"));
		Button yes = new Button("✔");
		Button x2 = new Button("x²");
		Button x1 = new Button("1/x");
		Button c = new Button("C");
		Button del = new Button("←");
		Button addandmin = new Button("±");
		Button point = new Button(".");
		for (int i = 0; i < 10; i++) {
			b[i] = new Button(number[i]);
			b[i].setPrefSize(80, 40);
			b[i].setStyle("-fx-base:white");
		}
 
		ce.setPrefSize(80, 40);
		zc.setPrefSize(80, 40);
		yes.setPrefSize(80, 40);
		x2.setPrefSize(80, 40);
		x1.setPrefSize(80, 40);
		c.setPrefSize(80, 40);
		del.setPrefSize(80, 40);
		addandmin.setPrefSize(80, 40);
		point.setPrefSize(80, 40);
 
		ce.setStyle("-fx-base: pink"); 		
		zc.setStyle("-fx-base: red");			
		yes.setStyle("-fx-base:Orange");
		x2.setStyle("-fx-base: yellow");
		x1.setStyle("-fx-base: green");
		c.setStyle("-fx-base: BLUEVIOLET");
		del.setStyle("-fx-base: blue");
		o[0].setStyle("-fx-base: Lightgreen");  
		o[1].setStyle("-fx-base: CADETBLUE");
		o[2].setStyle("-fx-base: DARKKHAKI");
		o[3].setStyle("-fx-base: CHARTREUSE");
		o[4].setStyle("-fx-base: DARKGRAY");
		addandmin.setStyle("-fx-base: DEEPPINK");
		point.setStyle("-fx-base: AQUA");
 
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(8, 8, 8, 8));
 
		HBox hbox = new HBox();
		hbox.getChildren().addAll(m1[0], m1[1], m1[2], m1[3], m1[4]);
		BorderPane pane1 = new BorderPane();
		pane1.setRight(text);
		pane1.setBottom(hbox);
		pane.setTop(pane1);
 
		gdpane.add(zc, 0, 0);
		gdpane.add(yes, 1, 0);
		gdpane.add(x2, 2, 0);
		gdpane.add(x1, 3, 0);
		gdpane.add(ce, 0, 1);
		gdpane.add(c, 1, 1);
		gdpane.add(del, 2, 1);
		for(int i=0;i<5;i++)
			gdpane.add(o[i], 3, i+1);
		
		for (int i = 2, count = 7; i < 5; i++, count = count - 3)
			for (int j = 0; j < 3; j++)
				gdpane.add(b[count + j], j, i);
		gdpane.add(b[0], 1, 5);
		gdpane.add(addandmin, 0, 5);
		gdpane.add(point, 2, 5);
	
		pane.setCenter(gdpane);
		for (int i = 0; i < 10; i++) {
			String carriage = String.valueOf(i);
			b[i].setOnAction(e -> add(carriage));
		}
		Scene scene = new Scene(pane);
		pr.setTitle("calculate");
		pr.setScene(scene);
		pr.show();
 
	}
 
	private void add(String s) {
		String out = "";
		if (text.getText().equals("0") || text.getText().equals("404"))
			text.setText("");
 
		switch (s) {
		case "ce":
			out = "0";
			break;
		case "±":
			try {
				out = String.valueOf(Integer.parseInt(text.getText()) * (-1));
			} catch (NumberFormatException e) {
				}
		break;
		case "+":
		case "-":
		case "X":		//这里的乘是大写字母X
		case "/":
			out = text.getText() + " " + s + " ";
			break;
		case "%":
			try {
				Float getNum = Float.parseFloat(text.getText());
				double setNum = Math.pow(getNum, 2d);
				if (setNum % 1 == 0)
					out = String.valueOf((int) setNum);
				else
					out = String.valueOf(setNum);
			} catch (NumberFormatException e) {
				System.err.println("Wrong Input");
				out = text.getText();
			}
			break;
		case "=":
			out = getResult();
			break;
		default:
			out = text.getText() + s;
			break;
		}
		text.setText(out);
	}
	private String getResult() {
		try {
			String[] textbox = text.getText().split(" ");
			float result = Float.parseFloat(textbox[0]);
			for (int i = 2; i < textbox.length; i += 2) {
				float num = Float.parseFloat(textbox[i]);
				switch (textbox[i - 1]) {
				case "+":
					result += num;
					break;
				case "-":
					result -= num;
					break;
				case "/":
					result = Float.parseFloat(textbox[i - 2]) / num;
					break;
				case "X":
					result *= num;
					break;
				}
			}
			if (result % 1 == 0)
				return String.valueOf((int) result);
			else
				return String.valueOf(result);
		} catch (NumberFormatException e) {
			System.err.println("wrong input || null String");
			return "404";
		}
	}
 
	public static void main(String[] args) {
		Application.launch(args);
	}
 
}
