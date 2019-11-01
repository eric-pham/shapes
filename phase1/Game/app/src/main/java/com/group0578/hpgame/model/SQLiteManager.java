package com.group0578.hpgame.model;

/** Manages the SQLite Database */
public class SQLiteManager {

  /** username and password of this SQLiteManager */
  private String username, password;

  /**
   * Sets the username
   *
   * @param username new username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Returns the username
   *
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the password
   *
   * @param password new password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Returns the password
   *
   * @return password
   */
  public String getPassword() {
    return password;
  }
}
