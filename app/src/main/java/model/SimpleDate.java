package model;

/**
 * Represents a date with day, month, and year components.
 * Once created, the date cannot be modified, ensuring immutability.
 */
public class SimpleDate {
  private final int day;
  private final int month;
  private final int year;

  /**
   * Constructs a SimpleDate with specified day, month, and year.
   *
   * @param month The month of the year. Should be in range 1-12.
   * @param year  The year.
   * @throws IllegalArgumentException if the provided date is invalid.
   */
  public SimpleDate(int day, int month, int year) {
    if (!isValidDate(day, month, year)) {
      throw new IllegalArgumentException("Invalid date provided.");
    }

    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Gets the day of the month.
   *
   * @return The day of the month.
   */
  public int getDay() {
    return day;
  }

  /**
   * Gets the month of the year.
   *
   * @return The month of the year.
   */
  public int getMonth() {
    return month;
  }

  /**
   * Gets the year.
   *
   * @return The year.
   */
  public int getYear() {
    return year;
  }

  /**
   * Checks if the provided date is valid.
   *
   * @param day   The day of the month.
   * @param month The month of the year.
   * @param year  The year.
   * @return True if the date is valid, false otherwise.
   */
  private boolean isValidDate(int day, int month, int year) {
    if (month < 1 || month > 12) {
      return false;
    }

    if (day < 1) {
      return false;
    }

    if (month == 2) {
      if (isLeapYear(year)) {
        return day <= 29;
      } else {
        return day <= 28;
      }
    }

    if (month == 4 || month == 6 || month == 9 || month == 11) {
      return day <= 30;
    }

    return day <= 31;
  }

  /**
   * Checks if the provided year is a leap year.
   *
   * @param year The year to check.
   * @return True if the year is a leap year, false otherwise.
   */
  private boolean isLeapYear(int year) {
    if (year % 4 != 0) {
      return false;
    }

    if (year % 100 != 0) {
      return true;
    }
    return year % 400 == 0;
  }

  /**
   * Gets the number of days between this date and the provided date.
   *
   * @param otherDate The other date.
   * @return The number of days between this date and the provided date.
   */
  public int daysBetween(SimpleDate otherDate) {
    return Math.abs(totalDaysFromYearZero() - otherDate.totalDaysFromYearZero());
  }

  /**
   * Gets the total number of days from year zero to this date.
   *
   * @return The total number of days from year zero to this date.
   */
  private int totalDaysFromYearZero() {
    int totalDays = 0;

    // Add days for the years
    for (int i = 0; i < year; i++) {
      totalDays += isLeapYear(i) ? 366 : 365;
    }

    // Add days for the months
    for (int i = 1; i < month; i++) {
      switch (i) {
        case 2:
          totalDays += isLeapYear(year) ? 29 : 28;
          break;
        case 4:
        case 6:
        case 9:
        case 11:
          totalDays += 30;
          break;
        default:
          totalDays += 31;
          break;
      }
    }

    // Add days for the current month
    totalDays += day;

    return totalDays;
  }

  /**
   * Determines if this date is before the provided date.
   *
   * @param other The other date to compare to.
   * @return True if this date is before the provided date, false otherwise.
   */
  public boolean isBefore(SimpleDate other) {
    if (this.year != other.year) {
      return this.year < other.year;
    }
    if (this.month != other.month) {
      return this.month < other.month;
    }
    return this.day < other.day;
  }

  /**
   * Determines if this date is after the provided date.
   *
   * @param other The other date to compare to.
   * @return True if this date is after the provided date, false otherwise.
   */
  public boolean isAfter(SimpleDate other) {
    return other.isBefore(this);
  }

  /**
   * Determines if this date is within the provided range, inclusive.
   *
   * @param startDate The start date of the range.
   * @param endDate   The end date of the range.
   * @return True if this date is within the range, false otherwise.
   */
  public boolean isInRange(SimpleDate startDate, SimpleDate endDate) {
    return !this.isBefore(startDate) && !this.isAfter(endDate);
  }

  public String toString() {
    return String.format("%d/%d/%d", day, month, year);
  }

}
