package com.group0578.hpgame.level2;

import android.graphics.Canvas;
import android.util.Log;

import com.group0578.hpgame.model.Timer;

/**
 * The class responsible for drawing the maze on the maze canvas during level 2.
 */
class MazePainter {

    /**
     * The maze drawn by this MazePainter object
     */
    private Maze maze;

    /**
     * Construct a new instance of a MazePainter object for drawing the Maze.
     *
     * @param maze the Maze object to be drawn
     */
    MazePainter(Maze maze) {
        this.maze = maze;
    }

    /**
     * Draws the maze depending on the colour scheme.
     *
     * @param mazeCanvas the canvas on which to draw the maze.
     * @param timer the object that keeps track of the number of seconds spent on this level.
     */
    void drawMaze(Canvas mazeCanvas, Timer timer) {

        // Setting mazeCanvas background colours based on colour scheme selected by user
        if (this.maze.getColourScheme().equalsIgnoreCase("Light")) {
            mazeCanvas.drawARGB(255, 204, 212, 255);
        } else {
            mazeCanvas.drawARGB(255, 100, 30, 250);
        }

        // Writing the legend on the screen using drawText() method
        mazeCanvas.drawText("Player = Smaller", 100, maze.getScreenHeight() - 100, this.maze.getTextBrush());
        mazeCanvas.drawText("End Point = Square", 550, maze.getScreenHeight() - 100, this.maze.getTextBrush());
        mazeCanvas.drawText(timer.getSecondsPassedString(), 100, 100, this.maze.getTextBrush());
        mazeCanvas.drawText("Lives: " + this.maze.getPlayer().getLives(), 100, 150, this.maze.getTextBrush());

        // Drawing the maze on the mazeCanvas object
        drawMazeWalls(mazeCanvas);
        drawPlayer(mazeCanvas, this.maze.getMazeSectionLength() / 10, this.maze.getCharacter());
        drawExitPoint(mazeCanvas, this.maze.getMazeSectionLength() / 10);
    }

    /**
     * Drawing the walls of the maze based on if each mazeSection in the mazeGrid has a top, bottom,
     * left or right wall
     *
     * @param mazeCanvas the canvas on which to draw the walls of the maze.
     */
    private void drawMazeWalls(Canvas mazeCanvas) {
        String TAG = "MazeView.drawMazeWalls";
        Log.i(TAG, "Trying to draw walls");

        // translating canvas makes drawing walls easier
        // moves 'origin' of where objects are drawn on the screen to top-left corner of the maze
        mazeCanvas.translate(this.maze.getHorizontalMargin(), this.maze.getVerticalMargin());

        // Drawing the walls for each of the mazeSections inside mazeGrid array
        for (int row = 0; row < this.maze.getMazeGrid().length; row++) {
            for (int col = 0; col < this.maze.getMazeGrid()[0].length; col++) {
                // if there is a top wall
                if (this.maze.getMazeGrid()[row][col].getHasTopWall()) {
                    drawTopWall(row, col, mazeCanvas);
                }
                // if there is a bottom wall
                if (this.maze.getMazeGrid()[row][col].getHasBottomWall()) {
                    drawBottomWall(row, col, mazeCanvas);
                }
                // if there is a left wall
                if (this.maze.getMazeGrid()[row][col].getHasLeftWall()) {
                    drawLeftWall(row, col, mazeCanvas);
                }
                // if there is a right wall
                if (this.maze.getMazeGrid()[row][col].getHasRightWall()) {
                    drawRightWall(row, col, mazeCanvas);
                }
            }
        }
    }

