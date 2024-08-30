package model;

/**
 * Represents an administrator of the system.
 */
public class Administrator implements Iuser {
  private String username;
  private String password;
  private String role;
  private String id;
  private String email;
  private String phoneNumber;

  /**
   * Creates a new instance of the Administrator class.
   *
   * @param username The username of the administrator.
   * @param password The password of the administrator.
   * @param adminEmail The email of the administrator.
   * @param adminPhoneNumber The phone number of the administrator.
   */
  public Administrator(String username, String password, String adminEmail, String adminPhoneNumber) {
    this.username = username;
    this.password = password;
    this.role = "Administrator";
    this.id = "adminID";
    this.email = adminEmail;
    this.phoneNumber = adminPhoneNumber;
  }

  // THIS VERSION OF THE PROGRAM DOES NOT USE GETTERS AND SETTERS BUT THEY ARE
  // HERE FOR FUTURE USE
  /*
   * @Override getUsername from IUser
   */
  @Override
  public String getUsername() {
    return username;
  }

  /*
   * @Override getPassword from IUser
   */
  @Override
  public String getPassword() {
    return password;
  }

  /*
   * @Override getRole from IUser
   */
  @Override
  public String getRole() {
    return role;
  }

  /*
   * @Override getId from IUser
   */
  @Override
  public String getId() {
    return id;
  }

  /*
   * @Override setUsername from IUser
   */
  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  /*
   * @Override setPassword from IUser
   */
  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  /*
   * @Override getEmail from IUser
   */
  @Override
  public String getEmail() {
    return email;
  }

  /*
   * @Override setEmail from IUser
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /*
   * @Override getPhoneNumber from IUser
   */
  @Override
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /*
   * @Override setPhoneNumber from IUser
   */
  @Override
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
