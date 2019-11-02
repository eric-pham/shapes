package com.group0578.hpgame.Level2;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

/**
 * The MazePresenter is responsible for implementing actions the user performs on the screen.
 */
public class MazePresenter implements Maze.Presenter {

    /**
     * The mazeView interface reference attribute.
     */
    private Maze.View mazeView;

    /**
     * Presenter accesses the UseCases to manipulate maze entities/data such as MazeSection
     * to build the MazeGrid.
     */
    private MazeUseCases mazeUseCases;

    /**
     * Construct a new MazePresenter object. (called by MazeView class)
     * @param mazeView the MazeView accessing this Presenter object.
     */
    public MazePresenter(Maze.View mazeView) {
        this.mazeView = mazeView;
        this.mazeUseCases = new MazeUseCases();
    }

    /**
     * Calls method in mazeUseCases which makes the mazeGrid for the maze.
     * @return 2-dimensional array of MazeSection objects holding the entire mazeGrid
     */
    public MazeSection[][] getMazeGrid() {
        return mazeUseCases.buildMazeGrid();
    }

    /**
     * Gets the number of the rows and columns in the mazeGrid.
     * @return 2 element integer array representing number of rows, columns in the mazeGrid array.
     */
    public int[] getRowColumnAttributes() {
        return new int[]{mazeUseCases.getROWS(), mazeUseCases.getCOLS()};
    }
}
