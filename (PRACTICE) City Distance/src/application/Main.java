package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Main extends Application {

	public static Parent root;
	public static Stage primaryStage;

	@FXML
	public TextField city1;

	@FXML
	public TextField city2;

	@FXML
	public Label distance;
	
	@FXML
	public Label info;

	@FXML
	public HBox hbox;

	@FXML
	public RadioButton km;

	@FXML
	public RadioButton miles;

	HashMap<String, String> map;

	double distanceValue;

	@Override
	public void start(Stage primaryStage) {
		try {
			parse();
			root = FXMLLoader.load(getClass().getResource("/application/display.fxml"));
			Main.primaryStage = primaryStage;
			primaryStage.setResizable(false);
			primaryStage.setTitle("City Distance");
			Scene scene = new Scene(root, 600, 300);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	public HashMap<String, String> parse() throws IOException {

		// Code from:
		// https://stackoverflow.com/questions/29061782/java-read-txt-file-to-hashmap-split-by

		BufferedReader reader = new BufferedReader(new FileReader("worldcities.csv"));

		map = new HashMap<String, String>();

		String line;

		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",", 4);
			if (parts.length >= 2) {
				String key = parts[0];
				String value = parts[1] + ";" + parts[2] + ";" + parts[3];
				map.put(key, value);
			} else {
				System.out.println("ignoring line: " + line);
			}
		}
		for (String key : map.keySet()) {
			System.out.println(key + ";" + map.get(key));
		}
		reader.close();
		return map;
	}

	public void search() throws IOException {
		String input1 = city1.getText();
		String input2 = city2.getText();

		double x1;
		double y1;

		double x2;
		double y2;

		HashMap mymap = parse();
		if (mymap.containsKey(input1) && (mymap.containsKey(input2))) {
			String city1 = (String) mymap.get(input1);
			String city2 = (String) mymap.get(input2);

			String parts1[] = city1.split(";", 3);
			x1 = Double.parseDouble(parts1[0]);
			y1 = Double.parseDouble(parts1[1]);

			System.out.println("City 1 coordinates: " + x1 + " " + y1);

			String parts2[] = city2.split(";", 3);
			x2 = Double.parseDouble(parts2[0]);
			y2 = Double.parseDouble(parts2[1]);

			System.out.println("City 2 coordinates: " + x2 + " " + y2);

			distanceValue = distance(x1, y1, x2, y2);

			hbox.setDisable(false);
			info.setText("Calulating distance from: " + input1.toUpperCase() + ", " +  parts1[2].toUpperCase() + " to: " + input2.toUpperCase() + ", " +  parts2[2].toUpperCase());
			distance.setText(String.valueOf(distanceValue) + " KM");

		} else {
			System.out.println("False");
		}

	}

	public void convertMiles() {
		System.out.println(distanceValue);
		distanceValue = distanceValue / 1.609344;
		distance.setText(String.valueOf(distanceValue) + " MILES");
		km.setSelected(false);
		miles.setDisable(true);
		km.setDisable(false);
	}
	
	public void convertKM() {
		System.out.println(distanceValue);
		distanceValue = distanceValue * 1.609344;
		distance.setText(String.valueOf(distanceValue) + " KM");
		miles.setSelected(false);
		miles.setDisable(false);
		km.setDisable(true);
	}

	/**
	 * Jason Winn http://jasonwinn.org Created July 10, 2013
	 *
	 * Description: Small class that provides approximate distance between two
	 * points using the Haversine formula.
	 *
	 * Call in a static context: Haversine.distance(47.6788206, -122.3271205,
	 * 47.6788206, -122.5271205) --> 14.973190481586224 [km]
	 *
	 */

	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	public static double distance(double startLat, double startLong, double endLat, double endLong) {

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}

	public static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}

}
