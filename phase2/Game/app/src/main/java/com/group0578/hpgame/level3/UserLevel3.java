package com.group0578.hpgame.level3;

import com.group0578.hpgame.model.SQLiteHelper;

public class UserLevel3 {

  /** Number of lives the player has. */
  private int lives;
  /** The player's appearance customisation on the screen. */
  private String character;
  /** The difficulty for the game the player is playing. */
  private String difficulty;

  /**
   * The constructor for this user.
   *
   * @param SqlHelper This user's SQLiteHelper.
   * @param username This user's username.
   */
  UserLevel3(SQLiteHelper SqlHelper, String username) {
    this.lives = SqlHelper.findLives(username);
    this.character = SqlHelper.findCharacter(username);
    this.difficulty = SqlHelper.findDifficulty(username);
  }

  /**
   * Getter for this user's lives.
   *
   * @return This user's lives.
   */
  public int getLives() {
    return this.lives;
  }

  /**
   * Getter for this user's appearance on screen.
   *
   * @return This user's appearance on screen.
   */
  public String getCharacter() {
    return this.character;
  }

  /**
   * Getter for this user's game difficulty.
   *
   * @return This user's game difficulty.
   */
  public String getDifficulty() {
    return this.difficulty;
  }

  /** Reduce the number of the user's lives by one. */
  void reduceLives() {
    this.lives = this.lives - 1;
  }
}
