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
   * @param mazePresenter
   */
  void build(MazePresenter mazePresenter) { // might not need mazeView attribute change later
    mazePresenter.setScreenWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
    mazePresenter.setScreenHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
    // Receiving mazeGrid from Presenter
    MazeSection[][] mazeGrid = mazePresenter.buildMazeGrid();
    mazePresenter.setMazeGrid(mazeGrid);
    // Initializing new Player object with coordinates (0,0) in mazeGrid array
    Player player = new Player(0, 0);
    mazePresenter.setPlayer(player);
    setExitLocation(mazePresenter);
    prepareMazeBrushes(mazePresenter);
    determineMazeDimensions(mazePresenter, mazeGrid);
    makePlayer(mazePresenter);
    makeExitPoint(mazePresenter);
  }

  /**
   * Initializes the exitPoint attribute to last column and last row in the mazeGrid array.
   *
   * @param mazePresenter
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
   * @param mazePresenter
   */
  private void prepareMazeBrushes(MazePresenter mazePresenter) {
    // Prepare the mazeBrush object to draw maze on the screen in run() method of the MazeThread
    Paint mazeBrush = new Paint();
    mazeBrush.setColor(Color.WHITE);
    mazeBrush.setStrokeWidth(3);
    mazePresenter.setMazeBrush(mazeBrush);

    // Prepare the textBrush object to draw text on the screen in run() method of the MazeThread
    Paint textBrush = new Paint();
    textBrush.setColor(Color.WHITE);
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
   * @param mazePresenter
   * @param mazeGrid
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
   * @param mazePresenter
   */
  private void makePlayer(MazePresenter mazePresenter) {
    Paint playerPaint = new Paint();
    playerPaint.setColor(Color.RED);
    mazePresenter.setPlayerPaint(playerPaint);
    String TAG = "MazeBuilder.makePlayer";
    Log.e(TAG, "test");
  }

  /**
   * Initializes a paint object used to draw the exit point in the maze.
   *
   * @param mazePresenter
   */
  private void makeExitPoint(MazePresenter mazePresenter) {
    Paint exitPointPaint = new Paint();
    exitPointPaint.setColor(Color.GREEN);
    mazePresenter.setExitPointPaint(exitPointPaint);
    String TAG = "MazeBuilder.makeExitPoint";
    Log.e(TAG, "test");
  }
}
