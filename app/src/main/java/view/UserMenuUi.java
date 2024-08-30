package view;

import model.MenuOption;

/**
 * This class is responsible for displaying the menu options for the user.
 */
public class UserMenuUi {

  /*
   * Displays the menu options for the user.
   */
  public void showMenuSelectOptionMessage() {
    System.out.println("Choose one of the following options and enter the number of the option you want to select.");
  }

  /**
   * Displays the menu options for the user.
   *
   * @param actions The menu options to display.
   */
  public void showMenuOptions(MenuOption[] actions) {
    for (MenuOption action : actions) {
      System.out.println(action.getOptionNumber() + ". " + action.getDescription());
    }
  }

}
