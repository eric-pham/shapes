package com.group0578.hpgame.level2;

/**
 * Methods from this class were derived from Part 5 of Y-Key's "Android Programming - Maze Game" 5
 * part Tutorial and modified to suit the needs of our project
 *
 * <p>Sources: Part 5: https://www.youtube.com/watch?v=icht-iVwWvs&t=8s
 *
 * <p>The class responsible for handling player movement within the maze during level 2
 */
class PlayerPositioner {

  /** The maze containing the player that this object moves around. */
  private Maze maze;

  /**
   * Construct a new instance of a PlayerPositioner object.
   *
   * @param maze the maze associated with the player this object moves.
   */
  PlayerPositioner(Maze maze) {
    this.maze = maze;
  }

  /**
   * Handles the movement of the player by changing the player's position to correspond with the
   * dragging events that are cause by the user's interaction with the MazeView.
   *
   * @param touchX the x coordinate of movement event that was detected.
   * @param touchY the y coordinate of movement event that was detected.
   */
  void handlePlayerMovement(float touchX, float touchY) {
    Player player = this.maze.getPlayer();
    float horizontalMargin = this.maze.getHorizontalMargin();
    float verticalMargin = this.maze.getVerticalMargin();
    float mazeSectionLength = this.maze.getMazeSectionLength();

    if (!player.hasMoved()) {
      player.setHasMoved(true); // Action move event detected so player moved.
    }

    // Player's current x and y locations in the Maze
    float currPlayerX = horizontalMargin + (player.getCol() + 0.5f) * mazeSectionLength;
    float currPlayerY = verticalMargin + (player.getRow() + 0.5f) * mazeSectionLength;

    // The difference between where the user clicked and player's location
    float displacementX = touchX - currPlayerX, displacementY = touchY - currPlayerY;

    float absDisplacementX = Math.abs(displacementX), absDisplacementY = Math.abs(displacementY);

    // Checks if user clicked/dragged "far enough" to move the player
    // Only when the user clicks/drags the cursor a length larger than mazeSectionLength
    // does the player on the screen begin to move.
    if (absDisplacementX > mazeSectionLength || absDisplacementY > mazeSectionLength) {

      if (absDisplacementX > absDisplacementY) { // move horizontally
        if (displacementX > 0) {
          movePlayerRight();
        } else {
          movePlayerLeft();
        }
      } else { // move vertically
        if (displacementY > 0) {
          movePlayerDown();
        } else {
          movePlayerUp();
        }
      }
    }
  }

  /** Moves the player's current location up which means one row higher in the mazeGrid array. */
  private void movePlayerUp() {
    Player player = this.maze.getPlayer();
    MazeSection[][] mazeGrid = this.maze.getMazeGrid();
    if (!mazeGrid[player.getRow()][player.getCol()].getHasTopWall()) {
      player.setRow(player.getRow() - 1);
    }
  }

  /** Moves the player's current location click which means one row larger in the mazeGrid array. */
  private void movePlayerDown() {
    Player player = this.maze.getPlayer();
    MazeSection[][] mazeGrid = this.maze.getMazeGrid();
    if (!mazeGrid[player.getRow()][player.getCol()].getHasBottomWall()) {
      player.setRow(player.getRow() + 1);
    }
  }

  /** Moves the player's current location left: One column smaller in the mazeGrid array */
  private void movePlayerLeft() {
    Player player = this.maze.getPlayer();
    MazeSection[][] mazeGrid = this.maze.getMazeGrid();
    if (!mazeGrid[player.getRow()][player.getCol()].getHasLeftWall()) {
      player.setCol(player.getCol() - 1);
    }
  }

  /** Moves the player's current location right: One column larger in the mazeGrid array */
  private void movePlayerRight() {
    Player player = this.maze.getPlayer();
    MazeSection[][] mazeGrid = this.maze.getMazeGrid();
    if (!mazeGrid[player.getRow()][player.getCol()].getHasRightWall()) {
      player.setCol(player.getCol() + 1);
    }
  }
}
