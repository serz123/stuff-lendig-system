package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for keeping track of members.
 */
public class MemberRegister {

  private List<Member> members;

  /**
   * Creates a new instance of the MemberRegister class.
   */
  public MemberRegister() {
    this.members = new ArrayList<>();
  }

  /**
   * Adds a member to the register.
   * Ensures uniqueness of email and phone.
   *
   * @param member The member to add.
   * @return true if the member was added, false otherwise.
   */
  public boolean addMember(Member member) {
    for (Member existingMember : members) {
      if (existingMember.getEmail().equals(member.getEmail())
          || existingMember.getPhoneNumber().equals(member.getPhoneNumber())) {
        return false; // Duplicate email or phone found.
      }
    }
    return members.add(member);
  }

  /**
   * Removes a member from the register.
   *
   * @param member The member to remove.
   * @return true if the member was removed, false otherwise.
   */
  public boolean removeMember(Member member) {
    return members.remove(member);
  }

  /**
   * Finds a member by their ID.
   *
   * @param id The ID of the member.
   * @return The member, or null if not found.
   */
  public Member getMemberById(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }

}