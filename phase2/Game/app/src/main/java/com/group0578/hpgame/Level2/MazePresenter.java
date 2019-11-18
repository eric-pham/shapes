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

  /**
   * The Paint objects used to draw and distinguish the Player's current location from the exit
   * point.
   */
  private Paint playerPaint, exitPointPaint;

  /** The Paint object used to draw text on the screen -- used for creating the legend. */
  private Paint textBrush;

  /** The mazeView interface reference attribute. */
  private MazeView mazeView;

  /**
   * Presenter accesses the UseCases to manipulate maze entities/data such as MazeSection to build
   * the MazeGrid.
   */
  private MazeUseCases mazeUseCases;

  private boolean gameWon;


  /**
   * Construct a new MazePresenter object. (called by MazeView class)
   *
   * @param mazeView the MazeView accessing this Presenter object.
   */
  public MazePresenter(MazeView mazeView) {
    this.mazeView = mazeView;
    this.mazeUseCases = new MazeUseCases();
    this.gameWon = false;
  }

  public Paint getTextBrush() {
    return textBrush;
  }

  public void setTextBrush(Paint textBrush) {
    this.textBrush = textBrush;
  }

  public int getScreenWidth() {
    return screenWidth;
  }

  public void setScreenWidth(int screenWidth) {
    this.screenWidth = screenWidth;
  }

  public int getScreenHeight() {
    return screenHeight;
  }

  public void setScreenHeight(int screenHeight) {
    this.screenHeight = screenHeight;
  }

  public MazeSection[][] getMazeGrid() {
    return mazeGrid;
  }

  public void setMazeGrid(MazeSection[][] mazeGrid) {
    this.mazeGrid = mazeGrid;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public MazeSection getExitPoint() {
    return exitPoint;
  }

  public void setExitPoint(MazeSection exitPoint) {
    this.exitPoint = exitPoint;
  }

  public Paint getMazeBrush() {
    return mazeBrush;
  }

  public void setMazeBrush(Paint mazeBrush) {
    this.mazeBrush = mazeBrush;
  }

  public float getMazeSectionLength() {
    return mazeSectionLength;
  }

  public void setMazeSectionLength(float mazeSectionLength) {
    this.mazeSectionLength = mazeSectionLength;
  }

  public float getVerticalMargin() {
    return verticalMargin;
  }

  public void setVerticalMargin(float verticalMargin) {
    this.verticalMargin = verticalMargin;
  }

  public float getHorizontalMargin() {
    return horizontalMargin;
  }

  public void setHorizontalMargin(float horizontalMargin) {
    this.horizontalMargin = horizontalMargin;
  }

  public Paint getPlayerPaint() {
    return playerPaint;
  }

  public void setPlayerPaint(Paint playerPaint) {
    this.playerPaint = playerPaint;
  }

  public Paint getExitPointPaint() {
    return exitPointPaint;
  }

  public void setExitPointPaint(Paint exitPointPaint) {
    this.exitPointPaint = exitPointPaint;
  }

  public boolean isGameWon() {
    return gameWon;
  }

  public void setGameWon(boolean gameWon) {
    this.gameWon = gameWon;
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
        if (this.mazeGrid[row][col].getHasTopWall()) {
          drawTopWall(row, col, mazeCanvas);
        }
        if (this.mazeGrid[row][col].getHasBottomWall()) {
          drawBottomWall(row, col, mazeCanvas);
        }
        if (this.mazeGrid[row][col].getHasLeftWall()) {
          drawLeftWall(row, col, mazeCanvas);
        }
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

  public void handlePlayerMovement(float touchX, float touchY) {
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

//    checkExitReached();
  }

//  private void checkExitReached() {
//    if (player.getRow() == exitPoint.getRow() && player.getCol() == exitPoint.getCol()) {
//      gameWon = true;
//    }

  /** Moves the player's current location up which means one row higher in the mazeGrid array. */
  void movePlayerUp() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasTopWall()) {
      player.setRow(player.getRow() - 1);
    }
  }

  /** Moves the player's current location down which means one row larger in the mazeGrid array. */
  void movePlayerDown() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasBottomWall()) {
      player.setRow(player.getRow() + 1);
    }
  }

  /** Moves the player's current location left: One column smaller in the mazeGrid array */
  void movePlayerLeft() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasLeftWall()) {
      player.setCol(player.getCol() - 1);
    }
  }

  /** Moves the player's current location right: One column larger in the mazeGrid array */
  void movePlayerRight() {
    if (!mazeGrid[player.getRow()][player.getCol()].getHasRightWall()) {
      player.setCol(player.getCol() + 1);
    }
  }

  /**
   * Calls method in mazeUseCases which makes the mazeGrid for the maze.
   *
   * @return 2-dimensional array of MazeSection objects holding the entire mazeGrid
   */
  public MazeSection[][] buildMazeGrid() {
    return mazeUseCases.buildMazeGrid();
  }

  /**
   * Gets the number of the rows and columns in the mazeGrid.
   *
   * @return 2 element integer array representing number of rows, columns in the mazeGrid array.
   */
  public int[] getRowColumnAttributes() {
    return new int[] {mazeUseCases.getROWS(), mazeUseCases.getCOLS()};
  }
}
