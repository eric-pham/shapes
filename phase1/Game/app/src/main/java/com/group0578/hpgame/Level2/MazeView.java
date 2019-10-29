package com.group0578.hpgame.Level2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.reflect.Array;

public class MazeView extends SurfaceView implements SurfaceHolder.Callback, Maze.View {

    private MazePresenter mazePresenter;
    private SurfaceHolder surfaceHolder = null;

    // the array representing the maze; is is structured as a grid where each element is a MazeSection.
    private Array[][] mazeGrid;
    // the number of rows in the maze
    private static final int ROWS = 7;
    // the number of columns in the maze
    private static final int COLS = 4;


    public MazeView(Context context) {
        super(context);
        setFocusable(true);
        if(surfaceHolder == null) {
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }
        mazePresenter = new MazePresenter(this);

        this.setBackgroundColor(Color.BLACK);
    }

    /**
     * This is called immediately after the surface is first created.
     * Implementations of this should start up whatever rendering code
     * they desire.  Note that only one thread can ever draw into
     * a {@link MazeView}, so you should not draw into the Surface here
     * if your normal rendering will be in another thread.
     *
     * @param surfaceHolder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    /**
     * This is called immediately after any structural changes (format or
     * size) have been made to the surface.  You should at this point update
     * the imagery in the surface.  This method is always called at least
     * once, after {@link #surfaceCreated}.
     *
     * @param surfaceHolder The SurfaceHolder whose surface has changed.
     * @param format The new PixelFormat of the surface.
     * @param width  The new width of the surface.
     * @param height The new height of the surface.
     */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    /**
     * This is called immediately before a surface is being destroyed. After
     * returning from this call, you should no longer try to access this
     * surface.  If you have a rendering thread that directly accesses
     * the surface, you must ensure that thread is no longer touching the
     * Surface before returning from this function.
     *
     * @param surfaceHolder The SurfaceHolder whose surface is being destroyed.
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
