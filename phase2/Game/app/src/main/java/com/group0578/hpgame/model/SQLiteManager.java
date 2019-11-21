package com.group0578.hpgame.model;

/** Manages the SQLite Database */
public class SQLiteManager {

  /**
   * attributes representing the information stored in the columns of the database that is managed
   * by the SQLiteManager.
   */
  private String username, password;
  private String levelDifficulty;
  private String colourScheme;
    private float levelOneTime;
    private float levelTwoTime;
    private float levelThreeTime;
  private int currLives;
  private String progress;
  private int returningUser;
  private String customCharacter;

  /**Sets the username.
   * @param username new username to set. */
  public void setUsername(String username) {
    this.username = username;
  }

  /**Returns the username.
   * @return username: a String. */
  public String getUsername() {
    return username;
  }

  /**Sets the password.
   * @param password new password to set. */
  public void setPassword(String password) {
    this.password = password;
  }

  /**Returns the password.
   * @return password: a String. */
  public String getPassword() {
    return password;
  }

  /**
   * Returns the string representing the level difficulty. Default value is 'Easy'.
   *
   * @return levelDifficulty: a String
   */
  public String getLevelDifficulty() {
    return levelDifficulty;
  }

  /**
   * Sets the level difficulty of the game, possible values are 'Easy', 'Hard'.
   *
   * @param levelDifficulty the String representing the game difficulty.
   */
  public void setLevelDifficulty(String levelDifficulty) {
    this.levelDifficulty = levelDifficulty;
  }

  /**
   * Returns the string representing the colour scheme. Default value is 'Light'.
   *
   * @return colourScheme: a String
   */
  public String getColourScheme() {
    return colourScheme;
  }

  /**
   * Sets the colour scheme, possible values are 'Light', 'Dark'.
   *
   * @param colourScheme the String representing the colour scheme.
   */
  public void setColourScheme(String colourScheme) {
    this.colourScheme = colourScheme;
  }

  /**
   * Returns the time taken to complete level 1. Default value is 0.
   *
   * @return levelOneTime: total time playing level one.
   */
  public float getLevelOneTime() {
    return levelOneTime;
  }

  /**
   * Sets the time taken to complete level 1.
   *
   * @param levelOneTime the time taken to complete level 1.
   */
  public void setLevelOneTime(float levelOneTime) {
    this.levelOneTime = levelOneTime;
  }

  /**
   * Returns the time taken to complete level 2. Default value is 0.
   *
   * @return levelTwoTime: total time playing level two
   */
  public float getLevelTwoTime() {
    return levelTwoTime;
  }

  /**
   * Sets the time taken to complete level 2.
   *
   * @param levelTwoTime the time taken to complete level 2.
   */
  public void setLevelTwoTime(float levelTwoTime) {
    this.levelTwoTime = levelTwoTime;
  }

  /**
   * Returns the time taken to complete level 3. Default value is 0.
   *
   * @return levelThreeTime: total time playing level three
   */
  public float getLevelThreeTime() {
    return levelThreeTime;
  }

  /**
   * Sets the time taken to complete level 3.
   *
   * @param levelThreeTime the time taken to complete level 3.
   */
  public void setLevelThreeTime(float levelThreeTime) {
    this.levelThreeTime = levelThreeTime;
  }

  /**
   * Returns the number of lives the player currently has. Default value is 10 for Easy mode,
   * 5 for Hard mode.
   *
   * @return levelOneTime: an integer
   */
  public int getCurrLives() {
    return currLives;
  }

  /**
   * Sets the amount of lives the user currently has.
   *
   * @param currLives an integer representing the lives of the user.
   */
  public void setCurrLives(int currLives) {
    this.currLives = currLives;
  }

  /**
   * Returns the string representing the user's progress. Default value is 'one'. This gets updated
   * with the most recently completed level.
   *
   * @return progress: a String
   */
  public String getProgress() {
    return progress;
  }

  /**
   * Sets the string representing the user's progress. Default value is 'one'. This gets updated
   * with the most recently completed level.
   *
   * @param progress the most recently completed level.
   */
  public void setProgress(String progress) {
    this.progress = progress;
  }

  /**
   * Returns 0 (representing false) if the user has logged in for the first time. Returns 1
   * (representing true) if the user has logged before.
   *
   * @return returningUser: a String
   */
  public int getReturningUser() {
    return returningUser;
  }

  /**
   * Sets the value to 0 (if the user has logged in for the first time) or 1 (if the user is a
   * returning user).
   *
   * @param returningUser whether the user has logged in before.
   */
  public void setReturningUser(int returningUser) {
    this.returningUser = returningUser;
  }

  /**
   * Gets the custom character selected by the user who is currently logged in
   *
   * @return "A" for character A or "B" for character B
   */
  public String getCharacter() {
    return customCharacter;
  }

  /**
   * Sets the value of the character's appearance for the user who is currently logged in.
   * By default, new users have custom character selection set to option "A"
   *
   * @param customCharacter "A" or "B"
   */
  public void setCharacter(String customCharacter) {
    this.customCharacter = customCharacter;
  }
}
