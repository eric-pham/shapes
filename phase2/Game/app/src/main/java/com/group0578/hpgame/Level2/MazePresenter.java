package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

/** The MazePresenter is responsible for implementing actions the user performs on the screen. */
public class MazePresenter implements Maze.Presenter {


  /** The width of the screen representing this MazeView. */
  private int screenWidth;

  /** The height of the screen representing this MazeView. */
  private int screenHeight;

  /** The array representing the maze; structured as a grid where each element is a MazeSection. */
  private MazeSection[][] mazeGrid;

  /**
   * The Player object that is currently playing the Maze. Used for keeping track of current
   * location in the Maze.
   */
  private Player player;

  /** The final location in the mazeGrid array the user reaches upon completion of the Maze. */
  private MazeSection exitPoint;

  /** The Paint object used to draw the grid lines of the maze on the screen. */
  private Paint mazeBrush;

  /** The numerical value for the length of each MazeSection in the mazeGrid array. */
  private float mazeSectionLength;

  /** The length between the top of the screen and beginning of the Maze. */
  private float verticalMargin;

  /** The length between the sides of the screen and the Maze. */
  private float horizontalMargin;

  /**The Paint objects used to draw and distinguish the Player's current location from the exit
   * point. */
  private Paint playerPaint, exitPointPaint;

  /** The Paint object used to draw text on the screen -- used for creating the legend. */
  private Paint textBrush;

  /**Presenter accesses the UseCases to manipulate maze entities/data such as MazeSection to build
   * the MazeGrid. */
  private MazeUseCases mazeUseCases;

  /**Constructor.
   * @param mazeView the MazeView accessing this Presenter object. */
  MazePresenter(MazeView mazeView) {
    /** The mazeView interface reference attribute. */
    this.mazeUseCases = new MazeUseCases();
  }

  /**Gets the paint object that is responsible for the text in the maze game.
   * @return textBrush: a paint object. */
  Paint getTextBrush() {
    return textBrush;
  }

  /** Sets the paint object that is responsible for the text in the maze game.
   * @param textBrush a paint object. */
  void setTextBrush(Paint textBrush) {
    this.textBrush = textBrush;
  }

  /**Gets the width of the screen.
   * @return screenWidth: an integer representing the width of the screen. */
  int getScreenWidth() {
    return screenWidth;
  }

