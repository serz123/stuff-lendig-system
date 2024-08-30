package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public enum MainMenuAction implements MenuOption {
  ITEMS(1, "Items"),
  MY_PROFILE(2, "My Profile"),
  MY_CONTRACTS(3, "My Contracts"),
  LOGOUT(4, "Logout"),
  BACK(-1, "Back"),
  EXIT(0, "Exit");

  private int optionNumber;
  private String description;

  MainMenuAction(int optionNumber, String description) {
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
