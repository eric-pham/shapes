package com.group0578.hpgame.Level2;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * MainThread manages new thread and updates to the MazeView screen.
 */
public class MainThread extends Thread {

    /**
     * The location of where the Maze is drawn.
     */
    private MazeView mazeView;

    /**
     * The canvas container.
     */
    private SurfaceHolder surfaceHolder;

    /**
     * Specifies whether the thread is running or not.
     */
    private boolean isRunning;

    /**
     * The canvas on which to draw the Maze.
     */
    public static Canvas canvas;

    /**
     * Construct the thread
     *
     * @param surfaceHolder the canvas container.
     * @param view where the maze and maze attributes (character, final destination) are drawn.
     */
    public MainThread(SurfaceHolder surfaceHolder, MazeView view) {
        this.surfaceHolder = surfaceHolder;
        this.mazeView = view;
    }

    // TODO: Implement the extended method run() from Thread super class
    // Use @Override annotation

    /**
     * Setter for isRunning attribute
     * @param isRunning is true if this thread is currently running.
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }


}
