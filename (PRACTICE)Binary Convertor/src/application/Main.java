package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	public Label number;

	@FXML
	public Label numberBinary;

	@FXML
	public TextField input;

	@FXML
	public TextField inputDecimal;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/display.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("Binary Convert");
			Scene scene = new Scene(root, 790, 185);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void convertBinary() {
		ArrayList<Integer> binary = new ArrayList<Integer>();
		int decimal, current = Integer.parseInt(input.getText());
		String combinedNumbers = "";

		if (current == 0) {
			number.setText("0");
			return;
		}

		while (current != 0) {
			decimal = current % 2;
			current = current / 2;
			binary.add(decimal);
			System.out.println(decimal);
		}

		Collections.reverse(binary);

		if (binary.get(0) == 0) {
			binary.add(0, 1);
		}

		for (int i = 0; i < binary.size(); i++) {
			combinedNumbers = combinedNumbers + binary.get(i).toString();
		}
		number.setText(combinedNumbers);
	}

	public void convertDecimal() {

		List<Character> chars = new ArrayList<Character>();

		String current = inputDecimal.getText();
		String reconstructed = "";

		int addZeroes = 0;
		int currentValue = 0;
		int counter = 0;

		// String to character array

		for (int i = 0; i < current.length(); i++) {
			chars.add(current.charAt(i));
		}

		// get input number and add zeroes to match 8

		if (chars.size() < 8) {
			System.out.println("Char is less than 8");

			if (chars.get(0) > 0) {
				addZeroes = 8 - chars.size();
				System.out.println("Adding: " + addZeroes);

				for (int i = 0; i < addZeroes; i++) {
					chars.add(0, '0');
				}
			}
		}

		// reconstruct array to sting

		for (int i = 0; i < chars.size(); i++) {
			reconstructed = reconstructed + chars.get(i);
		}

		// perform calculation (on 1011001 for example)
		System.out.println(currentValue);

		for (int i = 0; i < chars.size(); i++) {
			 currentValue =(currentValue * 2) + Integer.parseInt(chars.get(i).toString());
		}
		
		System.out.println("\n" + currentValue);
		numberBinary.setText(String.valueOf(currentValue));
	}

}
