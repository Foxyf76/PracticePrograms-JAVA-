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

	// LABELS

	@FXML
	public TextField inputCost;
	@FXML
	public TextField inputAmount;
	@FXML
	public Label euro2;
	@FXML
	public Label euro1;
	@FXML
	public Label cent50;
	@FXML
	public Label cent20;
	@FXML
	public Label cent10;
	@FXML
	public Label cent5;
	@FXML
	public Label cent2;
	@FXML
	public Label cent1;

	@FXML
	public Label euro100;
	@FXML
	public Label euro50;
	@FXML
	public Label euro20;
	@FXML
	public Label euro10;
	@FXML
	public Label euro5;

	@FXML
	public Label changeOwed;

	double[] coins = new double[] { .01, .02, .05, 0.1, 0.2, 0.5, 1, 2 };
	int[] notes = new int[] { 5, 10, 20, 50, 100 };

	int coinsSize = coins.length;
	int notesSize = notes.length;
	double currentValueNotes = 0;
	double currentValue = 0;

	// COUNTERS

	int counter2euro = 0;
	int counter1euro = 0;
	int counter50cent = 0;
	int counter20cent = 0;
	int counter10cent = 0;
	int counter5cent = 0;
	int counter2cent = 0;
	int counter1cent = 0;

	int counter100euro = 0;
	int counter50euro = 0;
	int counter20euro = 0;
	int counter10euro = 0;
	int counter5euro = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/display.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("Change Return");
			Scene scene = new Scene(root, 805, 460);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setValues() {
		counter2euro = 0;
		counter1euro = 0;
		counter50cent = 0;
		counter20cent = 0;
		counter10cent = 0;
		counter5cent = 0;
		counter2cent = 0;
		counter1cent = 0;

		counter100euro = 0;
		counter50euro = 0;
		counter20euro = 0;
		counter10euro = 0;
		counter5euro = 0;

		euro2.setText("0");
		euro1.setText("0");
		cent50.setText("0");
		cent20.setText("0");
		cent10.setText("0");
		cent5.setText("0");
		cent2.setText("0");
		cent1.setText("0");

		euro100.setText("0");
		euro50.setText("0");
		euro20.setText("0");
		euro10.setText("0");
		euro5.setText("0");

	}

	public double generateNotes() {
		setValues();
		double cost = Double.parseDouble(inputCost.getText());
		double amount = Double.parseDouble(inputAmount.getText());
		double change = Math.abs(cost - amount);
		change = Math.round(change * 100.0) / 100.0;
		changeOwed.setText(String.valueOf(change));

		while (change != 0) {
			if (change >= notes[notesSize - 1]) {

				currentValueNotes = change - notes[notesSize - 1];
				currentValueNotes = Math.round(currentValueNotes * 100.0) / 100.0;
				change = currentValueNotes;
				checkNote(notes[notesSize - 1]);

			}

			else {
				notesSize = notesSize - 1;
			}

			if (notes[notesSize - 1] < change) {
				change = Math.round(change * 100.0) / 100.0;
				currentValueNotes = change - notes[notesSize - 1];
				currentValueNotes = Math.round(currentValueNotes * 100.0) / 100.0;
				System.out.println("Second if --- Change owed: " + change + " - " + notes[notesSize - 1] + " = "
						+ currentValueNotes);
				change = currentValueNotes;

				checkNote(notes[notesSize - 1]);
			}

			if (notes[0] > change) {
				break;
			}

		}
		System.out.println("Returning change: " + currentValueNotes);
		change = 0;
		return currentValueNotes;

	}

	public void generateChange() {

		double cost = Double.parseDouble(inputCost.getText());
		double amount = Double.parseDouble(inputAmount.getText());
		double change = generateNotes();
		if (change == 0 && counter100euro == 0 && counter50euro == 0 && counter20euro == 0 && counter10euro == 0
				&& counter5euro == 0) {
			change = Math.abs(amount - cost);
		}
		change = Math.round(change * 100.0) / 100.0;
		System.out.println("Current chnage is: " + change);

		while (change != 0) {
			if (change >= coins[coinsSize - 1]) {

				currentValue = change - coins[coinsSize - 1];
				currentValue = Math.round(currentValue * 100.0) / 100.0;
				change = currentValue;
				checkCoin(coins[coinsSize - 1]);

			}

			else {
				coinsSize = coinsSize - 1;
			}

			if (coins[coinsSize - 1] < change) {
				change = Math.round(change * 100.0) / 100.0;
				currentValue = change - coins[coinsSize - 1];
				currentValue = Math.round(currentValue * 100.0) / 100.0;
				System.out.println("Change owed: " + change + " - " + coins[coinsSize - 1] + " = " + currentValue);
				change = currentValue;
				checkCoin(coins[coinsSize - 1]);
			}

		}
		change = 0;
		currentValue = 0;
		currentValueNotes = 0;

	}

	public void checkCoin(double coin) {
		if (coin == 2.0) {
			counter2euro++;
			System.out.println("2 euro used: " + counter2euro);
		}

		if (coin == 1.0) {
			counter1euro++;
			System.out.println("1 euro used: " + counter1euro);
		}

		if (coin == 0.5) {
			counter50cent++;
			System.out.println("50 cent used: " + counter50cent);
		}

		if (coin == 0.2) {
			counter20cent++;
			System.out.println("20 cent used: " + counter20cent);
		}

		if (coin == 0.1) {
			counter10cent++;
			System.out.println("10 cent used: " + counter10cent);
		}

		if (coin == .05) {
			counter5cent++;
			System.out.println("5 cent used: " + counter5cent);
		}

		if (coin == .02) {
			counter2cent++;
			System.out.println("2 cent used: " + counter2cent);
		}

		if (coin == .01) {
			counter1cent++;
			System.out.println("1 cent used: " + counter1cent);
		}

		euro2.setText(String.valueOf(counter2euro));
		euro1.setText(String.valueOf(counter1euro));
		cent50.setText(String.valueOf(counter50cent));
		cent20.setText(String.valueOf(counter20cent));
		cent10.setText(String.valueOf(counter10cent));
		cent5.setText(String.valueOf(counter5cent));
		cent2.setText(String.valueOf(counter2cent));
		cent1.setText(String.valueOf(counter1cent));

	}

	public void checkNote(double coin) {
		if (coin == 100) {
			counter100euro++;
			System.out.println("100 euro used: " + counter100euro);
		}

		if (coin == 50) {
			counter50euro++;
			System.out.println("50 euro used: " + counter50euro);
		}

		if (coin == 20) {
			counter20euro++;
			System.out.println("20 euro used: " + counter20euro);
		}

		if (coin == 10) {
			counter10euro++;
			System.out.println("10 euro used: " + counter10euro);
		}
		if (coin == 5) {
			counter5euro++;
			System.out.println("5 euro used: " + counter5euro);
		}

		euro100.setText(String.valueOf(counter100euro));
		euro50.setText(String.valueOf(counter50euro));
		euro20.setText(String.valueOf(counter20euro));
		euro10.setText(String.valueOf(counter10euro));
		euro5.setText(String.valueOf(counter5euro));
	}

}
