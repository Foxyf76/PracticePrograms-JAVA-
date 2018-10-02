package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	public static Parent root;
	public static Stage primaryStage;

	public String currentNumber;
	public Boolean add = false;
	public Boolean multiply = false;
	public Boolean subtract = false;
	public Boolean divide = false;

	public ArrayList<Integer> addNumbers = new ArrayList<>();
	public ArrayList<Integer> divideNumbers = new ArrayList<>();
	public ArrayList<Integer> subtractNumbers= new ArrayList<>();
	public ArrayList<Integer> multiplyNumbers= new ArrayList<>();
	
	int totalDivide = 0;

	@FXML
	public TextField input;
	@FXML
	public Button button1;
	@FXML
	public Button button2;
	@FXML
	public Button button3;
	@FXML
	public Button button4;
	@FXML
	public Button button5;
	@FXML
	public Button button6;
	@FXML
	public Button button7;
	@FXML
	public Button button8;
	@FXML
	public Button button9;
	@FXML
	public Button button0;
	@FXML
	public Button buttonDecimal;
	@FXML
	public Button buttonClear;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/display.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("Calculator");
			Scene scene = new Scene(root, 284, 442);
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
	public void clickButton(Event event) {
		Object node = event.getSource(); // returns the object that generated the event
		Button b = (Button) node;
		String number = b.getText();
		String currentNumber = input.getText();
		input.setText(currentNumber + number);
	}

	@FXML
	public void add() {
		System.out.println(input.getText());
		addNumbers.add(Integer.parseInt(input.getText()));
		input.clear();
		add = true;
	}

	@FXML
	public void divide() {
		divideNumbers.add(Integer.parseInt(input.getText()));
		if (divideNumbers.size() > 1) {
			totalDivide = divideNumbers.get(0) / divideNumbers.get(1);
			System.out.println("FIRST PART: " + divideNumbers.get(0) / divideNumbers.get(1));
		}
		input.clear();
		divide = true;
		System.out.println("total divide: " + totalDivide);

	}

	public void equals() {
		if (add == true) {
			addNumbers.add(Integer.parseInt(input.getText()));
			String value = itterateAdd();
			input.setText(value);
		}

		//
		// if (subtract == true) {
		//
		// }
		//

		if (divide == true) {
			divideNumbers.add(Integer.parseInt(input.getText()));
			String value = itterateDivide();
			input.setText(value);
		}

		//
		// if (multiply == true) {
		//
		// }
	}

	public void clear() {
		add = null;
		subtract = null;
		multiply = null;
		divide = null;
		currentNumber = "";

		addNumbers = new ArrayList<>();

		input.clear();

	}

	public String itterateAdd() {
		int total = 0;
		for (int i = 0; i < addNumbers.size(); i++) {
			System.out.println("Value of total: " + total + " + " + addNumbers.get(i));
			total = total + addNumbers.get(i);
		}

		System.out.println("Value of total: " + total);
		return String.valueOf(total);
	}

	public String itterateDivide() {
		
		for (int i = 0; i < divideNumbers.size(); i+=2) {
			int total = totalDivide;
			int value2 = divideNumbers.get(divideNumbers.size()-1);
			System.out.println("Value of total: " +total + " / " + value2);
			totalDivide = total / value2;
			break;
		}

		System.out.println("Value of total: " + totalDivide);
		return String.valueOf(totalDivide);
	}

}
