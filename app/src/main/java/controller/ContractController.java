package controller;

import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Iuser;
import model.Member;
import model.SimpleDate;
import model.TimeTracker;
import model.UserRegister;
import view.ContractUi;
import view.GeneralUi;

/**
 * The ContractController class is responsible for managing contracts between
 * members and items.
 * It contains methods for creating, deleting, and checking the status of
 * contracts.
 */
public class ContractController {

  private ContractUi contractUi = new ContractUi();
  private GeneralUi ui = new GeneralUi();
  private UserRegister userRegister;
  private TimeTracker timeTracker;

  /**
   * Constructor for the ContractController class.
   *
   * @param userRegister In-memory list of users
   */
  protected ContractController(UserRegister userRegister, TimeTracker timeTracker) {
    this.userRegister = userRegister;
    this.timeTracker = timeTracker;
  }

  /**
   * Displays messages to enter start and return dates for a contract, creates a
   * contract with the given dates and item, and displays the created contract
   * information.
   *
   * @param borrower The member who is borrowing the item.
   * @param item     The item being borrowed.
   */
  protected void signContract(Member borrower, Item item, SimpleDate currentDate) {
    SimpleDate startDate = takeInAndValidateStartDate(currentDate);
    if (startDate == null) {
      startDate = takeInAndValidateStartDate(currentDate);
    }
    SimpleDate endDate = takeInAndValidateReturnDate(startDate);
    if (endDate == null) {
      endDate = takeInAndValidateReturnDate(startDate);
    }

    Member lender = userRegister.getOwnerByItemId(item.getId());

    Contract createdContract = createContract(startDate, endDate, item, lender, borrower);
    if (createdContract != null) {
      contractUi.showCreatedContractMessage();
      contractUi.showContract(createdContract);
    }
  }

  /**
   * Displays all contracts related to a given member.
   *
   * @param member the member whose contracts will be displayed
   */
  protected void showCurrentMemberContracts(Member member) {
    ArrayList<Item> memberItems = member.getOwnedItems();
    ArrayList<Item> memberBorrowedItems = member.getBorrowedItems();
    ArrayList<Contract> memberContracts = new ArrayList<>();
    for (Item item : memberItems) {
      memberContracts.addAll(item.getAllContracts());

    }
    for (Item item : memberBorrowedItems) {
      memberContracts.addAll(item.getAllContracts());
    }

    for (Contract contract : memberContracts) {
      contractUi.showContract(contract);
    }

  }

  /**
   * Creates a new contract with the given start date, end date, item, lender, and
   * borrower.
   * Validates if the item exists, if the borrower exists and has enough credits,
   * and if there is no time conflict with existing contracts.
   * If all validations pass, creates a new contract and deducts credits from the
   * borrower's account.
   *
   * @param startDate the start date of the contract
   * @param endDate   the end date of the contract
   * @param item      the item being borrowed
   * @param lender    the member lending the item
   * @param borrower  the member borrowing the item
   * @return the newly created contract, or null if any validation fails
   */
  private Contract createContract(SimpleDate startDate, SimpleDate endDate, Item item, Member lender, Member borrower) {
    // Validate if the item exists
    boolean itemExists = false;
    for (Item i : userRegister.getAllItems()) {
      if (i.getId().equals(item.getId())) {
        itemExists = true;
        break;
      }
    }
    if (!itemExists) {
      contractUi.showItemNotFoundMessage();
      return null;
    }

    // Validate if member exists and has enough credits
    boolean borrowerExists = false;
    ArrayList<Member> allMembers = new ArrayList<>();
    for (Iuser user : userRegister.getListOfAllUsers()) {
      if (user.getRole().equals("Member")) {
        allMembers.add((Member) user);
      }
    }

    for (Member m : allMembers) {
      if (m.getId().equals(borrower.getId())) {
        borrowerExists = true;
        break;
      }
    }
    if (!borrowerExists) {
      contractUi.showMemberNotFoundMessage();
      return null;
    }

    int cost = item.getcostPerDay() * startDate.daysBetween(endDate);
    if (borrower.getCredits() < cost) {
      contractUi.showNotEnoughCreditsMessage();
      return null;
    }

    for (Contract contract : userRegister.getAllContracts()) {
      if (contract.getItem().getId().equals(item.getId())) {
        SimpleDate contractStartDate = contract.getStartDate();
        SimpleDate contractEndDate = contract.getEndDate();

        if (startDate.isInRange(contractStartDate, contractEndDate)
            || endDate.isInRange(contractStartDate, contractEndDate)
            || (startDate.isBefore(contractStartDate) && endDate.isAfter(contractEndDate))) {
          contractUi.showTimeConflictMessage();
          return null;
        }
      }
    }

    // If all validations pass, create a new contract
    Contract newContract = new Contract(startDate, endDate, item, lender, borrower);
    item.addContract(newContract);
    borrower.addBorrowedItem(item);

    // Deduct credits from the borrower's account
    borrower.deductCredits(cost);
    lender.addCredits(cost);

    return newContract;
  }

  /**
   * Deletes a contract from the list.
   *
   * @param contract The contract to be deleted.
   */
  protected void deleteContract(Item item, Contract contract) {
    item.deleteContract(contract);
  }

