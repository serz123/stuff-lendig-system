package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public enum RegistrationOrLoginAction implements MenuOption {
  REGISTER(1, "Register"),
  LOGIN(2, "Login"),
  BACK(-1, "Back"),
  EXIT(0, "Exit");

  private int optionNumber;
  private String description;

  RegistrationOrLoginAction(int optionNumber, String description) {
    this.optionNumber = optionNumber;
    this.description = description;
  }

  @Override
  public int getOptionNumber() {
    return optionNumber;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public boolean matchesOptionNumber(int optionNumber) {
    return this.optionNumber == optionNumber;
  }

}
