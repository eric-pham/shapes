package com.group0578.hpgame.Level2;

import android.graphics.Paint;

/**
 * The Maze that will be drawn onto the canvas during level 2.
 */
class Maze {

    /**
     * The width of the screen representing this MazeView.
     */
    private int screenWidth;

    /**
     * The height of the screen representing this MazeView.
     */
    private int screenHeight;

    /**
     * The array representing the maze; structured as a grid where each element is a MazeSection.
     */
    private MazeSection[][] mazeGrid;

    /**
     * The Player object that is currently playing the Maze. Used for keeping track of current
     * location in the Maze.
     */
    private Player player;

    /**
     * The final location in the mazeGrid array the user reaches upon completion of the Maze.
     */
    private MazeSection exitPoint;

    /**
     * The Paint object used to draw the grid lines of the maze on the screen.
     */
    private Paint mazeBrush;

    /**
     * The numerical value for the length of each MazeSection in the mazeGrid array.
     */
    private float mazeSectionLength;

    /**
     * The length between the top of the screen and beginning of the Maze.
     */
    private float verticalMargin;

    /**
     * The length between the sides of the screen and the Maze.
     */
    private float horizontalMargin;

    /**
     * The Paint objects used to draw and distinguish the Player's current location from the exit
     * point.
     */
    private Paint playerPaint, exitPointPaint;

    /**
     * The Paint object used to draw text on the screen -- used for creating the legend.
     */
    private Paint textBrush;

    /**
     * The number of rows in the maze.
     */
    private int rows;
    /**
     * The number of columns in the maze.
     */
    private int cols;

    /**
     * The colour scheme of the maze; always "Light" or "Dark"
     */
    private String colourScheme;

    /**
     * String representing the character selected by the user logged in and playing this maze.
     */
    private String character;


    /**
     * Construct a new instance of Maze
     */
    Maze() {
    }

    /**
     * Getter for rows instance attribute.
     *
     * @return number of rows in the Maze Grid.
     */
    int getRows() {
        return rows;
    }

    /**
     * Getter for cols instance attribute.
     *
     * @return number of columns in the Maze Grid.
     */
    int getCols() {
        return cols;
    }

    /**
     * Setter for rows instance attribute.
     *
     * @param rows number of rows in the Maze Grid.
     */
    void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Setter for cols instance attribute.
     *
     * @param cols number of columns in the Maze Grid.
     */
    void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Gets the paint object that is responsible for the text in the maze game.
     *
     * @return textBrush: a paint object.
     */
    Paint getTextBrush() {
        return textBrush;
    }

    /**
     * Sets the paint object that is responsible for the text in the maze game.
     *
     * @param textBrush a paint object.
     */
    void setTextBrush(Paint textBrush) {
        this.textBrush = textBrush;
    }

