package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public enum EditItemMenuAction implements MenuOption {
  EDIT_ITEM_NAME(1, "Edit Item Name"),
  EDIT_ITEM_COST(2, "Edit Item Cost"),
  EDIT_ITEM_DESCRIPTION(3, "Edit Item Description"),
  EDIT_ITEM_CATEGORY(4, "Edit Item Category"),
  BACK(-1, "Back"),
  EXIT(0, "Exit");

  private int optionNumber;
  private String description;

  EditItemMenuAction(int optionNumber, String description) {
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

