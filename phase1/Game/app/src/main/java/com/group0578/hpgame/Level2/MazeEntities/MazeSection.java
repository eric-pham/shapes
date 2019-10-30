package com.group0578.hpgame.Level2.MazeEntities;

public class MazeSection {

    /* These variables determine whether a MazeSection has a top, left, right and bottom wall.
     * Set to true if it has a wall; set to false if it doesn't.
     */
    private boolean hasTopWall = true;
    private boolean hasBottomWall = true;
    private boolean hasLeftWall = true;
    private boolean hasRightWall = true;

    // this MazeSection's grid location, with the top left corner of the maze as the origin (0, 0)
    private int row, col;

    private boolean modified = false;

    /**
     * Constructor for a MazeSection, which is a rectangle piece of the maze.
     * @param row the number which represents which row of the maze this MazeSection is in.
     * @param col the number which represents which column of the maze this MazeSection is in.
     */
    public MazeSection(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets the current value of hasTopWall
     * @return hasTopWall: true if this MazeSection has a top wall, false if it doesn't.
     */
    public boolean getHasTopWall() {
        return hasTopWall;
    }

    /**
     * Sets the value of hasTopWall.
     * @param hasTopWall boolean set to true if this MazeSection has a bottom wall, false if it doesn't.
     */
    public void setHasTopWall(boolean hasTopWall) {
        this.hasTopWall = hasTopWall;
    }

    /**
     * Gets the current value of hasBottomWall.
     * @return hasBottomWall: boolean true if this MazeSection has a bottom wall, false if it doesn't.
     */
    public boolean getHasBottomWall() {
        return hasBottomWall;
    }

    /**
     * Sets the value of hasBottomWall.
     * @param hasBottomWall true if this MazeSection has a bottom wall, false if it doesn't.
     */
    public void setHasBottomWall(boolean hasBottomWall) {
        this.hasBottomWall = hasBottomWall;
    }

    /**
     * Gets the current value of hasLeftWall.
     * @return hasLeftWall: boolean true if this MazeSection has a left wall, false if it doesn't.
     */
    public boolean getHasLeftWall() {
        return hasLeftWall;
    }

    /**
     * Sets the value of hasLeftWall.
     * @param hasLeftWall true if this MazeSection has a left wall, false if it doesn't.
     */
    public void setHasLeftWall(boolean hasLeftWall) {
        this.hasLeftWall = hasLeftWall;
    }

    /**
     * Gets the current value of hasRightWall.
     * @return hasRightWall: boolean true if this MazeSection has a right wall, false if it doesn't.
     */
    public boolean getHasRightWall() {
        return hasRightWall;
    }

    /**
     * Sets the value of hasRightWall.
     * @param hasRightWall true if this MazeSection has a right wall, false if it doesn't.
     */
    public void setHasRightWall(boolean hasRightWall) {
        this.hasRightWall = hasRightWall;
    }

    /**
     * Gets the value of row.
     * @return row: an integer that represents the row of this MazeSection in the maze.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the value of row.
     * @param row an integer that represents the row of this MazeSection in the maze.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the value of col.
     * @return col: an integer that represents the column of this MazeSection in the maze.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the value of col.
     * @param col the column of this MazeSection in the maze.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Gets the current value of modified.
     * @return modified: true if this MazeSection has been modified during the creation of the maze
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Sets the value of modified.
     * @param modified true if it has ever had any of its walls taken down, false otherwise
     */
    public void setModified(boolean modified) {
        this.modified = modified;
    }
}
