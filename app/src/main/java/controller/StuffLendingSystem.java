package controller;

import model.AdminMenuAction;
import model.Administrator;
import model.CategoryEnum; // This class is not dependent on Cathegory, I just need it for hardcoding test Items
import model.Contract; // This class is not dependent on Contract, I just need it for hardcoding test Items
import model.EditItemMenuAction;
import model.Item;
import model.ItemsMenuAction;
import model.Iuser;
import model.MainMenuAction;
import model.Member;
import model.MenuOption;
import model.MyProfileMenuAction;
import model.RegistrationOrLoginAction;
import model.SimpleDate; // This class is not dependent on SimpleDate, I just need it for hardcoding test Items
import model.TimeTracker;
import model.UserRegister;
import view.AdministratorUi;
import view.GeneralUi;
import view.UserMenuUi;

/**
 * The StuffLendingSystem class represents the main class of the application. It
 * handles the user interface and the flow of the application. It contains
 * methods for handling the authorization menu, member menu, administrator menu,
 * items menu, and my profile menu. It also contains a method for hard coding
 * users and items for testing purposes.
 */

public class StuffLendingSystem {

  private Iuser currentUser;
  UserRegister userRegister = new UserRegister();
  private ItemController itemController;
  private ContractController contractController;
  private GeneralUi ui = new GeneralUi();
  private UserMenuUi userMenuUi = new UserMenuUi();
  private AdministratorUi administratorUi = new AdministratorUi();
  private TimeTracker timeTracker = new TimeTracker();

  /*
   * Constructor for the StuffLendingSystem class.
   */
  public StuffLendingSystem() {
    contractController = new ContractController(userRegister, timeTracker);
  }

  /*
   * The runSystem method is the main method of the application. It runs the
   * application and handles the flow of the application.
   */
  protected void runSystem() {
    ui.showWelcomeMessage();

    ui.showCurrentDate(timeTracker.getCurrentDate().toString());
    // HARD CODED USERS
    hardCodeUsersAndItems();

    handleMemberOrAdminMenu();

    ui.closeScanner();
  }

  /*
   * The handleMemberOrAdminMenu method handles the authorization menu. It
   * contains methods for handling the registration and login of a user. It also
   * contains methods for handling the member menu and administrator menu.
   */
  private void handleMemberOrAdminMenu() {
    handleAuthorizeMenu(); // Id is returned if the User is found in the system and the user is logged in
    // Check weather the user is Member or admin
    if (currentUser instanceof Member) {
      itemController = new ItemController((Member) currentUser, userRegister);

      contractController.checkContractStatus(); // Check if any contracts are overdue
      handleMemberMainMenu();
    } else {
      // ADMIN MENU
      contractController.checkContractStatus(); // Check if any contracts are overdue
      handleAdministatorMainMenu();
    }
  }

  /*
   * The handleAuthorizeMenu method handles the authorization menu. It contains
   * methods for handling the registration and login of a user.
   */
  private void handleAuthorizeMenu() {
    userMenuUi.showMenuSelectOptionMessage();
    userMenuUi.showMenuOptions(RegistrationOrLoginAction.values());
    takeInAndValidateRegistrationOrLoginInput();
  }

  /*
   * The takeInAndValidateRegistrationOrLoginInput method takes in and validates
   * the input from the user in the authorization menu. It contains methods for
   * handling the registration and login of a user.
   */
  private void takeInAndValidateRegistrationOrLoginInput() {
    contractController.checkContractStatus(); // Check if any contracts are overdue
    int input = takeInAndValidateMenuOptionNumberInput(RegistrationOrLoginAction.class);
    MenuOption[] actions = RegistrationOrLoginAction.values();
    MenuOption action = getAction(actions, input);
    RegistrationOrLoginAction registrationOrLoginAction = (RegistrationOrLoginAction) action;

    switch (registrationOrLoginAction) {
      case REGISTER:
        // Registration
        RegistrationController registrationController = new RegistrationController(userRegister);
        registrationController.handleRegistration();
        // Show authorization menu again
        handleAuthorizeMenu();
        break;
      case LOGIN:
        // Log in
        LoginController loginController = new LoginController(userRegister);
        String validatedMemberId = loginController.handleLogIn();
        if (validatedMemberId.equals("-1")) {
          // Show authorization menu again
          handleAuthorizeMenu();
        } else {
          findCurrentMemberbyId(validatedMemberId); // Sets the current user based on the validatedMemberId
        }
        break;
      case EXIT:
        // Quit
        break;
      case BACK:
        // Back - In this case it is exit
        break;
      default:
        // Show authorization menu again
        handleAuthorizeMenu();
        break;
    }
  }

