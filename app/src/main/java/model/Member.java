package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a member of the system.
 */
public class Member implements Iuser {

  private String memberId; // Unique ID for the member
  private String username; // Username of the member
  private String password; // Password of the member
  private String role; // Role of the member
  private String email; // Email address of the member
  private String phoneNumber; // Phone number of the member

  private int credits;
  private ArrayList<Item> ownedItems;
  private ArrayList<Item> borrowedItems;

  /**
   * Constructs a member with the specified username, password, email, and phone
   * number.
   *
   * @param username    The username of the member.
   * @param password    The password of the member.
   * @param email       The email address of the member.
   * @param phoneNumber The phone number of the member.
   */
  public Member(String username, String password, String email, String phoneNumber) {
    setUsername(username);
    setPassword(password);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    this.credits = 0; // Initialize with 0 credits
    this.ownedItems = new ArrayList<>();
    this.borrowedItems = new ArrayList<>();
    this.role = "Member";
    this.memberId = createUniqueMemberId();
  }

  /*
   * Creates a unique member ID.
   *
   * @return The unique member ID.
   */
  private String createUniqueMemberId() {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    return uuid.substring(0, 6);
  }

  /*
   * Overrides the getId() method from the IUser interface.
   */
  @Override
  public String getId() {
    return memberId;
  }

  /*
   * Overrides the getUsername() method from the IUser interface.
   */
  @Override
  public String getUsername() {
    return username;
  }

  /*
   * Overrides the setUsername() method from the IUser interface.
   */
  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  /*
   * Overrides the getPassword() method from the IUser interface.
   */
  @Override
  public String getPassword() {
    return password;
  }

  /*
   * Overrides the setPassword() method from the IUser interface.
   */
  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  /*
   * Overrides the getEmail() method from the IUser interface.
   */
  @Override
  public String getEmail() {
    return email;
  }

  /*
   * Overrides the setEmail() method from the IUser interface.
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * Overrides the getPhoneNumber() method from the IUser interface.
   */
  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * Overrides the setPhoneNumber() method from the IUser interface.
   */
  @Override
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /*
   * Overrides the getRole() method from the IUser interface.
   */
  @Override
  public String getRole() {
    return role;
  }

  /*
   * Gets credits for a Member.
   *
   * @return The credits of the Member.
   */
  public int getCredits() {
    return credits;
  }

  /*
   * Adds credits to a Member.
   *
   * @param credits The credits to add.
   */
  public void addCredits(int credits) {
    this.credits += credits;
  }

  /*
   * Deducts credits from a Member.
   *
   * @param credits The credits to deduct.
   */
  public void deductCredits(int credits) {
    this.credits -= credits;
  }

  /*
   * Gets the items owned by a Member.
   *
   * @return The items owned by a Member.
   */
  public ArrayList<Item> getOwnedItems() {
    return new ArrayList<>(ownedItems);
  }

  /*
   * Gets the items borrowed by a Member.
   *
   * @return The items borrowed by a Member.
   */
  public ArrayList<Item> getBorrowedItems() {
    return new ArrayList<>(borrowedItems);
  }

  /**
   * Deletes an item from a member.
   *
   * @param itemId The ID of the item to delete.
   *
   * @return True if the item was deleted, false otherwise.
   */
  public boolean deleteItem(String itemId) {
    boolean itemDeleted = false;
    for (Item item : ownedItems) {
      if (item.getId().equals(itemId)) {
        ArrayList<Contract> itemContracts = item.getAllContracts();
        for (Contract contract : itemContracts) {
          item.deleteContract(contract);
        }
        ownedItems.remove(item);
        itemDeleted = true;
        return itemDeleted;
      }
    }
    return itemDeleted;
  }


  /**
   * Returns a copy of all the items owned by the member.
   *
   * @return an ArrayList containing copies of all the items owned by the member.
   */
  public ArrayList<Item> getCopyOfAllOwnedItems() {
    return new ArrayList<>(ownedItems);
  }

  /**
   * Adds an item to a Member.
   *
   * @param item The item to add.
   */
  public void addItem(Item item) {
    ownedItems.add(item);
    TimeTracker itemTimeTracker = new TimeTracker();
    LocalDate currentDate = itemTimeTracker.getCurrentDate();
    item.setDayOfRegistration(currentDate);
    addCredits(100);
  }

  /**
   * Adds an item to the list of borrowed items for this member, if it is not already in the list.
   *
   * @param item The item to be added to the list of borrowed items.
   */
  public void addBorrowedItem(Item item) {
    // Check if the item is already in the list of borrowed items for some other date.
    for (Item borrowedItem : borrowedItems) {
      if (borrowedItem.getId().equals(item.getId())) {
        return;
      }
    }
    borrowedItems.add(item);
  }

  /**
   * Gets item by ID.
   *
   * @param id The ID of the item to get.
   *
   * @return The item with the specified ID.
   */
  public Item getItemById(String id) {
    for (Item item : ownedItems) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  /**
   * Returns the number of items owned by this member.
   *
   * @return the number of items owned by this member
   */
  public int getNumberOfItems() {
    return ownedItems.size();
  }
}
