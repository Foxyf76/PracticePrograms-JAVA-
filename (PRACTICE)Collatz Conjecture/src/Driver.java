import java.util.Scanner;

public class Driver {

	public Scanner scanner;

	public Driver() {
		scanner = new Scanner(System.in);
		mainMenu();
	}

	public static void main(String[] args) {
		Driver app = new Driver();
	}

	public void mainMenu() {
		System.out.println("Please enter a number greater than 1: ");
		try {
		int input = scanner.nextInt();
		System.out.println(input+"\n");
		applyAlgorithm(input);
		}
		catch (Exception e) {
			System.out.println("Error!\n" + e);
		}
	}

	public void applyAlgorithm(int input) {
		int counter = 0;
		while (input > 1) {
			if (input % 2 == 0 && input != 1) {
				counter++;
				System.out.println("Input: " + input + " is even, dividing by 2");
				input = input / 2;
				System.out.println("New value for input: " + input);
			}
			if (input % 2 != 0 && input != 1) {
				counter++;
				System.out.println("Input: " + input + " is odd, multplying by 3 & adding by 1");
				input = (input * 3) + 1;
				System.out.println("New value for input: " + input);
			}
		}
		System.out.println("Input equals " + input + ", stopping!");
		System.out.println("------\nIt took: " + counter + " calculations!");
	}
}
