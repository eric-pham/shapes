package com.group0578.hpgame.level2;

/** The class representing the player object moving through the maze. */
class Player {

  /** Whether this player has moved. */
  private boolean hasMoved;

  /** The row and column values of this player's position in the maze grid array. */
  private int row, col;

  /** The number of lives this player has left to complete the maze. */
  private int lives;

  /**
   * Construct a new Player for the Maze.
   *
   * @param row the current row of the Player's position in the Maze Grid.
   * @param col the current column of the Player's position in the Maze Grid.
   */
  Player(int row, int col, int lives) {
    this.row = row;
    this.col = col;
    this.lives = lives;
    this.hasMoved = false;
  }

  /**
   * Getter for whether this player has moved
   *
   * @return true if this player has moved
   */
  boolean hasMoved() {
    return hasMoved;
  }

  /**
   * Setter for whether this player has moved
   *
   * @param hasMoved is true if this player has moved.
   */
  void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  /**
   * Gets the value of row.
   *
   * @return row: an integer that represents the row of this Player in the maze.
   */
  int getRow() {
    return row;
  }

  /**
   * Sets the value of row.
   *
   * @param row the new row attribute for this player.
   */
  void setRow(int row) {
    this.row = row;
  }

  /**
   * Gets the value of col.
   *
   * @return col: an integer that represents the column of this Player in the maze.
   */
  int getCol() {
    return col;
  }

  /**
   * Sets the value of col.
   *
   * @param col the new col attribute for this player.
   */
  void setCol(int col) {
    this.col = col;
  }

  /**
   * Gets the number of lives this player object currently has.
   *
   * @return integer: number of lives
   */
  int getLives() {
    return lives;
  }

  /**
   * Sets the number of lives this player has left to complete the maze.
   *
   * @param lives integer: new number of lives left.
   */
  void setLives(int lives) {
    this.lives = lives;
  }
}
