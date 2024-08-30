package view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public class Ui {
  Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

  public void tryAgainMessage() {
    System.out.println("Invalid. Please try again.");
  }

  /**
   * Returns pressed characters from the keyboard.
   */
  public String getInput() {
    String input = scanner.next();
    System.out.println("You entered: " + input);
    return input;
  }

  /*
   * Closes the input scanner.
   */
  public void closeScanner() {
    scanner.close();
  }
}
