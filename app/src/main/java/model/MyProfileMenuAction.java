package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public enum MyProfileMenuAction implements MenuOption {
  VIEW_MY_PROFILE_DETAILS(1, "View My Profile Details"),
  VIEW_MY_CREDITS(2, "View My Credits"),
  CHANGE_MY_USERNAME(3, "Change My Username"),
  CHANGE_MY_PASSWORD(4, "Change My Password"),
  CHANGE_MY_EMAIL(5, "Change My Email"),
  CHANGE_MY_PHONE_NUMBER(6, "Change My Phone Number"),
  DELETE_ACCOUNT(7, "Delete Account"),
  BACK(-1, "Back"),
  EXIT(0, "Exit");

  private int optionNumber;
  private String description;

  MyProfileMenuAction(int optionNumber, String description) {
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
