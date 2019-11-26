package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.group0578.hpgame.model.SQLiteHelper;

/** This thread defines the process of drawing the Level 2 Maze on the screen. */
public class MazeThread extends Thread {

  /** The surface which manages this thread. */
  private SurfaceHolder surfaceHolder;

  /** Boolean specifying whether this thread is running or not; true if running, else false */
  private boolean running = false;

  /** The canvas on which to draw the maze walls and player */
  public Canvas mazeCanvas;

  /** The MazeView object contains a reference to the surfaceHolder for this thread. */
  private MazeView mazeView;

  /**
   * The username belonging to the user currently logged in and viewing the profile page.
   */
  private String username;

  /**
   * The sql database helper that has methods that can operate on the database.
   */
  private SQLiteHelper sqlHelper;

  /**
   * The representation of the entire maze built by this maze Thread.
   */
  private Maze maze;

  /**
   * Constructing an instance of a MazeThread.
   *
   * @param surfaceHolder the surface creating this thread.
   * @param mazeView the MazeView object responsible for making the surface.
   * @param sqlHelper the database responsible for updating user customization features.
   * @param username the username of the user currently logged in and playing the maze level.
   */
  MazeThread(
          SurfaceHolder surfaceHolder, MazeView mazeView, SQLiteHelper sqlHelper, String username) {
    super();
    this.surfaceHolder = surfaceHolder;
    this.mazeView = mazeView;
    this.sqlHelper = sqlHelper;
    this.username = username;
    MazeBuilder mazeBuilder = new MazeBuilder();
    this.maze =
            mazeBuilder.build(
                    sqlHelper.findDifficulty(username),
                    sqlHelper.findColourScheme(username),
                    sqlHelper.findCharacter(username));
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
    MazePainter mazePainter = new MazePainter(maze);

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
      mazePainter.drawMaze(mazeCanvas);
      surfaceHolder.unlockCanvasAndPost(mazeCanvas); // canvas updated with drawn changes

      try {
        sleep(150);
      } catch (Exception e) {
        e.printStackTrace();
      }

      // Checking if player has reached exit point in the maze.
      checkExitReached();
      if (!running) {
        setTotalTime(start);
      }
    }
  }

  /**
   * Stores the total time taken to complete the maze.
   *
   * @param start the time at which the level was started.
   */
  private void setTotalTime(long start) {
    long end = System.currentTimeMillis();
    float totalSec = (end - start) / 1000F;
    System.out.println(totalSec + " seconds");
    this.sqlHelper.setLevelTwoTime(this.username, totalSec);
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
    if (this.maze.getPlayer().getRow() == this.maze.getExitPoint().getRow()
            && this.maze.getPlayer().getCol() == this.maze.getExitPoint().getCol()) {
      running = false; // player and exitPoint locations match so user has won.
    }
  }

  /**
   * Getter for the maze associated with this maze thread
   *
   * @return Maze object
   */
  public Maze getMaze() {
    return maze;
  }

  /**
   * Getter for the sqlHelper database associated with this maze thread
   *
   * @return SQLiteHelper object
   */
  public SQLiteHelper getSqlHelper() {
    return sqlHelper;
  }

  /**
   * Getter for the username of the user currently logged in and playing the maze level.
   *
   * @return String username
   */
  public String getUsername() {
    return username;
  }
}
