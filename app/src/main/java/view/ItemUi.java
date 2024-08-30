package view;

import model.CategoryEnum;

/**
 * Represents the user interface of the item.
 */
public class ItemUi {

  /**
   * Displays a message indicating that a new item is being added.
   */
  public void showAddingItemMessage() {
    System.out.println("Adding a new Item: ");
  }

  /**
   * Displays a message asking the user to enter the name of the item they would
   * like to add.
   */
  public void showAddItemNameMessage() {
    System.out.println("Please enter the name of the item you would like to add:");
  }

  /**
   * Displays a message asking the user to enter the cost of the item to be added.
   */
  public void showAddItemCostMessage() {
    System.out.println("Please enter the credit cost of the item you would like to add:");
  }

  /**
   * Displays a message asking the user to enter the description of the item they
   * would like to add.
   */
  public void showAddItemDescriptionMessage() {
    System.out.println("Please enter the description of the item you would like to add:");
  }

  /**
   * Displays a message asking the user to enter the category number of the item
   * they would like to add,
   * followed by a list of available categories.
   */
  public void showAddItemCategoryMessage() {
    System.out.println("Please enter the category number of the item you would like to add:");
    System.out.println("Categories:");
    for (int i = 1; i < CategoryEnum.values().length; i++) {
      System.out.println(i + ". " + CategoryEnum.values()[i].toString());
    }
  }

  /**
   * Displays a success message indicating that a new item has been added.
   *
   * @param newItem the name of the newly added item
   */
  public void showSuccesfullyAddedItem(String newItem) {
    System.out.println("Item successfully added.");
  }

  /**
   * Prints a message to the console indicating that all items will be displayed.
   */
  public void showListAllItemsMessage() {
    System.out.println("My items: ");
  }

  /**
   * Displays a message asking the user to enter the id of the item they would
   * like to edit.
   */
  public void showEditItemMessage() {
    System.out.println("Please enter the id of the item you would like to edit:");
  }

  /**
   * Displays a message asking the user to enter the new name of the item they
   * would like to edit.
   */
  public void showEditItemNameMessage() {
    System.out.println("Please enter the new name of the item you would like to edit:");
  }

  /**
   * Prints a message to the console indicating that the item name has been
   * successfully edited.
   */
  public void showSuccesfullyEditedItemName() {
    System.out.println("Item name successfully edited.");
  }

  /**
   * Displays a message asking the user to enter the new credit cost of the item
   * they would like to edit.
   */
  public void showEditItemCostMessage() {
    System.out.println("Please enter the new credit cost of the item you would like to edit:");
  }

  /**
   * Prints a message to the console indicating that the item cost has been
   * successfully edited.
   */
  public void showSuccesfullyEditedItemCost() {
    System.out.println("Item cost successfully edited.");
  }

  /**
   * Displays a message asking the user to enter the new description of the item
   * they would like to edit.
   */
  public void showEditItemDescriptionMessage() {
    System.out.println("Please enter the new description of the item you would like to edit:");
  }

  /**
   * Prints a message to the console indicating that the item description has been
   * successfully edited.
   */
  public void showSuccesfullyEditedItemDescription() {
    System.out.println("Item description successfully edited.");
  }

  /**
   * Displays a message asking the user to enter the category number of the item
   * they would like to edit.
   */
  public void showEditItemCategoryMessage() {
    System.out.println("Please enter the new category number of the item you would like to edit:");
    System.out.println("Categories:");
    for (int i = 1; i < CategoryEnum.values().length; i++) {
      System.out.println(i + ". " + CategoryEnum.values()[i].toString());
    }
  }

  /*
   * Displays item category sucessfully edited message.
   */
  public void showSuccesfullyEditedItemCategory() {
    System.out.println("Item category successfully edited.");
  }

  /*
   * Displays delete item message.
   */
  public void showDeleteItemMessage() {
    System.out.println("Please enter the id of the item you would like to delete:");
  }

  /*
   * Displays item sucessfully deleted message.
   */
  public void showSuccesfullyDeletedItem(String itemId) {
    System.out.println("Item successfully deleted: " + itemId);
  }

  /*
   * Displays item does not exist message.
   */
  public void showItemDoesNotExistMessage(String itemId) {
    System.out.println("Item " + itemId + " does not exist.");
  }

  /*
   * Displays item does not belong to user message.
   */
  public void showBorrowItemMessage() {
    System.out.println("Please enter the id of the item you would like to borrow:");
  }

  /**
   * Displays the details of an item, including its name, cost per day,
   * description, category, and availability.
   *
   * @param itemId          the id of the item
   * @param itemName        the name of the item
   * @param itemCostPerDay  the cost per day of the item
   * @param itemDescription the description of the item
   * @param itemCategory    the category of the item
   * @param isAvaliabile    the availability of the item
   */
  public void displayItem(String itemId, String itemName, int itemCostPerDay, String itemDescription,
      CategoryEnum itemCategory, boolean isAvaliabile) {
    String avaliability = "Not avaliable";
    if (isAvaliabile == true) {
      avaliability = "Avaliable";
    }
    System.out.println("Item ID: " + itemId + "\nItem name: " + itemName + "\nItem cost per day: " + itemCostPerDay
        + "\nItem description: " + itemDescription
        + "\nItem category: " + itemCategory
        + "\nItem avaliability: " + avaliability
        + "\n----------------------------------\n");
  }

  /**
   * Prints a message to the console indicating that the item name is too short.
   * The item name must be at least 3 characters long.
   */
  public void showItemNameTooShortMessage(int minItemNameLength) {
    System.out.println("Item name must be at least " + minItemNameLength + " characters long.");
  }

  /**
   * Prints a message to the console indicating that the item cost must be between
   * 1 and 1000.
   */
  public void showInvalidItemCostMessage(int minItemCost, int maxItemCost) {
    System.out.println("Item cost must be between " + minItemCost + " and " + maxItemCost + ".");
  }

  /**
   * Prints a message to the console indicating that the item description is too
   * short.
   * The minimum length for an item description is 10 characters.
   */
  public void showItemDescriptionTooShortMessage(int minItemDescriptionLength) {
    System.out.println("Item description must be at least " + minItemDescriptionLength + " characters long.");
  }

  /**
   * Displays a message to the user indicating that the entered category is
   * invalid.
   * The message prompts the user to enter a valid category number.
   */
  public void showInvalidItemCategoryMessage() {
    System.out.println("Invalid category. Please enter a valid category number.");
  }

}