    /**
     * Gets the width of the screen.
     *
     * @return screenWidth: an integer representing the width of the screen.
     */
    int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Sets the value of the variable representing the width of the screen.
     *
     * @param screenWidth width of the screen.
     */
    void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * Gets the height of the screen.
     *
     * @return screenHeight: an integer representing the height of the screen.
     */
    int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Sets the value of the variable representing the height of the screen. ]
     *
     * @param screenHeight height of the screen.
     */
    void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * Gets a MazeSection 2D array list representation of the maze.
     *
     * @return mazeGrid: a MazeSection 2D array list representing the maze.
     */
    MazeSection[][] getMazeGrid() {
        return mazeGrid;
    }

    /**
     * Sets the value of the MazeSection 2D array list representing the maze.
     *
     * @param mazeGrid representation of the maze.
     */
    void setMazeGrid(MazeSection[][] mazeGrid) {
        this.mazeGrid = mazeGrid;
    }

    /**
     * Gets the Player object associated with the maze.
     *
     * @return player: a Player object.
     */
    Player getPlayer() {
        return player;
    }

    /**
     * Sets the Player object that will go through the maze.
     *
     * @param player a Player object.
     */
    void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the MazeSection object associated with the exit point of the maze.
     *
     * @return exitPoint: a MazeSection object.
     */
    MazeSection getExitPoint() {
        return exitPoint;
    }

    /**
     * Sets the value of the MazeSection representing the exit.
     *
     * @param exitPoint a MazeSection object.
     */
    void setExitPoint(MazeSection exitPoint) {
        this.exitPoint = exitPoint;
    }

    /**
     * Gets the paint object that is responsible for the maze outline in the maze game.
     *
     * @return mazeBrush: a Paint object.
     */
    Paint getMazeBrush() {
        return mazeBrush;
    }

    /**
     * Sets the Paint object that is responsible for the maze outline in the maze game.
     *
     * @param mazeBrush a Paint object.
     */
    void setMazeBrush(Paint mazeBrush) {
        this.mazeBrush = mazeBrush;
    }

    /**
     * Gets the value of the float that represents the length of the walls of the MazeSection.
     *
     * @return mazeSectionLength: a float.
     */
    float getMazeSectionLength() {
        return mazeSectionLength;
    }

    /**
     * Sets the float variable that represents the length of the walls of the MazeSection.
     *
     * @param mazeSectionLength: a float.
     */
    void setMazeSectionLength(float mazeSectionLength) {
        this.mazeSectionLength = mazeSectionLength;
    }

    /**
     * Sets the float variable that represents the length of the vertical margin between the
     * top/bottom of the screen and the edges of the maze.
     *
     * @param verticalMargin a float.
     */
    void setVerticalMargin(float verticalMargin) {
        this.verticalMargin = verticalMargin;
    }

    /**
     * Sets the float variable that represents the length of the horizontal margin between the
     * sides of the screen and the edges of the maze.
     *
     * @param horizontalMargin a float.
     */
    void setHorizontalMargin(float horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    /**
     * Gets the float variable that represents the length of the vertical margin between the *
     * top/bottom of the screen and the edges of the maze.
     *
     * @return float verticalMargin
     */
    float getVerticalMargin() {
        return verticalMargin;
    }

    /**
     * Gets the float variable that represents the length of the horizontal margin between the * sides
     * of the screen and the edges of the maze.
     *
     * @return float horizontalMargin
     */
    float getHorizontalMargin() {
        return horizontalMargin;
    }

    /**
     * Gets the Paint object that is responsible for the player in the maze game.
     *
     * @return playerPaint: a Paint object.
     */
    Paint getPlayerPaint() {
        return playerPaint;
    }

    /**
     * Sets the paint object that is responsible for the player in the maze game.
     *
     * @param playerPaint a paint object.
     */
    void setPlayerPaint(Paint playerPaint) {
        this.playerPaint = playerPaint;
    }

    /**
     * Gets the Paint object that is responsible for the exit point in the maze game.
     *
     * @return exitPointPaint: a Paint object.
     */
    Paint getExitPointPaint() {
        return exitPointPaint;
    }

    /**
     * Sets the paint object that is responsible for the exit point in the maze game.
     *
     * @param exitPointPaint a paint object.
     */
    void setExitPointPaint(Paint exitPointPaint) {
        this.exitPointPaint = exitPointPaint;
    }

    /**
     * Gets the colour scheme the currently logged in user has selected for the game
     *
     * @return String: "light" or "dark"
     */
    String getColourScheme() {
        return colourScheme;
    }

    /**
     * Sets the colour scheme the currently logged in user has selected for the game
     * @param colourScheme String: "light" or "dark"
     */
    void setColourScheme(String colourScheme) {
        this.colourScheme = colourScheme;
    }

    /**
     * Gets the character shape selected by the user currently logged in and playing the maze level.
     * @return String: "Square" or "Circle"
     */
    public String getCharacter() {
        return character;
    }

    /**
     * Sets the character shape selected by the user currently logged in.
     * @param character String: "Square" or "Circle"
     */
    public void setCharacter(String character) {
        this.character = character;
    }
}
