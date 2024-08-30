package model;

/**
 * Represents a user of the system.
 */
public interface Iuser {
  String getId();

  String getUsername();

  void setUsername(String username);

  String getPassword();

  void setPassword(String password);

  String getEmail();

  void setEmail(String email);

  String getPhoneNumber();

  void setPhoneNumber(String phoneNumber);

  String getRole();

  String toString();
}
