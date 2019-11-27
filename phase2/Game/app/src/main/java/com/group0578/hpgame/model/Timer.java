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
   * Returns the number of seconds that has passed since the starting time.
   *
   * @return number of seconds as Double.
   */
  public double getSecondsPassed() {
    return ((double) System.currentTimeMillis() - start) / 1000;
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