  /**
   * Checks the status of all contracts.
   */
  protected void checkContractStatus() {
    SimpleDate currentDate = new SimpleDate(timeTracker.getCurrentDay(), timeTracker.getCurrentMonth(),
        timeTracker.getCurrentYear());

    for (Contract contract : userRegister.getAllContracts()) {
      if (isContractActiveOnDate(contract, currentDate)) {
        contract.getItem().setisItemAvailable(false);
      } else {
        contract.getItem().setisItemAvailable(true);
      }
    }
  }

  /*
   * Determines if a contract is active on a given date.
   */
  private boolean isContractActiveOnDate(Contract contract, SimpleDate currentDate) {
    return !currentDate.isBefore(contract.getStartDate()) && !currentDate.isAfter(contract.getEndDate());
  }

  /**
   * Takes in and validates the start date of a contract.
   *
   * @return A SimpleDate object representing the start date of the contract, or
   *         null if the start date is invalid.
   */
  private SimpleDate takeInAndValidateStartDate(SimpleDate currentDate) {
    contractUi.showEnterStartDateDayMessage();
    int inputStartDay = takeInAndValidateDateDay();
    if (inputStartDay == 0) {
      inputStartDay = takeInAndValidateDateDay();
    }
    contractUi.showEnterStartDateMonthMessage();
    int inputStartMonth = takeInAndValidateDateMonth();
    if (inputStartMonth == 0) {
      inputStartMonth = takeInAndValidateDateMonth();
    }
    contractUi.showEnterStartDateYearMessage();
    int inputStartYear = takeInAndValidateDateYear();
    if (inputStartYear == 0) {
      inputStartYear = takeInAndValidateDateYear();
    }
    SimpleDate startDate = new SimpleDate(inputStartDay, inputStartMonth, inputStartYear);
    System.out.println(currentDate);
    if (startDate.isBefore(currentDate)) {
      contractUi.showStartDatePassedMessage();
      return null;
    }
    return startDate;
  }

  /**
   * Prompts the user to enter a return date and validates the input.
   * If the input is invalid, the user is prompted to enter a valid input.
   * If the return date is before the start date, the user is informed and null is
   * returned.
   *
   * @param startDate The start date of the rental period.
   * @return A SimpleDate object representing the return date, or null if the
   *         input is
   *         invalid or the return date is before the start date.
   */
  private SimpleDate takeInAndValidateReturnDate(SimpleDate startDate) {
    contractUi.showEnterReturnDateDayMessage();
    int inputReturnDay = takeInAndValidateDateDay();
    if (inputReturnDay == 0) {
      inputReturnDay = takeInAndValidateDateDay();
    }
    contractUi.showEnterReturnDateMonthMessage();
    int inputReturnMonth = takeInAndValidateDateMonth();
    if (inputReturnMonth == 0) {
      inputReturnMonth = takeInAndValidateDateMonth();
    }
    contractUi.showEnterReturnDateYearMessage();
    int inputReturnYear = takeInAndValidateDateYear();
    if (inputReturnYear == 0) {
      inputReturnYear = takeInAndValidateDateYear();
    }
    SimpleDate returnDate = new SimpleDate(inputReturnDay, inputReturnMonth, inputReturnYear);
    if (returnDate.isBefore(startDate)) {
      contractUi.showReturnDateBeforeStartDateMessage();
      return null;
    }
    return returnDate;
  }

  /**
   * Prompts the user to enter the start date day and validates the input.
   * If the input is invalid, an error message is displayed and 0 is returned.
   *
   * @return The validated start date day inputted by the user.
   */
  private int takeInAndValidateDateDay() {
    try {
      int inputStartDay = Integer.parseInt(ui.getInput());
      if (inputStartDay < 1 || inputStartDay > 31) {
        throw new IllegalArgumentException();
      }
      return inputStartDay;
    } catch (IllegalArgumentException e) {
      contractUi.showInvalidDayMessage();
      return 0;
    }
  }

  /**
   * Prompts the user to enter a start date month and validates the input.
   * If the input is invalid, an error message is displayed and 0 is returned.
   *
   * @return The validated start date month input.
   */
  private int takeInAndValidateDateMonth() {
    try {
      int inputStartDay = Integer.parseInt(ui.getInput());
      if (inputStartDay < 1 || inputStartDay > 12) {
        throw new IllegalArgumentException();
      }
      return inputStartDay;
    } catch (IllegalArgumentException e) {
      contractUi.showInvalidMonthMessage();
      return 0;
    }
  }

  /**
   * Prompts the user to enter a start date year and validates the input.
   * If the input is invalid, an error message is displayed and 0 is returned.
   *
   * @return The validated start date year inputted by the user.
   */
  private int takeInAndValidateDateYear() {
    try {
      int inputStartDay = Integer.parseInt(ui.getInput());
      if (inputStartDay < 2023 || inputStartDay > 2123) {
        throw new IllegalArgumentException();
      }
      return inputStartDay;
    } catch (IllegalArgumentException e) {
      contractUi.showInvalidYearMessage();
      return 0;
    }
  }

}