    /**
     * Drawing a line on the screen representing the top wall of some mazeSection in the mazeGrid
     * array.
     *
     * @param row        the row in the mazeGrid of where to draw the top wall
     * @param col        the column in the mazeGrid of where to draw the top wall
     * @param mazeCanvas the canvas on which to draw the top wall
     */
    private void drawTopWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(
                col * this.maze.getMazeSectionLength(),
                row * this.maze.getMazeSectionLength(),
                (col + 1) * this.maze.getMazeSectionLength(),
                row * this.maze.getMazeSectionLength(),
                this.maze.getMazeBrush());
    }

    /**
     * Drawing a line on the screen representing the bottom wall of some mazeSection in the mazeGrid
     * array.
     *
     * @param row        the row in the mazeGrid of where to draw the bottom wall
     * @param col        the column in the mazeGrid of where to draw the bottom wall
     * @param mazeCanvas the canvas on which to draw the bottom wall
     */
    private void drawBottomWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(
                col * this.maze.getMazeSectionLength(),
                (row + 1) * this.maze.getMazeSectionLength(),
                (col + 1) * this.maze.getMazeSectionLength(),
                (row + 1) * this.maze.getMazeSectionLength(),
                this.maze.getMazeBrush());
    }

    /**
     * Drawing a line on the screen representing the left wall of some mazeSection in the mazeGrid
     * array.
     *
     * @param row        the row in the mazeGrid of where to draw the left wall
     * @param col        the column in the mazeGrid of where to draw the left wall
     * @param mazeCanvas the canvas on which to draw the left wall
     */
    private void drawLeftWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(
                col * this.maze.getMazeSectionLength(),
                row * this.maze.getMazeSectionLength(),
                col * this.maze.getMazeSectionLength(),
                (row + 1) * this.maze.getMazeSectionLength(),
                this.maze.getMazeBrush());
    }

    /**
     * Drawing a line on the screen representing the right wall of some mazeSection in the mazeGrid
     * array.
     *
     * @param row        the row in the mazeGrid of where to draw the right wall
     * @param col        the column in the mazeGrid of where to draw the right wall
     * @param mazeCanvas the canvas on which to draw the right wall
     */
    private void drawRightWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(
                (col + 1) * this.maze.getMazeSectionLength(),
                row * this.maze.getMazeSectionLength(),
                (col + 1) * this.maze.getMazeSectionLength(),
                (row + 1) * this.maze.getMazeSectionLength(),
                this.maze.getMazeBrush());
    }

    /**
     * Checks the character shape associated with the logged in user and then calls the corresponding
     * method to draw the player.
     *
     * @param mazeCanvas the canvas on which to draw the circle/player.
     * @param margin     the margin between the player and the maze walls.
     * @param character  the string representing the player sprite's shape.
     */
    private void drawPlayer(Canvas mazeCanvas, float margin, String character) {
        if (character.equalsIgnoreCase("Circle")) {
            drawCirclePlayer(mazeCanvas, margin);
        } else { // player shape is square
            drawSquarePlayer(mazeCanvas, margin);
        }
    }

    /**
     * Drawing a square on the screen representing the player's current location in the maze.
     *
     * @param mazeCanvas the canvas on which to draw the circle/player.
     * @param margin     the margin between the player and the maze walls.
     */
    private void drawSquarePlayer(Canvas mazeCanvas, float margin) {
        Player player = this.maze.getPlayer();
        float left = player.getCol() * this.maze.getMazeSectionLength() + 3 * margin;
        float right = (player.getCol() + 1) * this.maze.getMazeSectionLength() - 3 * margin;
        float top = player.getRow() * this.maze.getMazeSectionLength() + 3 * margin;
        float bottom = (player.getRow() + 1) * this.maze.getMazeSectionLength() - 3 * margin;
        mazeCanvas.drawRect(left, top, right, bottom, this.maze.getPlayerPaint());
        // add Player paint as instance attribute to player class? (maybe)
    }

    /**
     * Drawing a circle on the screen representing the player's current location in the maze.
     *
     * @param mazeCanvas the canvas on which to draw the circle/player.
     * @param margin     the margin between the player and the maze walls.
     */
    private void drawCirclePlayer(Canvas mazeCanvas, float margin) {
        Player player = this.maze.getPlayer();
        float mazeSectionLength = this.maze.getMazeSectionLength();
        if (player.hasMoved()) {
            float left = player.getCol() * mazeSectionLength + 3 * margin;
            float right = (player.getCol() + 1) * mazeSectionLength - 3 * margin;
            float top = player.getRow() * mazeSectionLength + 3 * margin;
            float bottom = (player.getRow() + 1) * mazeSectionLength - 3 * margin;
            mazeCanvas.drawCircle(
                    (left + right) / 2,
                    (top + bottom) / 2,
                    mazeSectionLength / 5,
                    this.maze.getPlayerPaint());
        } else { // player has not yet moved
            float firstX = mazeSectionLength / 2;
            float firstY = mazeSectionLength / 2;
            mazeCanvas.drawCircle(firstX, firstY, mazeSectionLength / 5, this.maze.getPlayerPaint());
        }
    }

    /**
     * Drawing a rectangle on the screen representing the exit point location in the maze.
     *
     * @param mazeCanvas the canvas on which to draw the square/exit location.
     * @param margin     the margin between the exitPoint location and the maze walls.
     */
    private void drawExitPoint(Canvas mazeCanvas, float margin) {
        MazeSection exitPoint = this.maze.getExitPoint();
        float mazeSectionLength = this.maze.getMazeSectionLength();
        float left = exitPoint.getCol() * mazeSectionLength + margin;
        float right = (exitPoint.getCol() + 1) * mazeSectionLength - margin;
        float top = exitPoint.getRow() * mazeSectionLength + margin;
        float bottom = (exitPoint.getRow() + 1) * mazeSectionLength - margin;
        mazeCanvas.drawRect(left, top, right, bottom, this.maze.getExitPointPaint());
    }
}
