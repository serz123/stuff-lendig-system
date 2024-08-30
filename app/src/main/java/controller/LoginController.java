package controller;

import model.UserRegister;
import view.GeneralUi;
import view.LoginUi;

/**
 * The LoginController class handles the logic for user login functionality.
 * It uses a UserRegister object to validate user credentials and returns the
 * validated member's ID if successful.
 * If the login fails, it returns -1.
 */
public class LoginController {
  private UserRegister userRegister;
  private LoginUi loginUi = new LoginUi();
  private GeneralUi ui = new GeneralUi();

  /**
   * Constructor for the LoginController class.
   *
   * @param userRegister In-memory list of users
   */
  protected LoginController(UserRegister userRegister) {
    this.userRegister = userRegister;
  }

  /**
   * Handles the login process by displaying login messages, prompting the user
   * for their username and password,
   * validating the credentials, and returning the validated member's ID if
   * successful.
   * If the login fails, it returns -1.
   *
   * @return the validated member's ID if successful, -1 if the login fails.
   */
  protected String handleLogIn() {
    loginUi.showLoginMessage();
    loginUi.showLoginUsernameMessage();
    String username = ui.getInput();
    loginUi.showLoginPasswordMessage();
    String password = ui.getInput();
    String validatedMembersId = userRegister.validateLoginCredentials(username, password);
    if (!validatedMembersId.equals("-1")) {
      loginUi.showLoginSuccessMessage(username);
      return validatedMembersId;
    } else {
      loginUi.showLoginFailureMessage();
      return "-1"; // Return -1 if the login failed
    }
  }
}
