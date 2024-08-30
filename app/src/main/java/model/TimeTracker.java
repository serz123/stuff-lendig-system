package model;

import java.time.LocalDate;

/**
 * A class for tracking the current date and time.
 */
public class TimeTracker {
  private LocalDate systemStartDate = LocalDate.now();
  private int daysElapsed = 0;

  /**
   * Sets the system start date.
   *
   * @param daysToAdd represents how many days we want to add to the start date.
   */
  public void advanceDay(int daysToAdd) {
    daysElapsed += daysToAdd; // Just adds one day at a time
  }

  /**
   * Gets the current date after advancing by the number of elapsed days.
   *
   * @return The current date.
   */
  public LocalDate getCurrentDate() {
    return systemStartDate.plusDays(daysElapsed);
  }

  /**
   * Returns the number of days elapsed since the start date.
   *
   * @return Number of days elapsed.
   */
  public int daysSinceStartDate() {
    return daysElapsed;
  }

  /**
   * Returns the day component of the current date.
   *
   * @return The day of the month.
   */
  public int getCurrentDay() {
    return getCurrentDate().getDayOfMonth();
  }

  /**
   * Returns the month component of the current date.
   *
   * @return The month of the year.
   */
  public int getCurrentMonth() {
    return getCurrentDate().getMonthValue();
  }

  /**
   * Returns the year component of the current date.
   *
   * @return The year.
   */
  public int getCurrentYear() {
    return getCurrentDate().getYear();
  }
}
