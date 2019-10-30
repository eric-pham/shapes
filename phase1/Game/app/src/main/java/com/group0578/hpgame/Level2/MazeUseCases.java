package com.group0578.hpgame.Level2;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

public class MazeUseCases {

    /** The number of rows in the maze. */
    private static final int ROWS = 4;
    /** The number of columns in the maze. */
    private static final int COLS = 4;

    public MazeUseCases() { }

    public MazeSection[][] buildMazeGrid() {
        MazeSection[][] mazeGrid = new MazeSection[ROWS][COLS];
        return createMazeGrid(mazeGrid);
    }

    private MazeSection[][] createMazeGrid(MazeSection[][] mazeGrid) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                mazeGrid[row][col] = new MazeSection(row, col);
            }
        }
        return mazeGrid;
    }



}
