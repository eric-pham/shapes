package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/** This thread defines the process of drawing the Level 2 Maze on the screen. */
public class MazeThread extends Thread {

  /** The surface which manages this thread. */
  private SurfaceHolder surfaceHolder;

  /** Boolean specifying whether this thread is running or not; true if running, else false */
  private boolean running = false;

  /** The canvas on which to draw the maze walls and player */
  public Canvas mazeCanvas;

  /** The MazePresenter responsible for handling user's actions within this thread. */
  private MazePresenter mazePresenter;

  /** The MazeView object contains a reference to the surfaceHolder for this thread. */
  private MazeView mazeView;

  /**
   * Constructing an instance of a MazeThread.
   *
   * @param surfaceHolder the surface creating this thread.
   * @param mazeView the MazeView object responsible for making the surface.
   * @param mazePresenter handles user's interactions with this mazeThread.
   */
  MazeThread(SurfaceHolder surfaceHolder, MazeView mazeView, MazePresenter mazePresenter) {
    super();
    this.surfaceHolder = surfaceHolder;
    this.mazePresenter = mazePresenter;
    this.mazeView = mazeView;
  }

  /**
   * Setter for updating the running instance attribute.
   *
   * @param running true if this thread is running, else false
   */
  void setRunning(boolean running) {
    this.running = running;
    String TAG = "MazeThread.setRunning";
    Log.e(TAG, "test");
  }

  /** Method called when MazeThread.start() is executed. */
  @Override
  public void run() {
    // Creating mazeBuilder to build player and exit point attributes.
    String colourScheme = mazePresenter.getSQLHelper().findColourScheme(mazePresenter.getUsername());
    MazeBuilder mazeBuilder = new MazeBuilder();
    mazeBuilder.build(this.mazePresenter, colourScheme);

    String TAG = "MazeThread.run";
    Log.e(TAG, "test");

    long start = System.currentTimeMillis();

    // Only draw the Maze when the thread is currently running
    while (running) {
      if (!mazeView.getSurfaceHolder().getSurface().isValid()) {
        Log.e(TAG, "test to see if it skips over the while loop iterations");
        continue;
      }
      Log.e(TAG, "test to see if it now it executes the while loop stuff");
      System.out.println("running");

      // Initializing the canvas on which to draw the maze
      mazeCanvas = mazeView.getSurfaceHolder().lockCanvas();

      drawMaze(colourScheme);

      surfaceHolder.unlockCanvasAndPost(mazeCanvas); // canvas updated with drawn changes

      try {
        sleep(150);
      } catch (Exception e) {
        e.printStackTrace();
      }

      // Checking if player has reached exit point in the maze.
      checkExitReached();
      if (!running) {
        storeTotalTime(start);
      }

    }
  }

  private void storeTotalTime(long start) {
    long end = System.currentTimeMillis();
    mazePresenter.setTotalTime(end - start);
  }

  /**
   * Returns whether the run method is running or not.
   *
   * @return running: a boolean.
   */
  boolean isRunning() {
    return running;
  }

  /** Checks if the player's location matches the exitPoint's location. */
  private void checkExitReached() {
    if (mazePresenter.getPlayer().getRow() == mazePresenter.getExitPoint().getRow()
            && mazePresenter.getPlayer().getCol() == mazePresenter.getExitPoint().getCol()) {
      running = false; // player and exitPoint locations match so user has won.
    }
  }

  /**
   * Draws the maze depending on the colour scheme.
   *
   * @param colourScheme the colour scheme of the game, either 'Light' or 'Dark'.
   */
  private void drawMaze(String colourScheme) {

    if (colourScheme.equalsIgnoreCase("Light")) {
      this.mazeCanvas.drawARGB(255, 204, 212, 255);
    } else {
      this.mazeCanvas.drawARGB(255, 100, 30, 250);
    }

    // Writing the legend on the screen using drawText() method
    mazeCanvas.drawText("Player = Circle", 100, 100, mazePresenter.getTextBrush());
    mazeCanvas.drawText("End Point = Square", 550, 100, mazePresenter.getTextBrush());

    // MazePresenter object handles drawing the maze, player, and exitPoint.
    mazePresenter.drawMazeWalls(mazeCanvas);
    mazePresenter.drawPlayer(mazeCanvas, mazePresenter.getMazeSectionLength() / 10);
    mazePresenter.drawExitPoint(mazeCanvas, mazePresenter.getMazeSectionLength() / 10);
  }
}