  /*
   * The findCurrentMemberbyId method finds the current user based on the
   * memberId.
   */
  private void findCurrentMemberbyId(String memberId) {
    currentUser = userRegister.getIuserById(memberId);
  }

  /*
   * The handleMemberMainMenu method handles the member menu. It contains methods
   * for showing menu messages.
   */
  private void handleMemberMainMenu() {
    userMenuUi.showMenuSelectOptionMessage();
    userMenuUi.showMenuOptions(MainMenuAction.values());
    takeInAndValidateMainMenuInput();
  }

  /*
   * The takeInAndValidateMainMenuInput method takes in and validates the input
   * from the user in the member menu.
   */
  private void takeInAndValidateMainMenuInput() {
    contractController.checkContractStatus(); // Check if any contracts are overdue
    int input = takeInAndValidateMenuOptionNumberInput(MainMenuAction.class);
    MenuOption[] actions = MainMenuAction.values();
    MenuOption action = getAction(actions, input);
    MainMenuAction mainMenuAction = (MainMenuAction) action;

    switch (mainMenuAction) {
      case ITEMS:
        // Show Items Menu
        handleItemsMenu();
        break;
      case MY_PROFILE:
        // Show My Profile Menu
        handleMyProfileMenu();
        break;
      case MY_CONTRACTS:
        // Show My contracts
        contractController.showCurrentMemberContracts((Member) currentUser);
        handleMemberMainMenu(); // Show Main Menu again
        break;
      case LOGOUT:
        // Logout
        currentUser = null;
        handleMemberOrAdminMenu(); // Show Authorization Menu again
        break;
      case EXIT:
        // Quit
        break;
      case BACK:
        // Back - In this case it is Authorization Menu
        handleAdministatorMainMenu();
        break;
      default:
        // Show Main Menu again
        handleMemberMainMenu();
        break;
    }
  }

  /*
   * The handleItemsMenu method handles the items menu. It contains methods for
   * showing menu messages.
   */
  private void handleItemsMenu() {
    userMenuUi.showMenuSelectOptionMessage();
    userMenuUi.showMenuOptions(ItemsMenuAction.values());
    takeInAndValidateItemsMenuInput();
  }

  /*
   * The takeInAndValidateItemsMenuInput method takes in and validates the input
   * from the user in the items menu.
   */
  private void takeInAndValidateItemsMenuInput() {
    contractController.checkContractStatus(); // Check if any contracts are overdue
    int input = takeInAndValidateMenuOptionNumberInput(ItemsMenuAction.class);
    MenuOption[] actions = ItemsMenuAction.values();
    MenuOption action = getAction(actions, input);
    ItemsMenuAction itemsMenuAction = (ItemsMenuAction) action;

    switch (itemsMenuAction) {
      case ADD_ITEM:
        // Add Item
        itemController.handleAddItem();
        handleItemsMenu(); // Show Items Menu again
        break;
      case VIEW_MY_ITEMS:
        // View My Items
        itemController.handleListAllItems();
        handleItemsMenu(); // Show Items Menu again
        break;
      case EDIT_MY_ITEM:
        // Edit Item
        if (itemController.validateEditItemInput()) {
          // If there are no items, the user is taken back to Items Menu
          handleEditItemMenu(); // Show Items Menu again
          break;
        } else {
          // If there are items, the user is taken to Edit Item Menu
          handleItemsMenu();
        }
        break;
      case DELETE_MY_ITEM:
        // Delete Item
        itemController.handleDeleteItem();
        handleItemsMenu(); // Show Items Menu again
        break;
      case SHOW_ALL_ITEMS_AVAILABLE_FOR_BORROWING:
        // Show all Items available for borrowing and make menu for borrowing items
        itemController.handleBorrowItem();
        Item itemToBorrow = itemController.validateBorrowItemInput();
        if (itemToBorrow == null) {
          // If there are no items, the user is taken back to Items Menu
          handleItemsMenu(); // Show Items Menu again
          break;
        } else {
          // If there are items, the user should borrow item
          SimpleDate currentDate = new SimpleDate(timeTracker.getCurrentDay(),
              timeTracker.getCurrentMonth(),
              timeTracker.getCurrentYear());
          contractController.signContract((Member) currentUser, itemToBorrow, currentDate);
          handleItemsMenu(); // Show Items Menu again
        }
        break;
      case EXIT:
        // Quit
        break;
      case BACK:
        // Back - In this case it is Main Menu
        handleMemberMainMenu();
        break;
      default:
        // Show Items Menu again
        handleItemsMenu();
        break;
    }
  }

  /*
   * The handleEditItemMenu method handles the edit item menu. It contains methods
   * for showing menu messages.
   */
  private void handleEditItemMenu() {
    userMenuUi.showMenuSelectOptionMessage();
    userMenuUi.showMenuOptions(EditItemMenuAction.values());
    takeInAndValidateEditItemMenuInput();
  }

