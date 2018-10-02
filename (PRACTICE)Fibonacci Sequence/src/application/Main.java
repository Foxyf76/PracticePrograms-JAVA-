package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	public static Parent root;
	public static Stage primaryStage;

	ArrayList<Integer> fibonacci = new ArrayList<Integer>();

	@FXML
	public Label number;
	@FXML
	public TextField input;

	@Override
	public void start(Stage primaryStage) {
		try {
			buildSequence();
			root = FXMLLoader.load(getClass().getResource("/application/display.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("Fibonacci Sequence");
			Scene scene = new Scene(root, 475, 185);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void buildSequence() {

		int counter = 0;
		fibonacci.add(0);
		fibonacci.add(1);
		while (fibonacci.size() < 47) { //limit, anything after becomes minus
			for (int i = 0; i < fibonacci.size(); i++) {
				int total = fibonacci.get(counter) + fibonacci.get(counter + 1);
				System.out.println(total);
				fibonacci.add(total);
				counter = counter + 1;
				break;
			}
		}
	}
	
	public void search() {
		buildSequence();
		int inputNumber = Integer.parseInt(input.getText());
		int result = fibonacci.get(inputNumber);
		number.setText(String.valueOf(result));
	}

}
