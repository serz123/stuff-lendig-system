package controller;

import java.util.ArrayList;
import model.CategoryEnum;
import model.Item;
import model.Member;
import model.UserRegister;
import view.GeneralUi;
import view.ItemUi;

/**
 * The ItemController class is responsible for handling user input related to
 * items.
 * It contains methods for adding items to the system, creating new items, and
 * validating user input.
 * This class interacts with the ItemUi, GeneralUi, Member, UserRegister, and
 * Item classes.
 */
public class ItemController {
  private ItemUi itemUi = new ItemUi();
  private GeneralUi ui = new GeneralUi();
  private Member currentUserMember;
  private UserRegister userRegister;
  private Item itemToEdit;

  /**
   * Constructor for the ItemController class.
   *
   * @param currentUserMember The member who is currently logged in.
   * @param userRegister      In-memory list of users.
   */
  protected ItemController(Member currentUserMember, UserRegister userRegister) {
    this.currentUserMember = currentUserMember;
    this.userRegister = userRegister;
  }

  /**
   * Displays messages to the user to add an item to the system and takes in and
   * validates user input for the item's name, cost, description, and category.
   * Shows success message after adding the item.
   */
  protected void handleAddItem() {
    itemUi.showAddingItemMessage();
    itemUi.showAddItemNameMessage();
    String itemName = takeInAndValidateItemNameInput();
    if (itemName.equals("")) {
      itemName = takeInAndValidateItemNameInput();
    }
    itemUi.showAddItemCostMessage();
    int itemCost = takeInAndValidateItemCostInput();
    if (itemCost == -1) {
      itemCost = takeInAndValidateItemCostInput();
    }
    itemUi.showAddItemDescriptionMessage();
    String itemDescription = takeInAndValidateItemDescriptionInput();
    if (itemDescription.equals("")) {
      itemDescription = takeInAndValidateItemDescriptionInput();
    }
    itemUi.showAddItemCategoryMessage();
    CategoryEnum itemCategory = takeInAndValidateItemCategoryInput();
    if (itemCategory == null) {
      itemCategory = takeInAndValidateItemCategoryInput();
    }
    // Create item and add it to the owner's list of items.
    String newItem = addItemToOwner(
        createItem(itemName, itemCost, itemDescription, itemCategory));
    itemUi.showSuccesfullyAddedItem(newItem);
  }

  /**
   * Creates a new item based on the provided parameters.
   *
   * @return A newly created Item object.
   */
  private Item createItem(String chosenName, int chosenCost, String chosenDescription, CategoryEnum chosenCategory) {
    // Arguments are taken from UI / MenuController
    Item newItem = new Item(chosenName, chosenCost, chosenDescription, chosenCategory);

    return newItem;
  }

  /**
   * Adds the newly created item to the owner's list of items.
   *
   * @param newItem The item to be added.
   * @return A string representation of the item that was added.
   */
  private String addItemToOwner(Item newItem) {
    currentUserMember.addItem(newItem);
    return newItem.toString();
  }

  /**
   * This method takes input from the user and validates it to ensure that the
   * item name is at least 3 characters long.
   * If the input is less than 3 characters, an IllegalArgumentException is
   * thrown.
   * If an exception is caught, the error message is printed and the user is
   * prompted to try again.
   */
  private String takeInAndValidateItemNameInput() {
    String input = ui.getInput();
    if (input.length() < 3) {
      itemUi.showItemNameTooShortMessage(3);
      ui.tryAgainMessage();
      return "";
    }
    return input;
  }

  /**
   * Takes in user input for item cost and validates it.
   * If the input is not a valid integer between 1 and 1000, an
   * IllegalArgumentException is thrown.
   * If the input is not a valid integer, the user is prompted to try again.
   */
  private int takeInAndValidateItemCostInput() {
    Integer input = 0;
    try {
      input = Integer.parseInt(ui.getInput());
    } catch (NumberFormatException e) {
      itemUi.showInvalidItemCostMessage(1, 1000);
      ui.tryAgainMessage();
      return -1;
    }
    if (input < 1 || input > 1000) {
      itemUi.showInvalidItemCostMessage(1, 1000);
      ui.tryAgainMessage();
      return -1;
    }
    return input;
  }

  /**
   * This method takes in user input for an item description and validates it.
   * If the input is less than 10 characters long, an IllegalArgumentException is
   * thrown.
   * If an exception is caught, the error message is printed and the user is
   * prompted to try again.
   */
  private String takeInAndValidateItemDescriptionInput() {
    String input = ui.getInput();
    if (input.length() < 10) {
      itemUi.showItemDescriptionTooShortMessage(10);
      ui.tryAgainMessage();
      return "";
    }
    return input;
  }

  /**
   * Takes in user input for item category and validates it.
   *
   * @return the name of the category based on the inputed number of the category,
   *         or null if the input was invalid.
   * @throws IllegalArgumentException if the input is not between 0 and 5.
   */
  private CategoryEnum takeInAndValidateItemCategoryInput() {
    Integer input = 0;
    try {
      input = Integer.parseInt(ui.getInput());
    } catch (NumberFormatException e) {
      itemUi.showInvalidItemCategoryMessage();
      ui.tryAgainMessage();
      return null;
    }
    int numberOfCategories = CategoryEnum.values().length;
    if (input < 0 || input > numberOfCategories) {
      itemUi.showInvalidItemCategoryMessage();
      ui.tryAgainMessage();
      return null;
    }
    // If the input is valid, return the category name based on the inputed number
    // of the category.
    CategoryEnum category = CategoryEnum.values()[0];
    for (int i = 0; i < numberOfCategories; i++) {
      if (input == i) {
        category = CategoryEnum.values()[i];
      }
    }
    return category;
  }