  /*
   * The takeInAndValidateEditItemMenuInput method takes in and validates the
   * input from the user in the edit item
   */
  private void takeInAndValidateEditItemMenuInput() {
    contractController.checkContractStatus(); // Check if any contracts are overdue
    int input = takeInAndValidateMenuOptionNumberInput(EditItemMenuAction.class);
    MenuOption[] actions = EditItemMenuAction.values();
    MenuOption action = getAction(actions, input);
    EditItemMenuAction editItemMenuAction = (EditItemMenuAction) action;

    switch (editItemMenuAction) {
      case EDIT_ITEM_NAME:
        // Edit Item name
        itemController.handleEditItemName();
        handleEditItemMenu(); // Show Edit Item Menu again
        break;
      case EDIT_ITEM_COST:
        // Edit Item cost
        itemController.handleEditItemCost();
        handleEditItemMenu(); // Show Edit Item Menu again
        break;
      case EDIT_ITEM_DESCRIPTION:
        // Edit Item description
        itemController.handleEditItemDescription();
        handleEditItemMenu(); // Show Edit Item Menu again
        break;
      case EDIT_ITEM_CATEGORY:
        // Edit Item category
        itemController.handleEditItemCategory();
        handleEditItemMenu(); // Show Edit Item Menu again
        break;
      case EXIT:
        // Quit
        break;
      case BACK:
        // Back - In this case it is Items Menu
        handleItemsMenu();
        break;
      default:
        // Show Edit Item Menu again
        handleEditItemMenu();
        break;
    }
  }

  /*
   * The handleMyProfileMenu method handles the my profile menu. It contains
   * methods for showing menu messages.
   */
  private void handleMyProfileMenu() {
    userMenuUi.showMenuSelectOptionMessage();
    userMenuUi.showMenuOptions(MyProfileMenuAction.values());
    takeInAndValidateMyProfileMenuInput();
  }

  /*
   * The takeInAndValidateMyProfileMenuInput method takes in and validates the
   * input from the user in the my profile
   */
  private void takeInAndValidateMyProfileMenuInput() {
    contractController.checkContractStatus(); // Check if any contracts are overdue
    Member currentUserMember = (Member) currentUser;
    MemberController memberController = new MemberController();
    int input = takeInAndValidateMenuOptionNumberInput(MyProfileMenuAction.class);
    MenuOption[] actions = MyProfileMenuAction.values();
    MenuOption action = getAction(actions, input);
    MyProfileMenuAction myProfileMenuAction = (MyProfileMenuAction) action;

    switch (myProfileMenuAction) {
      case VIEW_MY_PROFILE_DETAILS:
        // View my profile details
        memberController.handleViewMyProfileDetails(currentUserMember);
        handleMyProfileMenu(); // Show My Profile Menu again
        break;
      case VIEW_MY_CREDITS:
        // View my credits
        memberController.handleViewMyCredits(currentUserMember);
        handleMyProfileMenu(); // Show My Profile Menu again
        break;
      case CHANGE_MY_USERNAME:
        // Change my username
        memberController.handleChangeUsername(currentUserMember);
        handleMyProfileMenu(); // Show My Profile Menu again
        break;
      case CHANGE_MY_PASSWORD:
        // Change my password
        memberController.handleChangePassword(currentUserMember);
        handleMyProfileMenu(); // Show My Profile Menu again
        break;
      case CHANGE_MY_EMAIL:
        // Change my email
        memberController.handleChangeEmail(currentUserMember, userRegister);
        handleMyProfileMenu(); // Show My Profile Menu again
        break;
      case CHANGE_MY_PHONE_NUMBER:
        // Change my phone number
        memberController.handleChangePhoneNumber(currentUserMember, userRegister);
        handleMyProfileMenu(); // Show My Profile Menu again
        break;
      case DELETE_ACCOUNT:
        // Delete account
        memberController.handleDeleteAccount(currentUserMember, userRegister);
        currentUser = null;
        handleMemberOrAdminMenu(); // Show Authorization Menu again
        break;
      case EXIT:
        // Quit
        break;
      case BACK:
        // Back - In this case it is Main Menu
        handleMemberMainMenu();
        break;
      default:
        // Show My Profile Menu again
        handleMyProfileMenu();
        break;
    }
  }

  /*
   * The handleAdministatorMainMenu method handles the administrator menu. It
   * contains methods for showing menu
   */
  private void handleAdministatorMainMenu() {
    administratorUi.showMenuSelectOptionMessage();
    administratorUi.showMainMenuMessage();
    takeInAndValidateAdminMainMenuInput();
  }

