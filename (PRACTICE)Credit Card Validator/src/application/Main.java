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

	@FXML
	public TextField input;
	@FXML
	public Label validity;
	@FXML
	public Label isValid;

	public int checkDigit;

	ArrayList<Integer> digits;
	ArrayList<Integer> doubledDigits;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/display.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("Credit Card Validator");
			Scene scene = new Scene(root, 650, 260);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	public void checkValidCard() {
		String text = input.getText();
		System.out.println("Input text: " + text);
		digits = new ArrayList<Integer>();
		for (char c : text.toCharArray()) {
			int number = Character.getNumericValue(c);
			digits.add(number);
		}
		checkDigit = digits.get(digits.size() - 1); // set last value as check digit
		digits.remove(digits.size() - 1); // remove last digit, not needed
		itterateDigits();
	}

	public void itterateDigits() {
		doubledDigits = new ArrayList<Integer>();
		for (int i = 0; i < digits.size(); i += 2) {
			System.out.println("Number: " + digits.get(i));
			int doubled = digits.get(i) + digits.get(i);
			if (doubled >= 10 && doubled <= 99) {
				int firstInt = doubled / 10;
				int secondInt = doubled % 10; // Modulo
				int total = firstInt + secondInt;
				System.out.println(doubled + " OVER 10 ---> " + firstInt + " + " + secondInt + " = " + total);
				digits.set(i, total);
			} else {
				System.out.println("Number doubled: " + doubled);
				digits.set(i, doubled);
			}
		}
		int counter = 0;
		for (int i = 0; i < digits.size(); i++) {
			counter = counter + digits.get(i);
			System.out.println("Conuter = " + counter + ", " + "adding: " + digits.get(i));

		}
		if ((counter + checkDigit) % 10 == 0) {
			System.out.println("VALID CREDIT CARD");
			isValid.setText("VALID CREDIT CARD");
		} else {
			System.out.println("NOT VALID CREDIT CARD");
			isValid.setText("NOT VALID CREDIT CARD");
		}
	}
}
