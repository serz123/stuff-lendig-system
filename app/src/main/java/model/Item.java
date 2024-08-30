package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an item that can be used in a system.
 */
public class Item {
  private String id;
  private String name;
  private int costPerDay;
  private String description;
  private CategoryEnum category;
  private LocalDate dayOfRegistration;
  private boolean isItemAvailable;
  private ArrayList<Contract> allContracts = new ArrayList<>();

  /**
   * Constructs an item with the specified name, costPerDay, description, and
   * category.
   *
   * @param name        name of item
   *
   * @param costPerDay  cost of item
   *
   * @param description description of item
   *
   * @param category    category of item
   */
  public Item(String name, int costPerDay, String description, CategoryEnum category) {
    setName(name);
    setcostPerDay(costPerDay);
    setDescription(description);
    setCategory(category);
    setisItemAvailable(true); // When a new item is added, It is avaliable
    this.id = createUniqueItemId();
  }

  /*
   * Creates a unique item ID.
   *
   * @return The unique item ID.
   */
  private String createUniqueItemId() {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    return uuid.substring(0, 6);
  }

  /**
   * Gets the id of the item.
   *
   * @return The id of the item.
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the costPerDay of the item.
   *
   * @return The costPerDay of the item.
   */
  public int getcostPerDay() {
    // Validated in controller
    return costPerDay;
  }

  /**
   * Gets a brief description of the item.
   *
   * @return The description of the item.
   */
  public String getDescription() {
    // Validated in controller
    return description;
  }

  /**
   * Gets the category to which the item belongs.
   *
   * @return The category of the item.
   */
  public CategoryEnum getCategory() {
    return category;
  }

  /**
   * Returns the isItemAvailable of the item.
   *
   * @return true if the item is available, false otherwise.
   */
  public boolean getAvaliability() {
    return isItemAvailable;
  }

  /**
   * Gets the dayOfRegistration of the item.
   *
   * @return The dayOfRegistration of the item.
   */
  public LocalDate getDayOfRegistration() {
    return dayOfRegistration;
  }

  /**
   * Sets the costPerDay of the item.
   *
   * @param costPerDay The costPerDay of the item.
   */
  public void setcostPerDay(int costPerDay) {
    this.costPerDay = costPerDay;
  }

  /**
   * Sets the name of the item.
   *
   * @param name The name of the item.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of the item.
   *
   * @param description The description of the item.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the category of the item.
   *
   * @param category The category of the item.
   */
  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  /**
   * Sets the isItemAvailable of the item.
   *
   * @param isItemAvailable The isItemAvailable of the item, true or false.
   */
  public void setisItemAvailable(boolean isItemAvailable) {
    this.isItemAvailable = isItemAvailable;
  }

  /**
   * Sets the dayOfRegistration of the item.
   *
   * @param dayOfRegistration The dayOfRegistration of the item.
   */
  public void setDayOfRegistration(LocalDate dayOfRegistration) {
    this.dayOfRegistration = dayOfRegistration;
  }

  /**
   * Adds a contract to the item.
   *
   * @param contract The contract to add.
   */
  public void addContract(Contract contract) {
    allContracts.add(contract);
  }

  /**
   * Gets all contracts of the item, both current and upcoming.
   *
   * @return A copy of the ArrayList containing all contracts of the item.
   */
  public ArrayList<Contract> getAllContracts() {
    return new ArrayList<>(allContracts);
  }

  /**
   * Deletes a contract from the item.
   *
   * @param contract The contract to delete.
   */
  public void deleteContract(Contract contract) {
    allContracts.remove(contract);
  }

}
