package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The UserRegister class represents a collection of Iuser objects and provides
 * methods to manage them.
 */

public class UserRegister {

  private ArrayList<Iuser> iusers;
  private Map<String, Iuser> emailToUserMap;
  private Map<String, Iuser> phoneToUserMap;

  /**
   * Constructs a new UserRegister object with an empty list of users and empty
   * maps for email and phone number to user mapping.
   */
  public UserRegister() {
    this.iusers = new ArrayList<>();
    this.emailToUserMap = new HashMap<>();
    this.phoneToUserMap = new HashMap<>();
  }

  /**
   * Check if email is unique.
   *
   * @param email user emial
   * @return boolean
   */
  public boolean isEmailUnique(String email) {
    return !emailToUserMap.containsKey(email.toLowerCase());
  }

  /**
   * Check if phone number is unique.
   *
   * @param phoneNumber user phone number.
   * @return boolean
   */
  public boolean isPhoneNumberUnique(String phoneNumber) {
    return !phoneToUserMap.containsKey(phoneNumber);
  }

  /**
   * Adds a iuser to the register.
   * Ensures uniqueness of email and phone.
   *
   * @param iuser The iuser to add.
   * @return true if the iuser was added, false otherwise.
   */
  public boolean addIuser(Iuser iuser) {
    String emailKey = iuser.getEmail().toLowerCase();
    String phoneKey = iuser.getPhoneNumber();

    // Check if the email and phone are unique
    if (isEmailUnique(emailKey) && isPhoneNumberUnique(phoneKey)) {
      iusers.add(iuser);
      emailToUserMap.put(emailKey, iuser); // Add to email map
      phoneToUserMap.put(phoneKey, iuser); // Add to phone map
      return true;
    }
    return false;
  }

  /**
   * Removes a iuser from the register.
   *
   * @param iuser The iuser to remove.
   * @return true if the iuser was removed, false otherwise.
   */
  public boolean removeIuser(Iuser iuser) {
    if (iusers.remove(iuser)) { // If the user is successfully removed from the list
      emailToUserMap.remove(iuser.getEmail().toLowerCase()); // Remove from email map
      phoneToUserMap.remove(iuser.getPhoneNumber()); // Remove from phone map
      return true;
    }
    return false;
  }

  /**
   * Finds a iuser by their ID.
   *
   * @param id The ID of the iuser.
   * @return The iuser, or null if not found.
   */
  public Iuser getIuserById(String id) {
    for (Iuser iuser : iusers) {
      if (iuser.getId().equals(id)) {
        return iuser;
      }
    }
    return null;
  }

  /**
   * Finds a iuser by their username.
   *
   * @param username The username of the iuser.
   * @return The iuser, or null if not found.
   */
  public String validateLoginCredentials(String username, String password) {
    String existingIuserId = "-1"; // -1 is returned if the username and password do not match any member in the
                                   // system.
    for (Iuser user : iusers) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        existingIuserId = user.getId(); // Username and password match a member in the system.
      }
    }
    return existingIuserId; // Username and password did not match any member in the system.
  }

  /**
   * Finds a iuser by their username.
   *
   * @param id the id of the item we want to find the owner of.
   * @return The iuser, or null if not found.
   */
  public Member getOwnerByItemId(String id) {
    for (Iuser iuser : iusers) {
      if (iuser instanceof Member) {
        Member member = (Member) iuser;
        ArrayList<Item> oneMemberItems = member.getOwnedItems();
        for (Item item : oneMemberItems) {
          if (item.getId().equals(id)) {
            return member;
          }
        }
      }
    }
    return null;
  }

  /**
   * Returns all items in the system.
   *
   * @return ArrayList of all items.
   */
  public ArrayList<Item> getAllItems() {
    ArrayList<Item> allItems = new ArrayList<>();
    for (Iuser iuser : iusers) {
      // Check if iuser is a member.
      if (iuser instanceof Member) {
        Member member = (Member) iuser;
        ArrayList<Item> oneMemberItems = member.getOwnedItems();
        for (Item item : oneMemberItems) {
          allItems.add(item);
        }
      }
    }
    return allItems;
  }

  /**
   * Gets one item based on id.
   *
   * @param id The id of the item to get.
   * @return Item matching id.
   */
  public Item getItemById(String id) {
    ArrayList<Item> allItems = getAllItems();
    Item item = null;
    for (Item oneItem : allItems) {
      if (oneItem.getId().equals(id)) {
        item = oneItem;
      }
    }
    return item;
  }

  /**
   * Gets all items in the system as an arraylist.
   *
   * @return ArrayList of all contracts.
   */
  public ArrayList<Contract> getAllContracts() {
    ArrayList<Contract> allContracts = new ArrayList<>();
    ArrayList<Item> allItems = getAllItems();
    for (Item item : allItems) {
      for (Contract contract : item.getAllContracts()) {
        allContracts.add(contract);
      }
    }
    return allContracts;
  }

  /**
   * Gets all members in the system.
   *
   * @return ArrayList of all Members (a copy of the internal list).
   */
  public ArrayList<Iuser> getListOfAllUsers() {
    return new ArrayList<>(iusers);
  }

}
