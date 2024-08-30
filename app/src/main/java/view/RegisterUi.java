package view;

/**
 * The RegisterUi class is responsible for displaying messages related to user
 * registration.
 * It contains methods to prompt the user for registration, ask for username,
 * password, email and phone number,
 * and display success or failure messages.
 */

public class RegisterUi {

  /**
   * Displays a message to prompt the user for registration.
   */
  public void showRegistrationMessage() {
    System.out.println("Registration:");
  }

  /**
   * Displays a message asking the user to enter a username for registration.
   */
  public void showRegistrationUsernameMessage() {
    System.out.println("Please enter a username:");
  }

  /**
   * Displays a message asking the user to enter a password for registration.
   */
  public void showRegistrationPasswordMessage() {
    System.out.println("Please enter a password:");
  }

  /**
   * Displays a message asking the user to enter their email for registration.
   */
  public void showRegistrationEmailMessage() {
    System.out.println("Please enter your email:");
  }

  /**
   * Displays a message asking the user to enter their phone number for
   * registration.
   */
  public void showRegistrationPhoneNumberMessage() {
    System.out.println("Please enter your phone number:");
  }

  /**
   * Displays a message indicating that the registration was successful.
   */
  public void showRegistrationSuccessMessage() {
    System.out.println("Registration successful!");
  }

  public void showRegistrationUsernameTooShortMessage(int minLength) {
    System.out.println("Registration failed. Username must be at least " + minLength + " characters long.");
  }

  /**
   * Displays a message indicating that the registration failed because the
   * password is too short.
   *
   * @param length The minimum length required for the password.
   */
  public void showRegistrationPasswordTooShortMessage(int length) {
    System.out.println("Registration failed. Password must be at least " + length + " characters long.");
  }

  /**
   * Prints a message to the console indicating that the registration failed due
   * to a non-unique phone number or email.
   */
  public void showRegistrationFailureNonUniqueMessage() {
    System.out.println("Registration faied. Username, phone number or email already exists.");
  }

  /**
   * Displays a message indicating that the registration failed due to an invalid
   * email format.
   */
  public void showRegistrationInvalidEmailMessage() {
    System.out.println("Registration failed. Invalid email format.");
  }

  /**
   * Prints a message to the console indicating that the registration failed due
   * to an invalid phone number.
   */
  public void showRegistrationInvalidPhoneNumberMessage() {
    System.out.println("Registration failed. Invalid phone number.");
  }
}
