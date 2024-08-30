package model;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public interface MenuOption {

  int getOptionNumber();

  String getDescription();

  boolean matchesOptionNumber(int optionNumber);
}
