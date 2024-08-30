package model;

import java.util.UUID;

/**
 * Represents a contract created by a member.
 */
public class Contract {
  private String contractId;
  private SimpleDate startDate;
  private SimpleDate endDate;
  private Item item;
  private Member lender;
  private Member borrower;

  /**
   * Constructs a contract with the specified start date, end date, item, lender,
   * and borrower.
   *
   * @param startDate The start date of the contract.
   * @param endDate   The end date of the contract.
   * @param item      The item involved in the contract.
   * @param lender    The member who is lending the item.
   * @param borrower  The member who is borrowing the item.
   */
  public Contract(SimpleDate startDate, SimpleDate endDate, Item item, Member lender, Member borrower) {
    this.contractId = generateContractId();
    setStartDate(startDate);
    setEndDate(endDate);
    setItem(item);
    setLender(lender);
    setBorrower(borrower);
  }

  /**
   * Set a unique contract ID.
   *
   * @return a unique ID string for the contract.
   */
  private String generateContractId() {
    return UUID.randomUUID().toString();
  }

  /**
   * Sets the start date of the contract.
   *
   * @param startDate The start date of the contract.
   */
  private void setStartDate(SimpleDate startDate) {
    this.startDate = startDate;
  }

  /**
   * Sets the end date of the contract.
   *
   * @param endDate The end date of the contract.
   */
  private void setEndDate(SimpleDate endDate) {
    this.endDate = endDate;
  }

  /**
   * Sets the item involved in the contract.
   *
   * @param item The item involved in the contract.
   */
  private void setItem(Item item) {
    this.item = item;
  }

  /**
   * Sets the member who is lending the item.
   *
   * @param lender The member who is lending the item.
   */
  private void setLender(Member lender) {
    this.lender = lender;
  }

  /**
   * Sets the member who is borrowing the item.
   *
   * @param borrower The member who is borrowing the item.
   */
  private void setBorrower(Member borrower) {
    this.borrower = borrower;
  }

  /**
   * Compute the cost of the contract.
   *
   * @return the cost for the contract.
   */
  public int getCost() {
    int contractLength = startDate.daysBetween(endDate);
    return contractLength * item.getcostPerDay();
  }

  /**
   * Retrieves the contract's ID.
   *
   * @return The unique identifier for the contract.
   */
  public String getContractId() {
    return contractId;
  }

  /**
   * Retrieves the start date of the contract.
   *
   * @return The start date of the contract.
   */
  public SimpleDate getStartDate() {
    return startDate;
  }

  /**
   * Retrieves the end date of the contract.
   *
   * @return The end date of the contract.
   */
  public SimpleDate getEndDate() {
    return endDate;
  }

  /**
   * Retrieves the item involved in the contract.
   *
   * @return The item involved in the contract.
   */
  public Item getItem() {
    return this.item;
  }

  /**
   * Retrieves the member who is lending the item.
   *
   * @return The member who is lending the item.
   */
  public Member getLender() {
    // Return a defensive copy of the lender object
    return new Member(lender.getUsername(), lender.getPassword(), lender.getEmail(), lender.getPhoneNumber());
  }

  /**
   * Retrieves the member who is lending the item.
   *
   * @return The member who is lending the item.
   */
  public Member getBorrower() {
    // Return a defensive copy of the borrower object
    return new Member(borrower.getUsername(), borrower.getPassword(), borrower.getEmail(), borrower.getPhoneNumber());
  }

}