  /**
   * Displays a message to the user to list all items and shows all items in the
   * current user's inventory.
   */
  protected void handleListAllItems() {
    itemUi.showListAllItemsMessage();
    ArrayList<Item> itemList = currentUserMember.getCopyOfAllOwnedItems();
    for (Item item : itemList) {
      itemUi.displayItem(item.getId(), item.getName(), item.getcostPerDay(), item.getDescription(), item.getCategory(),
          item.getAvaliability());
    }
  }

  /**
   * Validates the input for editing an item.
   * Shows the edit item message and handles listing all items.
   * Gets the item ID input from the user and checks if it exists in the current
   * user's member items.
   * If the item exists, sets the item to edit and returns true.
   * Otherwise, shows an item does not exist message and returns false.
   *
   * @return true if the input is valid and the item exists, false otherwise.
   */
  protected boolean validateEditItemInput() {
    itemUi.showEditItemMessage();
    handleListAllItems();
    String itemIdInput = ui.getInput();
    if (currentUserMember.getItemById(itemIdInput) != null) {
      itemToEdit = currentUserMember.getItemById(itemIdInput);
      return true;
    } else {
      itemUi.showItemDoesNotExistMessage(itemIdInput);
      return false;
    }
  }

  /**
   * Displays a message to edit the item name, takes in and validates the input,
   * sets the new name to the item to edit,
   * and displays a success message.
   */
  protected void handleEditItemName() {
    itemUi.showEditItemNameMessage();
    String newUsername = takeInAndValidateItemNameInput();
    itemToEdit.setName(newUsername);
    itemUi.showSuccesfullyEditedItemName();
  }

  /**
   * Displays a message to the user to edit the cost of an item, takes in and
   * validates the new cost input,
   * sets the new cost of the item to be edited, and displays a success message to
   * the user.
   */
  protected void handleEditItemCost() {
    itemUi.showEditItemCostMessage();
    int newCost = takeInAndValidateItemCostInput();
    itemToEdit.setcostPerDay(newCost);
    itemUi.showSuccesfullyEditedItemCost();
  }

  /**
   * Displays a message to edit the item description, takes in and validates the
   * new description input,
   * sets the new description to the item to be edited, and displays a success
   * message.
   */
  protected void handleEditItemDescription() {
    itemUi.showEditItemDescriptionMessage();
    String newDescription = takeInAndValidateItemDescriptionInput();
    itemToEdit.setDescription(newDescription);
    itemUi.showSuccesfullyEditedItemDescription();
  }

  /**
   * Displays a message to the user to edit the category of an item, takes in and
   * validates the new category input,
   * sets the new category to the item being edited, and displays a success
   * message to the user.
   */
  protected void handleEditItemCategory() {
    itemUi.showEditItemCategoryMessage();
    CategoryEnum newCategory = takeInAndValidateItemCategoryInput();
    itemToEdit.setCategory(newCategory);
    itemUi.showSuccesfullyEditedItemCategory();
  }

  /**
   * This method handles the deletion of an item by prompting the user for the
   * item ID,
   * showing a message indicating that the item is about to be deleted,
   * displaying a list of all items belonging to the current user,
   * and then deleting the item if it exists.
   * If the item is successfully deleted, a success message is displayed.
   * If the item does not exist, a message indicating that the item does not exist
   * is displayed.
   */
  protected void handleDeleteItem() {
    itemUi.showDeleteItemMessage();
    handleListAllItems();
    String itemIdInput = ui.getInput();
    if (currentUserMember.deleteItem(itemIdInput)) {
      itemUi.showSuccesfullyDeletedItem(itemIdInput);
    } else {
      itemUi.showItemDoesNotExistMessage(itemIdInput);
    }
  }

  /**
   * Displays all available items to the user interface.
   */
  private void handleListAllAvaliableItems() {
    ArrayList<Item> itemList = userRegister.getAllItems();
    for (Item item : itemList) {
      if (item.getAvaliability() == true) {
        itemUi.displayItem(item.getId(), item.getName(),
            item.getcostPerDay(), item.getDescription(), item.getCategory(),
            item.getAvaliability());
      }
    }
  }

  /**
   * Displays all available items and prompts the user to borrow an item.
   * Calls the handleListAllAvaliableItems method to display all available items.
   * Calls the showBorrowItemMessage method of the itemUi object to prompt the
   * user to borrow an item.
   */
  protected void handleBorrowItem() {
    handleListAllAvaliableItems();
    itemUi.showBorrowItemMessage();
  }

  /**
   * Validates the input for borrowing an item.
   *
   * @return the item to borrow if it exists, otherwise null.
   */
  protected Item validateBorrowItemInput() {
    String itemIdInput = ui.getInput();
    if (itemIdInput.equals("0")) {
      System.exit(0);
    }
    if (userRegister.getItemById(itemIdInput) != null) {
      Item itemToBorrow = userRegister.getItemById(itemIdInput);
      return itemToBorrow;
    } else {
      itemUi.showItemDoesNotExistMessage(itemIdInput);
      return null;
    }
  }

}
