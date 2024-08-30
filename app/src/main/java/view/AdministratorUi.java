package view;

import model.AdminMenuAction;
import model.MenuOption;

/**
 * The AdministratorUi class represents the user interface for the administrator
 * of the application.
 * It provides methods to display menus and lists of members, items, and
 * contracts.
 * It also provides a method to advance the day count.
 */

public class AdministratorUi {

  /*
   * Displays general menu options.
   */
  public void showMenuSelectOptionMessage() {
    System.out.println("Please select an option from the menu: ");
  }

  /**
   * Displays the main menu for the admin user.
   */
  public void showMainMenuMessage() {
    for (MenuOption action : AdminMenuAction.values()) {
      System.out.println(action.getOptionNumber() + ". " + action.getDescription());
    }
  }

  /**
   * Displays the message for advancing the day count.
   */
  public void showAdvanceDayMessage() {
    System.out.println("The day count has been advanced by one day.");
  }

  /**
   * Displays the simple information of a member, including their username, email,
   * credits, and number of items.
   *
   * @param username      the username of the member
   * @param email         the email of the member
   * @param credits       the number of credits the member has
   * @param numberOfItems the number of items the member has
   */
  public void displayMemberSimpleInfo(String username, String email, int credits, int numberOfItems) {
    System.out.println("Username: " + username + "\nEmail: " + email
        + "\nCredits: " + credits + "\nNumber of items: " + numberOfItems
        + "\n**********************************\n");
  }

  /**
   * Displays verbose information about a member, including their username, email,
   * credits, and owned items.
   *
   * @param username the username of the member
   * @param email    the email of the member
   * @param credits  the number of credits the member has
   */
  public void displayMemberVerboseInfo(String username, String email, int credits) {
    System.out.println("Username: " + username + "\nEmail: " + email
        + "\nCredits: " + credits + "\nItems: ");
  }
}
