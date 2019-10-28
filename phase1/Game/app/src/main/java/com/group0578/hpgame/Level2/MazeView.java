package com.group0578.hpgame.Level2;

import android.content.Context;
import android.view.SurfaceView;

public class MazeView extends SurfaceView implements Maze.View {

    private MazePresenter mazePresenter;

    public MazeView(Context context) {
        super(context);
        mazePresenter = new MazePresenter(this);
    }



}