  /** Sets the value of the variable representing the width of the screen.
   * @param screenWidth width of the screen. */
  void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
  }

  /**Gets the height of the screen.
   * @return screenHeight: an integer representing the height of the screen. */
  int getScreenHeight() {
    return screenHeight;
  }

  /** Sets the value of the variable representing the height of the screen. ]
   * @param screenHeight height of the screen. */
  void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
  }

  /**Gets a MazeSection 2D array list representation of the maze.
   * @return mazeGrid: a MazeSection 2D array list representing the maze. */
  MazeSection[][] getMazeGrid() {
    return mazeGrid;
  }

  /** Sets the value of the MazeSection 2D array list representing the maze.
   * @param mazeGrid representation of the maze. */
  void setMazeGrid(MazeSection[][] mazeGrid) {
    this.mazeGrid = mazeGrid;
  }

  /**Gets the Player object associated with the maze.
   * @return player: a Player object. */
  Player getPlayer() {
    return player;
  }

  /** Sets the Player object that will go through the maze.
   * @param player a Player object. */
  void setPlayer(Player player) {
    this.player = player;
  }

  /**Gets the MazeSection object associated with the exit point of the maze.
   * @return exitPoint: a MazeSection object. */
  MazeSection getExitPoint() {
    return exitPoint;
  }

  /** Sets the value of the MazeSection representing the exit.
   * @param exitPoint a MazeSection object. */
  void setExitPoint(MazeSection exitPoint) {
    this.exitPoint = exitPoint;
  }

  /**Gets the paint object that is responsible for the maze outline in the maze game.
   * @return mazeBrush: a Paint object. */
  public Paint getMazeBrush() {
    return mazeBrush;
  }

  /** Sets the Paint object that is responsible for the maze outline in the maze game.
   * @param mazeBrush a Paint object. */
  void setMazeBrush(Paint mazeBrush) {
    this.mazeBrush = mazeBrush;
  }

  /**Gets the value of the float that represents the length of the walls of the MazeSection.
   * @return mazeSectionLength: a float. */
  float getMazeSectionLength() {
    return mazeSectionLength;
  }

  /**Sets the float variable that represents the length of the walls of the MazeSection.
   * @param mazeSectionLength: a float. */
  void setMazeSectionLength(float mazeSectionLength) {
    this.mazeSectionLength = mazeSectionLength;
  }

  /**Sets the float variable that represents the length of the vertical margin between the
   * top/bottom of the screen and the edges of the maze.
   * @param verticalMargin a float. */
  void setVerticalMargin(float verticalMargin) {
    this.verticalMargin = verticalMargin;
  }

  /**Sets the float variable that represents the length of the horizontal margin between the
   * sides of the screen and the edges of the maze.
   * @param horizontalMargin a float. */
  void setHorizontalMargin(float horizontalMargin) {
    this.horizontalMargin = horizontalMargin;
  }

  /**Gets the Paint object that is responsible for the player in the maze game.
   * @return playerPaint: a Paint object. */
  public Paint getPlayerPaint() {
    return playerPaint;
  }

  /** Sets the paint object that is responsible for the player in the maze game.
   * @param playerPaint a paint object. */
  void setPlayerPaint(Paint playerPaint) {
    this.playerPaint = playerPaint;
  }

  /**Gets the Paint object that is responsible for the exit point in the maze game.
   * @return exitPointPaint: a Paint object. */
  public Paint getExitPointPaint() {
    return exitPointPaint;
  }

  /** Sets the paint object that is responsible for the exit point in the maze game.
   * @param exitPointPaint a paint object. */
  void setExitPointPaint(Paint exitPointPaint) {
    this.exitPointPaint = exitPointPaint;
  }


  /**Calls method in mazeUseCases which makes the mazeGrid for the maze.
   * @return 2-dimensional array of MazeSection objects holding the entire mazeGrid. */
  MazeSection[][] buildMazeGrid() {
    return mazeUseCases.buildMazeGrid();
  }

  /**Gets the number of the rows and columns in the mazeGrid.
   * @return 2 element integer array representing number of rows, columns in the mazeGrid array. */
  int[] getRowColumnAttributes() {
    return new int[] {mazeUseCases.getROWS(), mazeUseCases.getCOLS()};
  }

  /**
   * Drawing the walls of the maze based on if each mazeSection in the mazeGrid has a top, bottom,
   * left or right wall
   *
   * @param mazeCanvas the canvas on which to draw the walls of the maze.
   */
  void drawMazeWalls(Canvas mazeCanvas) {
    String TAG = "MazeView.drawMazeWalls";
    Log.i(TAG, "Trying to draw walls");

    // translating canvas makes drawing walls easier
    // moves 'origin' of where objects are drawn on the screen to top-left corner of the maze
    mazeCanvas.translate(this.horizontalMargin, this.verticalMargin);

    // Drawing the walls for each of the mazeSections inside mazeGrid array
    for (int row = 0; row < this.mazeGrid.length; row++) {
      for (int col = 0; col < this.mazeGrid[0].length; col++) {
        // if there is a top wall
        if (this.mazeGrid[row][col].getHasTopWall()) {
          drawTopWall(row, col, mazeCanvas);
        }
        // if there is a bottom wall
        if (this.mazeGrid[row][col].getHasBottomWall()) {
          drawBottomWall(row, col, mazeCanvas);
        }
        // if there is a left wall
        if (this.mazeGrid[row][col].getHasLeftWall()) {
          drawLeftWall(row, col, mazeCanvas);
        }
        // if there is a right wall
        if (this.mazeGrid[row][col].getHasRightWall()) {
          drawRightWall(row, col, mazeCanvas);
        }
      }
    }
  }

  /**
   * Drawing a line on the screen representing the top wall of some mazeSection in the mazeGrid
   * array.
   *
   * @param row the row in the mazeGrid of where to draw the top wall
   * @param col the column in the mazeGrid of where to draw the top wall
   * @param mazeCanvas the canvas on which to draw the top wall
   */
  private void drawTopWall(int row, int col, Canvas mazeCanvas) {
    mazeCanvas.drawLine(
        col * mazeSectionLength,
        row * this.mazeSectionLength,
        (col + 1) * this.mazeSectionLength,
        row * this.mazeSectionLength,
        mazeBrush);
  }

  /**
   * Drawing a line on the screen representing the bottom wall of some mazeSection in the mazeGrid
   * array.
   *
   * @param row the row in the mazeGrid of where to draw the bottom wall
   * @param col the column in the mazeGrid of where to draw the bottom wall
   * @param mazeCanvas the canvas on which to draw the bottom wall
   */
  private void drawBottomWall(int row, int col, Canvas mazeCanvas) {
    mazeCanvas.drawLine(
        col * this.mazeSectionLength,
        (row + 1) * this.mazeSectionLength,
        (col + 1) * mazeSectionLength,
        (row + 1) * mazeSectionLength,
        mazeBrush);
  }

  /**
   * Drawing a line on the screen representing the left wall of some mazeSection in the mazeGrid
   * array.
   *
   * @param row the row in the mazeGrid of where to draw the left wall
   * @param col the column in the mazeGrid of where to draw the left wall
   * @param mazeCanvas the canvas on which to draw the left wall
   */
  private void drawLeftWall(int row, int col, Canvas mazeCanvas) {
    mazeCanvas.drawLine(
        col * mazeSectionLength,
        row * mazeSectionLength,
        col * mazeSectionLength,
        (row + 1) * mazeSectionLength,
        mazeBrush);
  }

  /**
   * Drawing a line on the screen representing the right wall of some mazeSection in the mazeGrid
   * array.
   *
   * @param row the row in the mazeGrid of where to draw the right wall
   * @param col the column in the mazeGrid of where to draw the right wall
   * @param mazeCanvas the canvas on which to draw the right wall
   */
  private void drawRightWall(int row, int col, Canvas mazeCanvas) {
    mazeCanvas.drawLine(
        (col + 1) * mazeSectionLength,
        row * mazeSectionLength,
        (col + 1) * mazeSectionLength,
        (row + 1) * mazeSectionLength,
        mazeBrush);
  }

  /**
   * Drawing a circle on the screen representing the player's current location in the maze.
   *
   * @param mazeCanvas the canvas on which to draw the circle/player
   * @param margin the margin between the player and the maze walls.
   */
  void drawPlayer(Canvas mazeCanvas, float margin) {
    if (player.hasMoved()) {
      float left = player.getCol() * mazeSectionLength + margin;
      float right = (player.getCol() + 1) * mazeSectionLength - margin;
      float top = player.getRow() * mazeSectionLength + margin;
      float bottom = (player.getRow() + 1) * mazeSectionLength - margin;
      mazeCanvas.drawCircle(
          (left + right) / 2, (top + bottom) / 2, mazeSectionLength / 3, playerPaint);
    } else { // player has already moved at least once
      float firstX = mazeSectionLength / 2;
      float firstY = mazeSectionLength / 2;
      mazeCanvas.drawCircle(firstX, firstY, mazeSectionLength / 3, playerPaint);
    }
  }

  /**
   * Drawing a rectangle on the screen representing the exit point location in the maze.
   *
   * @param mazeCanvas the canvas on which to draw the square/exit location.
   * @param margin the margin between the exitPoint location and the maze walls.
   */
  void drawExitPoint(Canvas mazeCanvas, float margin) {
    float left = exitPoint.getCol() * mazeSectionLength + margin;
    float right = (exitPoint.getCol() + 1) * mazeSectionLength - margin;
    float top = exitPoint.getRow() * mazeSectionLength + margin;
    float bottom = (exitPoint.getRow() + 1) * mazeSectionLength - margin;
    mazeCanvas.drawRect(left, top, right, bottom, exitPointPaint);
  }

  /**
   * Handles the movement of the player by changing the player's position to correspond with the
   * dragging events that are cause by the user's interaction with the MazeView.
   * @param touchX the x coordinate of movement event that was detected.
   * @param touchY the y coordinate of movement event that was detected.
   */
  void handlePlayerMovement(float touchX, float touchY) {
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
    if (!mazeGrid[player.getRow()][player.getCol()].getHasTopWall()) {
      player.setRow(player.getRow() - 1);
    }
  }

  /** Moves the player's current location down which means one row larger in the mazeGrid array. */
  private void movePlayerDown() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasBottomWall()) {
      player.setRow(player.getRow() + 1);
    }
  }

  /** Moves the player's current location left: One column smaller in the mazeGrid array */
  private void movePlayerLeft() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasLeftWall()) {
      player.setCol(player.getCol() - 1);
    }
  }

  /** Moves the player's current location right: One column larger in the mazeGrid array */
  private void movePlayerRight() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasRightWall()) {
      player.setCol(player.getCol() + 1);
    }
  }

  public void setTotalTime(long totalTime) {

  }
}
