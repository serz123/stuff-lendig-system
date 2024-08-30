package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public enum ItemsMenuAction implements MenuOption {
  ADD_ITEM(1, "Add Item"),
  VIEW_MY_ITEMS(2, "View My Items"),
  EDIT_MY_ITEM(3, "Edit My Item"),
  DELETE_MY_ITEM(4, "Delete My Item"),
  SHOW_ALL_ITEMS_AVAILABLE_FOR_BORROWING(5, "Show all Items available for borrowing"),
  BACK(-1, "Back"),
  EXIT(0, "Exit");

  private int optionNumber;
  private String description;

  ItemsMenuAction(int optionNumber, String description) {
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

