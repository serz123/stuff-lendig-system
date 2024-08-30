package controller;

import java.util.ArrayList;
import model.Contract;
import model.Item;
import model.Iuser;
import model.Member;
import model.TimeTracker;
import model.UserRegister;
import view.AdministratorUi;
import view.ContractUi;
import view.GeneralUi;
import view.ItemUi;

/**
 * The AdministratorController class is responsible for handling the actions of
 * an administrator user.
 * It contains methods for listing all members, items, and contracts, as well as
 * advancing the day count.
 */
public class AdministratorController {
  private GeneralUi ui = new GeneralUi();
  private UserRegister userRegister; // In-memory list of
  private AdministratorUi administratorUi = new AdministratorUi(); // UI for the administrator

  /**
   * Constructor for the AdministratorController class.
   *
   * @param userRegister In-memory list of users
   */
  protected AdministratorController(UserRegister userRegister) {
    this.userRegister = userRegister;
  }

  /**
   * Displays a simple list of all members.
   */
  protected void handleListAllMembersSimple() {
    ArrayList<Iuser> allUsers = userRegister.getListOfAllUsers();
    ArrayList<Member> allMembers = new ArrayList<>();
    for (Iuser user : allUsers) {
      if (!user.getRole().equals("Administrator")) {
        allMembers.add((Member) user);
      }
    }
    for (Member member : allMembers) {
      administratorUi.displayMemberSimpleInfo(member.getUsername(), member.getEmail(), member.getCredits(),
          member.getNumberOfItems());
    }
  }

  /**
   * Displays all members in verbose format.
   */
  protected void handleListAllMembersVerbose() {
    ArrayList<Iuser> allUsers = userRegister.getListOfAllUsers();
    ArrayList<Member> allMembers = new ArrayList<>();
    ItemUi itemUi = new ItemUi();
    for (Iuser user : allUsers) {
      if (!user.getRole().equals("Administrator")) {
        allMembers.add((Member) user);
      }
    }
    for (Member member : allMembers) {
      administratorUi.displayMemberVerboseInfo(member.getUsername(), member.getEmail(), member.getCredits());
      ArrayList<Item> ownedItems = member.getOwnedItems();
      for (Item item : ownedItems) {
        itemUi.displayItem(item.getId(), item.getName(),
            item.getcostPerDay(), item.getDescription(),
            item.getCategory(), item.getAvaliability());
      }
    }
  }

  /**
   * Displays all items.
   */
  protected void handleListAllItems() {
    ItemUi itemUi = new ItemUi();
    ArrayList<Item> allItems = userRegister.getAllItems();
    for (Item item : allItems) {
      itemUi.displayItem(item.getId(), item.getName(),
          item.getcostPerDay(), item.getDescription(), item.getCategory(),
          item.getAvaliability());
    }
  }

  /**
   * Retrieves all contracts and displays them in the administrator UI.
   */
  protected void handleListAllContracts() {
    ArrayList<Contract> allContracts = userRegister.getAllContracts();
    ContractUi contractUi = new ContractUi();

    for (Contract contract : allContracts) {
      // Create a copy of the contract so that the original is not modified
      Contract copyOfContract = new Contract(contract.getStartDate(), contract.getEndDate(), contract.getItem(),
          contract.getLender(), contract.getBorrower());
      contractUi.showContract(copyOfContract);
    }

  }

  /**
   * Advances the day count in the TimeTracker object by 1 and updates the UI to
   * reflect the new date.
   *
   * @param timeTracker the TimeTracker object to be updated
   */
  protected void handleAdvanceDayCount(TimeTracker timeTracker) {
    timeTracker.advanceDay(1);
    administratorUi.showAdvanceDayMessage();
    ui.showCurrentDate(timeTracker.getCurrentDate().toString());
  }

}
