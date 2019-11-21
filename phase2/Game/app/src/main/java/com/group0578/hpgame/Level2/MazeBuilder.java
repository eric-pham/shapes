package com.group0578.hpgame.Level2;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

public class MazeBuilder {

  MazeBuilder() {}

  /**
   * The series of instructions to create all the tools and objects needed to draw the maze
   *
   * @param mazePresenter the MazePresenter responsible for drawing the maze
   * @param colourScheme the string representing the colour scheme of the game
   */
  void build(MazePresenter mazePresenter, String colourScheme) {
    // gets the screen height and width
    mazePresenter.setScreenWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
    mazePresenter.setScreenHeight(Resources.getSystem().getDisplayMetrics().heightPixels);

    // Receiving mazeGrid from Presenter
    MazeSection[][] mazeGrid = mazePresenter.buildMazeGrid();
    mazePresenter.setMazeGrid(mazeGrid);

    // Initializing new Player object with coordinates (0,0) in mazeGrid array
    Player player = new Player(0, 0);
    mazePresenter.setPlayer(player);

    // prepares the exit
    setExitLocation(mazePresenter);

    // prepares the brushes for the maze outline and the text
    prepareMazeBrushes(mazePresenter, colourScheme);

    // calculates the dimensions of the margins and the lengths of the walls
    determineMazeDimensions(mazePresenter, mazeGrid);

    // prepares the Paint objects for the player and exit
    makePlayer(mazePresenter, colourScheme);
    makeExitPoint(mazePresenter, colourScheme);
  }

  /**
   * Initializes the exitPoint attribute to last column and last row in the mazeGrid array.
   *
   * @param mazePresenter the MazePresenter responsible for drawing the maze
   */
  private void setExitLocation(MazePresenter mazePresenter) {
    int rows = mazePresenter.getRowColumnAttributes()[0];
    int cols = mazePresenter.getRowColumnAttributes()[1];
    mazePresenter.setExitPoint(mazePresenter.getMazeGrid()[rows - 1][cols - 1]);
  }

  /**
   * Initializes and performs actions on mazeBrush attribute which is used to draw the maze.
   * Initializes and performs actions on textBrush attribute which is used to draw the text.
   *
   * @param mazePresenter the MazePresenter responsible for drawing the maze
   * @param colourScheme the string representing the colour scheme of the game
   */
  private void prepareMazeBrushes(MazePresenter mazePresenter, String colourScheme) {
    int colour;
    if (colourScheme.equalsIgnoreCase("Light")) {
      colour = Color.BLACK;
    } else {
      colour = Color.WHITE;
    }
    // Prepare the mazeBrush object to draw maze on the screen in run() method of the MazeThread
    Paint mazeBrush = new Paint();
    mazeBrush.setColor(colour);
    mazeBrush.setStrokeWidth(3);
    mazePresenter.setMazeBrush(mazeBrush);

    // Prepare the textBrush object to draw text on the screen in run() method of the MazeThread
    Paint textBrush = new Paint();
    textBrush.setColor(colour);
    textBrush.setStrokeWidth(10);
    textBrush.setTextSize(50);
    mazePresenter.setTextBrush(textBrush);
  }

  /**
   * Determines the dimensions for the Maze by deciding:
   *
   * <p>1) How large each mazeSection should be on the screen (setting mazeSectionLength attribute)
   * 2) Vertical and horizontal margins between the maze and edges of the screen
   *
   * @param mazePresenter the MazePresenter responsible for drawing the maze
   * @param mazeGrid the grid array on which the maze appears.
   */
  private void determineMazeDimensions(MazePresenter mazePresenter, MazeSection[][] mazeGrid) {

    String TAG = "MazeBuilder.determineMazeDimensions";

    int rows = mazeGrid.length;
    int cols = mazeGrid[0].length;

    int screenHeight = mazePresenter.getScreenHeight();
    int screenWidth = mazePresenter.getScreenWidth();
    // Phone screen rotated sideways or landscape mode
    if (screenHeight <= screenWidth) {
      mazePresenter.setMazeSectionLength(screenHeight / (rows + 1));
    } else { // phone screen vertically oriented or portrait mode
      mazePresenter.setMazeSectionLength(screenWidth / (cols + 1));
    }

    float mazeWidth = cols * mazePresenter.getMazeSectionLength();
    float mazeHeight = rows * mazePresenter.getMazeSectionLength();

    // Setting margins based on mazeWidth and mazeHeight attributes
    mazePresenter.setVerticalMargin((screenHeight - mazeHeight) / 2);
    mazePresenter.setHorizontalMargin((screenWidth - mazeWidth) / 2);

    Log.e(TAG, "screen height, screen width: " + screenHeight + ", " + screenWidth);
  }

  /**
   * Initializes a paint object used to draw the player.
   *
   * @param mazePresenter the MazePresenter responsible for drawing the maze
   * @param colourScheme the string representing the colour scheme of the game
   */
  private void makePlayer(MazePresenter mazePresenter, String colourScheme) {
    int colour;
    if (colourScheme.equalsIgnoreCase("Light")) {
      colour = Color.argb(255, 0, 191, 230);
    } else {
      colour = Color.RED;
    }

    Paint playerPaint = new Paint();
    playerPaint.setColor(colour);
    mazePresenter.setPlayerPaint(playerPaint);
    String TAG = "MazeBuilder.makePlayer";
    Log.e(TAG, "test");
  }

  /**
   * Initializes a paint object used to draw the exit point in the maze.
   *
   * @param mazePresenter the MazePresenter responsible for drawing the maze
   * @param colourScheme the string representing the colour scheme of the game
   */
  private void makeExitPoint(MazePresenter mazePresenter, String colourScheme) {
    int colour;
    if (colourScheme.equalsIgnoreCase("Light")) {
//      colour = Color.argb(255, 230, 190, 170);
      colour = Color.argb(255, 255, 77, 106);
    } else {
      colour = Color.GREEN;
    }

    Paint exitPointPaint = new Paint();
    exitPointPaint.setColor(colour);
    mazePresenter.setExitPointPaint(exitPointPaint);
    String TAG = "MazeBuilder.makeExitPoint";
    Log.e(TAG, "test");
  }
}
