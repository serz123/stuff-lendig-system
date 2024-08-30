package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Member;
import model.UserRegister;
import view.GeneralUi;
import view.MyProfileUi;

/**
 * The MemberController class handles the logic for managing member accounts.
 * It contains methods for deleting a member, displaying member details and credits,
 * changing a member's username, password, email and phone number, and validating user input.
 *
 */
public class MemberController {

  private GeneralUi ui = new GeneralUi();
  private MyProfileUi myProfileUi = new MyProfileUi();

  /**
   * Deletes a member from the user register.
   *
   * @param memberId     the ID of the member to be deleted
   * @param userRegister the user register containing the member to be deleted
   */
  private void deleteMember(String memberId, UserRegister userRegister) {
    userRegister.removeIuser(userRegister.getIuserById(memberId));
  }

  /**
   * Displays the details of the given member on the My Profile UI.
   *
   * @param member The member whose details are to be displayed.
   */
  protected void handleViewMyProfileDetails(Member member) {
    myProfileUi.showMyProfileDetails(member.getUsername(), member.getEmail(), member.getPhoneNumber());
  }

  /**
   * Displays the credits of the given member on the UI.
   *
   * @param member The member whose credits are to be displayed.
   */
  protected void handleViewMyCredits(Member member) {
    myProfileUi.showMyCredits(member.getCredits());
  }

  /**
   * This method handles the change of username for a given member.
   * It prompts the user to input a new username, validates it, and sets it as the
   * new username for the member.
   * It also displays success message after the username has been changed.
   *
   * @param member The member whose username is to be changed.
   */
  protected void handleChangeUsername(Member member) {
    myProfileUi.showChangeUsernameMessage();
    String newUsername = takeInAndValidateUsernameInput();
    member.setUsername(newUsername);
    myProfileUi.showChangeUsernameSuccessMessage();
  }

  /**
   * Shows a change password message to the user, takes in and validates the new
   * password input,
   * sets the new password for the given member and shows a success message to the
   * user.
   *
   * @param member the member whose password is being changed
   */
  protected void handleChangePassword(Member member) {
    myProfileUi.showChangePasswordMessage();
    String newPassword = takeInAndValidatePasswordInput();
    member.setPassword(newPassword);
    myProfileUi.showChangePasswordSuccessMessage();
  }

  /**
   * This method handles the change of email for a member.
   * It shows a message to the user to indicate that the email is being changed,
   * takes in and validates the new email input, sets the new email for the
   * member,
   * and shows a success message to the user to indicate that the email has been
   * changed.
   *
   * @param member the member whose email is being changed
   */
  protected void handleChangeEmail(Member member, UserRegister userRegister) {
    myProfileUi.showChangeEmailMessage();
    String newEmail = takeInAndValidateEmailInput();

    if (userRegister.isEmailUnique(newEmail)) {
      member.setEmail(newEmail);
      myProfileUi.showChangeEmailSuccessMessage();
    } else {
      myProfileUi.showUniqueEmailError();
    }
  }

  /**
   * Shows a message to the user to change their phone number, takes in and
   * validates the new phone number input,
   * sets the new phone number to the member object and shows a success message to
   * the user.
   *
   * @param member the member object to change the phone number for
   */
  protected void handleChangePhoneNumber(Member member, UserRegister userRegister) {
    myProfileUi.showChangePhoneNumberMessage();
    String newPhoneNumber = takeInAndValidatePhoneNumberInput();

    if (userRegister.isPhoneNumberUnique(newPhoneNumber)) {
      member.setPhoneNumber(newPhoneNumber);
      myProfileUi.showChangePhoneNumberSuccessMessage();
    } else {
      myProfileUi.showUniquePhoneError();
    }
  }

  /**
   * Takes in user input for username and validates it.
   *
   * @return validated username input
   * @throws IllegalArgumentException if the input is less than 3 characters long
   */
  private String takeInAndValidateUsernameInput() {
    try {
      String input = ui.getInput();
      input = input.trim();
      if (input.length() < 3) {
        throw new IllegalArgumentException("Username must be at least 3 characters long.");
      }
      return input;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ui.tryAgainMessage();
    }
    return takeInAndValidateUsernameInput();
  }

  /**
   * Takes in user input for password and validates it.
   * Password must be at least 8 characters long.
   *
   * @return validated password input
   */
  private String takeInAndValidatePasswordInput() {
    try {
      String input = ui.getInput();
      input = input.trim(); // Assign the trimmed value back to 'input'
      if (input.length() < 8) {
        throw new IllegalArgumentException("Password must be at least 8 characters long.");
      }
      return input;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ui.tryAgainMessage();
    }
    return takeInAndValidatePasswordInput();
  }

  /**
   * Takes user input and validates it as an email address using regular
   * expressions.
   *
   * @return the validated email address
   * @throws IllegalArgumentException if the input is not a valid email address
   *                                  format
   */
  private String takeInAndValidateEmailInput() {
    try {
      String input = ui.getInput();
      // Remove leading and trailing spaces
      input = input.trim();
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
        throw new IllegalArgumentException("Invalid email address format.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ui.tryAgainMessage();
      return takeInAndValidateEmailInput();
    }
  }

  /**
   * Takes user input for a phone number and validates it against a regular
   * expression for Swedish mobile phone numbers.
   * Removes leading and trailing spaces and spaces from the input, if any.
   * If the input matches the regular expression, returns the input. Otherwise,
   * throws an IllegalArgumentException.
   * If an exception is caught, prints the error message and prompts the user to
   * try again.
   *
   * @return the validated phone number input
   */
  private String takeInAndValidatePhoneNumberInput() {
    try {
      String input = ui.getInput();
      // Remove leading and trailing spaces
      input = input.trim();
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
        throw new IllegalArgumentException("Invalid phone number format.");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ui.tryAgainMessage();
      return takeInAndValidatePhoneNumberInput();
    }
  }

  /**
   * Displays a message to confirm the deletion of a member's account and deletes
   * the account if the user confirms.
   *
   * @param member       The member whose account is being deleted.
   * @param userRegister The user register containing the member's information.
   */
  protected void handleDeleteAccount(Member member, UserRegister userRegister) {
    char yesChoice = 'y';
    char noChoice = 'n';
    myProfileUi.showDeleteAccountMessage(yesChoice, noChoice);
    String input = ui.getInput();
    if (input.equalsIgnoreCase(String.valueOf(yesChoice))) {
      deleteMember(member.getId(), userRegister);
      myProfileUi.showDeleteAccountSuccessMessage();
    } else {
      myProfileUi.showDeleteAccountCancelledMessage();
    }
  }
}
