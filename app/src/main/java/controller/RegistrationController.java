package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Member;
import model.UserRegister;
import view.GeneralUi;
import view.RegisterUi;

/**
 * The RegistrationController class handles the registration process for a new
 * member.
 * It shows registration messages and takes in and validates user input for
 * username, password, email and phone number.
 * It creates a new member and adds it to the system.
 */
public class RegistrationController {
  private RegisterUi registerUi = new RegisterUi();
  private GeneralUi ui = new GeneralUi();
  private UserRegister userRegister;

  /**
   * Constructor for the RegistrationController class.
   *
   * @param userRegister In-memory list of users
   */
  protected RegistrationController(UserRegister userRegister) {
    this.userRegister = userRegister;
  }

  /**
   * Handles the registration process for a new member.
   * Shows registration messages and takes in and validates user input for
   * username, password, email and phone number.
   * Creates a new member and adds it to the system.
   *
   * @return void
   */
  protected void handleRegistration() {
    registerUi.showRegistrationMessage();
    registerUi.showRegistrationUsernameMessage();
    String username = takeInAndValidateRegistrationUsernameInput();
    if (username.equals("")) {
      username = takeInAndValidateRegistrationUsernameInput();
    }
    registerUi.showRegistrationPasswordMessage();
    String password = takeInAndValidateRegistrationPasswordInput();
    if (password.equals("")) {
      password = takeInAndValidateRegistrationPasswordInput();
    }
    registerUi.showRegistrationEmailMessage();
    String email = takeInAndValidateRegistrationEmailInput();
    if (email.equals("")) {
      email = takeInAndValidateRegistrationEmailInput();
    }
    registerUi.showRegistrationPhoneNumberMessage();
    String phoneNumber = takeInAndValidateRegistrationPhoneNumberInput();
    if (phoneNumber.equals("")) {
      phoneNumber = takeInAndValidateRegistrationPhoneNumberInput();
    }
    Member member = new Member(username, password, email, phoneNumber);
    if (userRegister.addIuser(member)) {
      registerUi.showRegistrationSuccessMessage(); // If the member was added to the system successfully
    } else {
      registerUi.showRegistrationFailureNonUniqueMessage(); // Member was not added to the system because of non-unique
      // username, email or phone number
    }
  }

  /**
   * Takes in user input for registration username and validates it.
   * If the input is less than 3 characters, an IllegalArgumentException is
   * thrown.
   * If an exception is caught, an error message is printed and the user is
   * prompted to try again.
   *
   * @return the validated username input
   */
  private String takeInAndValidateRegistrationUsernameInput() {
    String input = ui.getInput();
    input = input.trim(); // Assign the trimmed result back to the variable
    if (input.length() < 3) {
      registerUi.showRegistrationUsernameTooShortMessage(3);
      ui.tryAgainMessage();
      return "";
    }
    return input;
  }

  /**
   * Takes in user input for registration password and validates it.
   * If the password is not at least 8 characters long, an
   * IllegalArgumentException is thrown.
   * If an exception is caught, an error message is printed, and the user is
   * prompted to try again.
   *
   * @return validated password input
   */
  private String takeInAndValidateRegistrationPasswordInput() {
    String input = ui.getInput();
    input = input.trim(); // Assign the trimmed result back to the variable
    if (input.length() < 8) {
      registerUi.showRegistrationPasswordTooShortMessage(8);
      ui.tryAgainMessage();
      return "";
    }
    return input;
  }

  /**
   * Takes in user input for email address and validates it using a regular
   * expression.
   * Removes leading and trailing spaces from the input.
   * Returns the validated email address if it matches the regular expression.
   * Throws an IllegalArgumentException if the email address format is invalid.
   * If an exception occurs, prints the error message and prompts the user to try
   * again.
   *
   * @return validated email address
   */
  private String takeInAndValidateRegistrationEmailInput() {
    String input = ui.getInput();
    input = input.trim(); // Assign the trimmed result back to the variable
    // Define a regular expression for validating email addresses
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

    // Create a Pattern object
    Pattern pattern = Pattern.compile(regex);

    // Create a Matcher object
    Matcher matcher = pattern.matcher(input);

    // Check for a match
    if (matcher.matches()) {
      return input;
    } else {
      ui.tryAgainMessage();
      registerUi.showRegistrationInvalidEmailMessage();
      return "";
    }
  }

  /**
   * Takes in user input for phone number and validates it against a regular
   * expression for Swedish mobile phone numbers.
   * Removes any spaces from the input before validation.
   *
   * @return the validated phone number input
   * @throws IllegalArgumentException if the input does not match the regular
   *                                  expression
   */
  private String takeInAndValidateRegistrationPhoneNumberInput() {
    String input = ui.getInput();
    input = input.trim(); // Assign the trimmed result back to the variable
    // Remove spaces from the input, if any
    input = input.replaceAll("\\s", "");

    // Define a regular expression for validating Swedish mobile phone numbers
    String regex = "^(\\+46|0|46)?[1-9]\\d{8}$";

    // Create a Pattern object
    Pattern pattern = Pattern.compile(regex);

    // Create a Matcher object
    Matcher matcher = pattern.matcher(input);

    // Check for a match
    if (matcher.matches()) {
      return input;
    } else {
      ui.tryAgainMessage();
      registerUi.showRegistrationInvalidPhoneNumberMessage();
      return "";
    }
  }
}
