package com.group0578.hpgame.model;

import java.text.DecimalFormat;

/** Timer class represents the amount of time used to complete a level in this game. */
public class Timer {

  private long start;
  DecimalFormat df = new DecimalFormat("0.000");

  /** Initialize a new Timer Object with the starting time equal to when it is initialized */
  public Timer() {
    start = System.currentTimeMillis();
  }

  /** Start the timer, sets the start time to when this method is called */
  public void start() {
    start = System.currentTimeMillis();
  }

  /**
   * Returns the number of seconds
   *
   * @return number of seconds in long format
   */
  public long getSeconds() {
    return (System.currentTimeMillis() - start) / 1000;
  }

  /**
   * Returns the number of milliseconds
   *
   * @return number of milliseconds in long format
   */
  public long getMilliseconds() {
    return (System.currentTimeMillis() - start) % 1000;
  }

  /**
   * Returns the String representation of seconds
   *
   * @return number of seconds as String
   */
  private String getSecondsString() {
    return String.valueOf((System.currentTimeMillis() - start) / 1000);
  }

  /**
   * Returns the number of seconds that has passed since the starting time.
   *
   * @return number of seconds as Double.
   */
  public double getSecondsPassed() {
    return ((double) System.currentTimeMillis() - start) / 1000;
  }

  /**
   * Returns the String representation of milliseconds, does not include seconds (takes last 3
   * digits of the number of milliseconds.
   *
   * @return number of milliseconds as String
   */
  private String getMillisecondsString() {
    String msString = String.valueOf((System.currentTimeMillis() - start) % 1000);

    // If 00X, where 0<=X<=9 add extra 0's
    if (msString.length() == 1) {
      return "00" + msString;

      // If 0YX, where 0<=X<=9 and 0<=Y<=9 add extra 0's
    } else if (msString.length() == 2) {
      return "0" + msString;
    } else {
      return msString;
    }
  }

  public String getTimerString() {
    return "Seconds : " + this.getSecondsString() + "." + this.getMillisecondsString();
  }

  /**
   * Returns the number of seconds that has passed since the starting time.
   *
   * @return the number of seconds since the start as a String.
   */
  public String getSecondsPassedString() {
    return "Seconds: " + df.format(getSecondsPassed());
  }
}
