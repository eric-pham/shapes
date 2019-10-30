package com.group0578.hpgame.Level2;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

public class MazePresenter implements Maze.Presenter {

    private Maze.View mazeView;
    private MazeUseCases mazeUseCases;

    public MazePresenter(Maze.View mazeView) {
        this.mazeView = mazeView;
        this.mazeUseCases = new MazeUseCases();
    }

    public MazeSection[][] getMazeGrid() {
        return mazeUseCases.buildMazeGrid();
    }
}
