package view;

/**
 * Represents the user interface of the user's profile.
 */
public class MyProfileUi {

  /**
   * Displays the user's profile details.
   *
   * @param username    of the person
   * @param email       of the person
   * @param phoneNumber of the person
   */
  public void showMyProfileDetails(String username, String email, String phoneNumber) {
    System.out.println("Username: " + username);
    System.out.println("Email: " + email);
    System.out.println("Phone number: " + phoneNumber);
  }

  /*
   * Displays the user's credits.
   */
  public void showMyCredits(int credits) {
    System.out.println("Credits: " + credits);
  }

  /*
   * Displays change username message.
   */
  public void showChangeUsernameMessage() {
    System.out.println("Enter new username: ");
  }

  /*
   * Displays change username success message.
   */
  public void showChangeUsernameSuccessMessage() {
    System.out.println("Username changed successfully!");
  }

  /*
   * Displays change password message.
   */
  public void showChangePasswordMessage() {
    System.out.println("Enter new password: ");
  }

  /*
   * Displays change password success message.
   */
  public void showChangePasswordSuccessMessage() {
    System.out.println("Password changed successfully!");
  }

  /*
   * Displays change email message.
   */
  public void showChangeEmailMessage() {
    System.out.println("Enter new email: ");
  }

  /*
   * Displays change email success message.
   */
  public void showChangeEmailSuccessMessage() {
    System.out.println("Email changed successfully!");
  }

  /*
   * Displays change phone number message.
   */
  public void showChangePhoneNumberMessage() {
    System.out.println("Enter new phone number: ");
  }

  /*
   * Displays change phone number success message.
   */
  public void showChangePhoneNumberSuccessMessage() {
    System.out.println("Phone number changed successfully!");
  }

  /*
   * Displays delete account message.
   */
  public void showDeleteAccountMessage(char yesChoice, char noChoice) {
    System.out.println("Are you sure you want to delete your account? (" + yesChoice + "/" + noChoice + ")");
  }

  /*
   * Displays delete account success message.
   */
  public void showDeleteAccountSuccessMessage() {
    System.out.println("Account deleted successfully!");
  }

  /*
   * Displays delete account cancelled message.
   */
  public void showDeleteAccountCancelledMessage() {
    System.out.println("Account deletion failed!");
  }

  /*
   * Displays not unique email error.
   */
  public void showUniqueEmailError() {
    System.out.println("This email is already in use, please enter a new email.");
  }

  /*
   * Displays not unique phone number error.
   */
  public void showUniquePhoneError() {
    System.out.println("This phone number is already in use, please enter a new number.");
  }

}
