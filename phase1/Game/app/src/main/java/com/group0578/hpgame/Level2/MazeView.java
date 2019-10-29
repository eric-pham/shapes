package com.group0578.hpgame.Level2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * The Maze's view or visual appearance on the screen for the user.
 */

public class MazeView extends SurfaceView implements SurfaceHolder.Callback, Maze.View, Runnable {

    /**
     * The part of the program managing time.
     */
    private MainThread thread;

    /**
     * The Presenter managing actions such as updating this MazeView.
     */
    private MazePresenter mazePresenter;

    /**
     * The SurfaceHolder object controls actions of the display surface of this MazeView.
     */
    private SurfaceHolder surfaceHolder = null;
    private Paint mazeBrush;
    Canvas mazeCanvas;

    /**
     * The array representing the maze; structured as a grid where each element is a MazeSection.
     */
    private MazeSection[][] mazeGrid;

    /**
     * The number of rows in the maze.
     */

    // the number of rows in the maze

    private static final int ROWS = 7;

    /**
     * The number of columns in the maze.
     */
    private static final int COLS = 4;

    private float mazeSectionLength;

    private float verticalMargin;

    private float horizontalMargin;


    /**
     * Create a new Maze in the context environment.
     * @param context the environment in which this Maze must be seen (MazeActivity.java)
     */
    public MazeView(Context context) {
        super(context);
        setFocusable(true);
        if(surfaceHolder == null) {
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
        }
        thread = new MainThread(surfaceHolder, this);
        mazePresenter = new MazePresenter(this);
        this.setBackgroundColor(Color.BLACK);

        // Array of MazeSection objects that will represent the maze.
        mazeGrid = new MazeSection[ROWS][COLS];

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
        createMazeGrid();
        prepareMazeBrush();
        determineMazeDimensions();
        drawMazeWalls();
    }

    private void createMazeGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                mazeGrid[row][col] = new MazeSection(row, col);
            }
        }
    }

    private void prepareMazeBrush() {
        mazeBrush = new Paint();
        mazeBrush.setColor(Color.WHITE);
        mazeBrush.setStrokeWidth(3);
    }

    private void determineMazeDimensions() {
        mazeCanvas = surfaceHolder.lockCanvas();
        if (mazeCanvas == null) {
            String TAG = "MazeView.determineMazeDimensions";
            Log.e(TAG, "Cannot draw onto the canvas as it's null");
        } else {
            int canvasHeight = getHeight();
            int canvasWidth = getWidth();

            if (canvasHeight/canvasWidth < ROWS/COLS) {
                mazeSectionLength = canvasHeight/(ROWS + 1);
            } else {
                mazeSectionLength = canvasHeight/(ROWS + 1);
            }

            float mazeWidth = COLS*mazeSectionLength;
            float mazeHeight = ROWS*mazeSectionLength;

            verticalMargin = (canvasHeight - mazeHeight)/2;
            horizontalMargin = (canvasWidth - mazeWidth)/2;
            surfaceHolder.unlockCanvasAndPost(mazeCanvas);
        }
    }

    private void drawMazeWalls() {
        String TAG = "MazeView.drawMazeWalls";
        Log.i(TAG, "Trying to draw...");

        mazeCanvas.translate(horizontalMargin, verticalMargin);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (mazeGrid[row][col].getHasTopWall()) {
                    drawTopWall(row, col);
                }
                if (mazeGrid[row][col].getHasBottomWall()) {
                    drawBottomWall(row, col);
                }
                if (mazeGrid[row][col].getHasLeftWall()) {
                    drawLeftWall(row, col);
                }
                if (mazeGrid[row][col].getHasRightWall()) {
                    drawRightWall(row, col);
                }
            }
        }
    }

    private void drawTopWall(int row, int col) {
        mazeCanvas.drawLine(col*mazeSectionLength,
                row*mazeSectionLength,
                (col + 1)*mazeSectionLength,
                row*mazeSectionLength, mazeBrush);
    }

    private void drawBottomWall(int row, int col) {
        mazeCanvas.drawLine(col*mazeSectionLength,
                (row + 1)*mazeSectionLength,
                (col + 1)*mazeSectionLength,
                (row + 1)*mazeSectionLength, mazeBrush);
    }

    private void drawLeftWall(int row, int col) {
        mazeCanvas.drawLine(col*mazeSectionLength,
                row*mazeSectionLength,
                col*mazeSectionLength,
                (row + 1)*mazeSectionLength, mazeBrush);
    }

    private void drawRightWall(int row, int col) {
        mazeCanvas.drawLine((col + 1)*mazeSectionLength,
                row*mazeSectionLength,
                (col + 1)*mazeSectionLength,
                (row + 1)*mazeSectionLength, mazeBrush);
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

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }
}

