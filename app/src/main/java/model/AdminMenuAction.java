package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public enum AdminMenuAction implements MenuOption {
  LIST_ALL_MEMBERS_SIMPLE(1, "List all members - simple"),
  LIST_ALL_MEMBERS_VERBOSE(2, "List all members - verbose"),
  LIST_ALL_ITEMS(3, "List all items"),
  LIST_CONTRACTS(4, "List contracts"),
  ADVANCE_DAY(5, "Advance day count"),
  LOGOUT(6, "Logout"),
  BACK(-1, "Back"),
  EXIT(0, "Exit");

  private int optionNumber;
  private String description;

  AdminMenuAction(int optionNumber, String description) {
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