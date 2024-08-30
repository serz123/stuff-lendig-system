package view;

import java.util.Scanner;

/**
 * Represents the general user interface of the system.
 */
public class GeneralUi {

  /**
   * Scanner object used for user input.
   */
  Scanner scanner = new Scanner(System.in, "UTF-8");

  /**
   * Displays a welcome message to the user.
   */
  public void showWelcomeMessage() {
    System.out.println("Welcome to the Stuff Lending System!");
  }

  /**
   * Prints a message asking the user to try again.
   */
  public void tryAgainMessage() {
    System.out.println("Please try again.");
  }

  public void showCurrentDate(String date) {
    System.out.println("Current date: " + date);
  }

  /**
   * Returns pressed characters from the keyboard.
   */
  public String getInput() {
    String input = scanner.nextLine();
    if ("0".equals(input)) {
      System.out.println("Quitting...");
      scanner.close(); // Close scanner before exiting
      System.exit(0); // Quit requested
    }
    return input;
  }

  /**
   * Closes the scanner object used for user input.
   */
  public void closeScanner() {
    scanner.close();
  }
}
