package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.Timer;

/**
 * This thread defines the process of drawing the Level 2 Maze on the screen.
 */
class MazeThread extends Thread {

    /**
     * The surface which manages this thread.
     */
    private SurfaceHolder surfaceHolder;

    /**
     * Boolean specifying whether this thread is running or not; true if running, else false
     */
    private boolean running = false;

    /**
     * The MazeView object contains a reference to the surfaceHolder for this thread.
     */
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
     * Whether the game has been won.
     */
    private boolean gameWon;

    Timer totalTimer = new Timer();

    /**
     * Constructing an instance of a MazeThread.
     *
     * @param surfaceHolder the surface creating this thread.
     * @param mazeView      the MazeView object responsible for making the surface.
     * @param sqlHelper     the database responsible for updating user customization features.
     * @param username      the username of the user currently logged in and playing the maze level.
     */
    MazeThread(
            SurfaceHolder surfaceHolder, MazeView mazeView, SQLiteHelper sqlHelper, String username) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.mazeView = mazeView;
        this.sqlHelper = sqlHelper;
        this.username = username;
        MazeBuilder mazeBuilder = new MazeBuilder();
        this.maze = mazeBuilder.build(
                sqlHelper.findDifficulty(username), sqlHelper.findColourScheme(username),
                sqlHelper.findCharacter(username), sqlHelper.findLives(username));
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

    /**
     * Method called when MazeThread.start() is executed.
     */
    @Override
    public void run() {
//        Timer totalTimer = new Timer(), resetTimer = new Timer();
        Timer resetTimer = new Timer();
        MazePainter mazePainter = new MazePainter(maze);

        String TAG = "MazeThread.run";
        Log.e(TAG, "test");

        this.totalTimer.start();
        resetTimer.start();

        // Only draw the Maze when the thread is currently running
        while (running) {
            if (!mazeView.getSurfaceHolder().getSurface().isValid()) {
                Log.e(TAG, "test to see if it skips over the while loop iterations");
                continue;
            }
            Log.e(TAG, "test to see if it now it executes the while loop stuff");
            System.out.println("running");

            // Initializing the canvas on which to draw the maze
            Canvas mazeCanvas = mazeView.getSurfaceHolder().lockCanvas();
            mazePainter.drawMaze(mazeCanvas, this.totalTimer);
            surfaceHolder.unlockCanvasAndPost(mazeCanvas); // canvas updated with drawn changes

            try {
                sleep(150);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // checks if the player still has lives
            if (!playerStillAlive()) {
                running = false;
                this.gameWon = false;
//                this.sqlHelper.setLives(username, 0);
                break;

            }

            // Checking if player has exceeded the time limit for completing the maze.
            if (timeLimitExceeded(resetTimer)) {
                playerLosesLife();  // may cause running = false
                resetTimer.start(); // resetTimer must start again.
            }

            // Checking if player has reached maze exit point if thread is still running.
            if (running) {
                checkExitReached();

            }

            // Only store the user's time if the user has won the game.
            if (!running && this.gameWon) { // move this to the stop game method? make timer an instance variable?
                setTotalTime();
            }
        }
    }

    /**
     * Returns
     *
     * @return a boolean: true if the player still has lives, false if the lives are zero
     */
    private boolean playerStillAlive() {
        return this.maze.getPlayer().getLives() > 0;
    }

    /**
     * Checks how long the user has been playing the maze level for and returns true if the time
     * limit for completing the maze is exceeded.
     *
     * @param timer Timer object representing the time in seconds since this thread began running.
     * @return boolean true if maze time limit completion is exceeded, else false.
     */
    private boolean timeLimitExceeded(Timer timer) {
        System.out.println("Method MazeThread.timeLimitExceeded reached");
        String difficulty = sqlHelper.findDifficulty(username);
        double seconds = timer.getSecondsPassed();
        // Time limit to complete the maze is 5 sec. for easy level difficulty, 10 sec. for hard
        if (difficulty.equalsIgnoreCase("Hard") && seconds > 15.0) {
            return true;
        } else if (difficulty.equalsIgnoreCase("Easy") && seconds > 10.0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deducts a life for every occasion where the user hasn't completed the maze in the required
     * time limit, and the user loses the game if they lose all their lives. This method is only
     * ever called when the lives are greater than 0.
     */
    private void playerLosesLife() {
        System.out.println("Method MazeThread.playerLosesLife reached");
        int livesLeft = this.maze.getPlayer().getLives();
            this.maze.getPlayer().setLives(livesLeft - 1); // Deduct a life
            this.maze.getPlayer().setRow(0);    // player goes back to start
            this.maze.getPlayer().setCol(0);    // player goes back to start
    }

    /**
     * Checks if the player's location matches the exitPoint's location.
     */
    private void checkExitReached() {
        System.out.println("Method MazeThread.checkExitReached reached");
        if (this.maze.getPlayer().getRow() == this.maze.getExitPoint().getRow()
                && this.maze.getPlayer().getCol() == this.maze.getExitPoint().getCol()) {
            this.gameWon = true;
            running = false; // player and exitPoint locations match so user has won.
        }
    }

    /**
     * Stores the total time taken to complete the maze.
     *
     //     * @param timer the timer object representing how long this thread has been running
     */
    private void setTotalTime() {
        System.out.println("Method MazeThread.setTotalTime reached");
        double totalTime = this.totalTimer.getSecondsPassed();
        System.out.println(totalTime + " seconds");
        this.sqlHelper.setLevelTwoTime(this.username, totalTime);
    }

    void updateDatabase() {
        this.sqlHelper.setLives(this.username, this.maze.getPlayer().getLives());
        setTotalTime();
    }

    /**
     * Returns whether the run method is running or not.
     *
     * @return running: a boolean.
     */
    boolean isRunning() {
        return running;
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

    /**
     * Getter for whether this player has won the maze level or not.
     *
     * @return true if this player has completed the maze level successfully, else false.
     */
    boolean isGameWon() {
        return gameWon;
    }

    /**
     * Setter for whether this player has won the maze level or not.
     *
     * @param gameWon true if this player has completed the maze level successfully, else false
     */
    void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }
}