  /*
   * The takeInAndValidateAdminMainMenuInput method takes in and validates the
   * input from the user in the administrator.
   */
  private void takeInAndValidateAdminMainMenuInput() {
    contractController.checkContractStatus(); // Check if any contracts are overdue
    AdministratorController administratorController = new AdministratorController(userRegister);
    int input = takeInAndValidateMenuOptionNumberInput(AdminMenuAction.class);
    MenuOption[] actions = AdminMenuAction.values();
    MenuOption action = getAction(actions, input);
    AdminMenuAction adminMenuAction = (AdminMenuAction) action;

    switch (adminMenuAction) {
      case LIST_ALL_MEMBERS_SIMPLE:
        // List all members - simple
        administratorController.handleListAllMembersSimple();
        handleAdministatorMainMenu(); // Show Admin Main Menu again
        break;
      case LIST_ALL_MEMBERS_VERBOSE:
        // List all members - verbose
        administratorController.handleListAllMembersVerbose();
        handleAdministatorMainMenu(); // Show Admin Main Menu again
        break;
      case LIST_ALL_ITEMS:
        // List all items
        administratorController.handleListAllItems();
        handleAdministatorMainMenu(); // Show Admin Main Menu again
        break;
      case LIST_CONTRACTS:
        // List contracts
        administratorController.handleListAllContracts();
        handleAdministatorMainMenu(); // Show Admin Main Menu again
        break;
      case ADVANCE_DAY:
        // Advance day count
        administratorController.handleAdvanceDayCount(timeTracker);
        handleAdministatorMainMenu(); // Show Admin Main Menu again
        break;
      case LOGOUT:
        // Logout
        currentUser = null;
        handleMemberOrAdminMenu(); // Show Authorization Menu again
        break;
      case EXIT:
        // Quit
        break;
      case BACK:
        // Back - In this case it is Main Menu
        handleMemberOrAdminMenu();
        break;
      default:
        // Show Admin Main Menu again
        handleAdministatorMainMenu();
        break;
    }
  }

  /**
   * The takeInAndValidateMenuOptionNumberInput method takes in and validates the
   * input from the user in the menu.
   *
   * @param enumClass The enum class that contains the menu options.
   */
  private <E extends Enum<E> & MenuOption> int takeInAndValidateMenuOptionNumberInput(Class<E> enumClass) {
    int input = -2;
    boolean valid = false;

    while (!valid) {
      try {
        input = Integer.parseInt(ui.getInput());

        // Now we need to check each enum constant if it matches the input.
        E matchedAction = null;
        for (E action : enumClass.getEnumConstants()) {
          if (action.matchesOptionNumber(input)) {
            matchedAction = action;
            break;
          }
        }

        valid = matchedAction != null;
        if (!valid) {
          ui.tryAgainMessage();
        }

      } catch (NumberFormatException e) {
        ui.tryAgainMessage();
      }
    }
    return input;
  }

  /**
   * The getAction method returns the action that matches the input.
   *
   * @param actions The actions that are available in the menu.
   * @param input  The input from the user.
   * @return The action that matches the input.
   */
  private MenuOption getAction(MenuOption[] actions, int input) {
    for (MenuOption registrationOrLoginAction : actions) {
      if (registrationOrLoginAction.matchesOptionNumber(input)) {
        return registrationOrLoginAction;
      }
    }
    return null;
  }

  /*
   * The hardCodeUsersAndItems method hard codes users and items for testing.
   */
  private void hardCodeUsersAndItems() {
    Administrator admin = new Administrator("admin", "aaaaaaaa", "admin@mail.com", "0701234777");
    userRegister.addIuser(admin);

    Member m1 = new Member("vanja", "vvvvvvvv", "vanja@mail.com", "0701234567");
    userRegister.addIuser(m1);
    Item i1 = new Item("Ball", 50, "Nice red ball", CategoryEnum.SPORT);
    Item i2 = new Item("Bike", 10, "Nice red bike", CategoryEnum.SPORT);
    ;
    m1.addItem(i1);
    m1.addItem(i2);
    m1.addCredits(300);

    Member m2 = new Member("tea1", "tttttttt", "tea@mail.com", "0701224567");
    userRegister.addIuser(m2);

    m2.addCredits(100);
    Member m3 = new Member("sheila", "ssssssss", "sheila@mail.com", "0701234447");
    userRegister.addIuser(m3);
    m3.addCredits(100);

    // M3 borrows i2 from M1
    SimpleDate startDate = new SimpleDate(15, 12, 2023);
    SimpleDate endDate = new SimpleDate(17, 12, 2023);
    Contract hardcodedContract = new Contract(startDate, endDate, i2, m1, m3);
    i2.addContract(hardcodedContract);
    m3.addBorrowedItem(i2);
  }
}
