import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    Scanner input;
    ArrayList<Character> alphabet = new ArrayList<>((Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')));
    ArrayList<Character> lettersPlaintext = new ArrayList<>();
    ArrayList<Character> lettersCiphertext = new ArrayList<>();
    ArrayList<Integer> indexes = new ArrayList<>();
    ArrayList<String> dictionary = new ArrayList<>();

    public Main() {
        parse();
        input = new Scanner(System.in);
        mainMenu();
    }

    public static void main(String[] args) {
        Main app = new Main();
    }

    public void parse() {
        try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainMenu() {
        System.out.println("\nWELCOME!\n");
        System.out.println("1) Convert Plaintext to Ciphertext");
        System.out.println("2) Convert Ciphertext to Plaintext\n");
        System.out.println("\nPlease select an option: ");
        int option = input.nextInt();

        if (option == 1) {
            System.out.println("Please enter the word you would like to encrypt: ");
            String plaintext = input.nextLine(); // bug with scanner, needs to be declared twice
            plaintext = input.nextLine();
            System.out.println("Enter value to shift alphabet: ");
            int value = input.nextInt();
            while (value <= 0 || value > 25) {
                System.out.println("Please enter a value greater than 0 and less than 25!");
                value = input.nextInt();
            }
            cipher(plaintext.toUpperCase(), value);
            plaintext ="";
            indexes.clear();
            lettersPlaintext.clear();
            mainMenu();
        }

        if (option == 2) {
            System.out.println("Please enter the word you would like to decrypt: ");
            String ciphertext = input.nextLine(); // bug with scanner, needs to be declared twice
            ciphertext = input.nextLine();
            decipher(ciphertext);
            ciphertext="";
            indexes.clear();
            lettersCiphertext.clear();
            mainMenu();
        } else {
            System.out.println("Please enter a valid option!");
            mainMenu();
        }
    }

    public void cipher(String plaintext, int value) {
        int counter = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            lettersPlaintext.add(plaintext.charAt(i));
        }
        while (counter < lettersPlaintext.size()) {
            for (int i = 0; i < lettersPlaintext.size(); i++) {
                for (int j = 0; j < alphabet.size(); j++) {

                    System.out.println("Comparing " + lettersPlaintext.get(i) + " with " + alphabet.get(j));
                    if (lettersPlaintext.get(i) == alphabet.get(j)) {
                        System.out.println("FOUND: " + lettersPlaintext.get(i));
                        counter++;
                        indexes.add(alphabet.indexOf(alphabet.get(j)));
                        System.out.println("INDEX OF VALUE ADDED: " + alphabet.indexOf(alphabet.get(j)));
                    }
                }
            }
        }

        for (int i = 0; i < indexes.size(); i++) {
            System.out.print(alphabet.get(indexes.get(i)));
        }

        Collections.rotate(alphabet, -value);

        System.out.println("\nCIPHERTEXT: ");
        for (int i = 0; i < indexes.size(); i++) {
            System.out.print(alphabet.get(indexes.get(i)));
        }
        System.out.println(" ");
    }

    public void decipher(String ciphertext) {
        int counter = 0;
        boolean foundWord = false;
        for (int i = 0; i < ciphertext.length(); i++) {
            lettersCiphertext.add(ciphertext.charAt(i));
        }
        while (counter < lettersCiphertext.size()) {
            for (int i = 0; i < lettersCiphertext.size(); i++) {
                for (int j = 0; j < alphabet.size(); j++) {
                    if (lettersCiphertext.get(i) == alphabet.get(j)) {
                        counter++;
                        indexes.add(alphabet.indexOf(alphabet.get(j)));
                    }
                }
            }
        }

        while (foundWord == false) {

            String word = constructWord();
            for (int i = 0; i < dictionary.size(); i++) {
                System.out.println("Comparing: " + word + " with " + dictionary.get(i));
                if (word.equals(dictionary.get(i))) {
                    foundWord = true;
                    System.out.println("\nFOUND: " + dictionary.get(i));
                    break;
                } else {
                    constructWord();
                }
            }
        }
    }

    public String constructWord() {
        String word = "";
        int shiftCounter = 1;
        Collections.rotate(alphabet, (-26 + shiftCounter));
        for (int i = 0; i < indexes.size(); i++) {
            word = word + (alphabet.get(indexes.get(i)));
        }
        return word;
    }
}

