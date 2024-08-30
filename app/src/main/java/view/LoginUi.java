package view;

/**
 * This class represents the user interface for the login system.
 */
public class LoginUi {
  /**
   * Displays a login message to the user.
   */
  public void showLoginMessage() {
    System.out.println("Login:");
  }

  /**
   * Displays a message asking the user to enter their username.
   */
  public void showLoginUsernameMessage() {
    System.out.println("Please enter your username:");
  }

  /**
   * Displays a message asking the user to enter their password.
   */
  public void showLoginPasswordMessage() {
    System.out.println("Please enter your password:");
  }

  /**
   * Displays a message indicating that the login was successful.
   */
  public void showLoginSuccessMessage(String currentUser) {
    System.out.println("Login successful! Welcome " + currentUser + "!");
  }

  /**
   * Displays a message to the user indicating that the login attempt has failed.
   */
  public void showLoginFailureMessage() {
    System.out.println("Login failed. Please try again.");
  }

  /**
   * Displays a message to the user indicating that the maximum number of login attempts has been
   * reached.
   */
  public void showMaxLoginAttemptsMessage() {
    System.out.println("Maximum number of login attempts reached. Please try again later.");
  }

}
