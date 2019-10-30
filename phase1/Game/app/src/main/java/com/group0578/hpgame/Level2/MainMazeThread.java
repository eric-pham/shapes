package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * MainMazeThread manages new thread and updates to the MazeView screen.
 */
public class MainMazeThread extends Thread {

    /**
     * The location of where the Maze is drawn.
     */
    private MazeView mazeView;

    /**
     * The mazeCanvas container.
     */
    private SurfaceHolder surfaceHolder;

    /**
     * Specifies whether the thread is running or not.
     */
    private boolean isRunning;

    /**
     * The mazeCanvas on which to draw the Maze.
     */
    private static Canvas mazeCanvas;

    /**
     * Construct the thread
     *
     * @param surfaceHolder the mazeCanvas container.
     * @param mazeView where the maze and maze attributes (character, final destination) are drawn.
     */
    public MainMazeThread(SurfaceHolder surfaceHolder, MazeView mazeView) {
        this.surfaceHolder = surfaceHolder;
        this.mazeView = mazeView;
    }

    /**
     * Setter for isRunning attribute
     * @param isRunning is true if this thread is currently running.
     */

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        mazeCanvas = this.surfaceHolder.lockCanvas();
        while (isRunning) {
            try {
            synchronized (surfaceHolder) {

                mazeView.draw(mazeCanvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mazeCanvas != null) {
                try {
                    String TAG = "MainMazeThread.run";

                    surfaceHolder.unlockCanvasAndPost(mazeCanvas);
                    Log.i(TAG, "Trying to post mazeCanvas.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        }



    }




}
