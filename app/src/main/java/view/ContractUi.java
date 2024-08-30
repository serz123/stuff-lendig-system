package view;

import model.Contract;

/**
 * This class represents the user interface for displaying messages related to
 * contracts.
 * It contains methods for displaying messages such as item not found, not
 * enough credits, member not found,
 * time conflict, enter start date, enter return date, all contracts, created
 * contract, contract ID,
 * contract start date, contract end date, contract item name, contract lender
 * name, contract borrower name,
 * and contract status.
 */
public class ContractUi {

  /*
   * Displays item not found message.
   */
  public void showItemNotFoundMessage() {
    System.out.println("Item not found!");
  }

  /*
   * Displays not enough credits message.
   */
  public void showNotEnoughCreditsMessage() {
    System.out.println("Not enough credits!");
  }

  /*
   * Displays member not found message.
   */
  public void showMemberNotFoundMessage() {
    System.out.println("Member not found!");
  }

  /*
   * Displays time conflict message.
   */
  public void showTimeConflictMessage() {
    System.out.println("Time conflict with an existing contract!");
  }

  /*
   * Displays enter start day message.
   */
  public void showEnterStartDateDayMessage() {
    System.out.println("Enter the day of the start date: ");
  }

  /*
   * Displays enter start month message.
   */
  public void showEnterStartDateMonthMessage() {
    System.out.println("Enter the month of the start date: ");
  }

  /*
   * Displays enter start year message.
   */
  public void showEnterStartDateYearMessage() {
    System.out.println("Enter the year of the start date: ");
  }

  /*
   * Displays enter return day message.
   */
  public void showEnterReturnDateDayMessage() {
    System.out.println("Enter the day of the return date: ");
  }

  /*
   * Displays enter return month message.
   */
  public void showEnterReturnDateMonthMessage() {
    System.out.println("Enter the month of the return date: ");
  }

  /*
   * Displays enter return year message.
   */
  public void showEnterReturnDateYearMessage() {
    System.out.println("Enter the year of the return date: ");
  }

  /*
   * Shows contract just created message.
   */
  public void showCreatedContractMessage() {
    System.out.println("Contract created!");
  }

  /**
   * Displays the details of a contract.
   *
   * @param contract the contract to be displayed
   */
  public void showContract(Contract contract) {
    System.out.println("Contract ID: " + contract.getContractId() + "\nStart date: "
        + contract.getStartDate().toString() + "\nEnd date: " + contract.getEndDate().toString()
        + "\nItem name: " + contract.getItem().getName() 
        + "\nLender name: " + contract.getLender().getUsername() + "\nBorrower name: "
        + contract.getBorrower().getUsername() + "\n-----------------------------------\n");
  }

  /**
   * Prints a message to the console indicating that the start date is invalid.
   */
  public void showInvalidStartDateMessage() {
    System.out.println("Invalid start date! Please enter a valid start date.");
  }

  /**
   * Displays a message to the user indicating that the entered day is invalid.
   * The message prompts the user to enter a valid day.
   */
  public void showInvalidDayMessage() {
    System.out.println("Invalid day! Please enter a valid day.");
  }

  /**
   * Displays a message to the user indicating that the entered month is invalid.
   */
  public void showInvalidMonthMessage() {
    System.out.println("Invalid month! Please enter a valid month.");
  }

  /**
   * Displays a message to the user indicating that the entered year is invalid.
   */
  public void showInvalidYearMessage() {
    System.out.println("Invalid year! Please enter a valid year.");
  }

  /**
   * Displays a message to the user indicating that the start date has already
   * passed and prompts them to enter a valid start date.
   */
  public void showStartDatePassedMessage() {
    System.out.println("Start date has already passed! Please enter a valid start date.");
  }

  public void showReturnDateBeforeStartDateMessage() {
    System.out.println("Return date is before start date! Please enter a valid return date.");
  }

}
