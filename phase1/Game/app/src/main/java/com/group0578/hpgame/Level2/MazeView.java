package com.group0578.hpgame.Level2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

/**
 * The Maze's view or visual appearance on the screen for the user.
 */

public class MazeView extends SurfaceView implements Runnable, Maze.View{

    Thread mazeThread = null;
    SurfaceHolder surfaceHolder;
    boolean isRunning = false;

    /** The screen width. */
    private int screenWidth;
    /** The screen height. */
    private int screenHeight;
    /** The array representing the maze; structured as a grid where each element is a MazeSection. */
    private MazeSection[][] mazeGrid;
    /** the paint object used for the maze */
    private Paint mazeBrush;

    private float mazeSectionLength;

    private float verticalMargin;

    private float horizontalMargin;

    private MazePresenter mazePresenter;


    public MazeView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        mazePresenter = new MazePresenter(this);
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
        mazeGrid = mazePresenter.getMazeGrid();
        prepareMazeBrush();
        determineMazeDimensions();

        while (isRunning) {
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            Canvas mazeCanvas = surfaceHolder.lockCanvas();
            mazeCanvas.drawARGB(255, 100, 30, 250);
            drawMazeWalls(mazeCanvas);
            surfaceHolder.unlockCanvasAndPost(mazeCanvas);
        }
    }

    public void pause() {
        isRunning = false;
            try {
                mazeThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        mazeThread = null;
    }

    public void resume() {
        isRunning = true;
        mazeThread = new Thread(this);
        mazeThread.start();
    }

    void prepareMazeBrush() {
        mazeBrush = new Paint(); // maybe instantiate where declared
        mazeBrush.setColor(Color.WHITE);
        mazeBrush.setStrokeWidth(3);
    }

    void determineMazeDimensions() {

        String TAG = "MazeView.determineMazeDimensions";

        int rows = mazeGrid.length;
        int cols = mazeGrid[0].length;

        if (screenHeight <= screenWidth) {
            mazeSectionLength = screenHeight/(rows + 1);
        } else {  // screenWidth < screenHeight
            mazeSectionLength = screenWidth/(cols + 1);
        }

        float mazeWidth = cols*mazeSectionLength;
        float mazeHeight = rows*mazeSectionLength;

        verticalMargin = (screenHeight - mazeHeight)/2;
        horizontalMargin = (screenWidth - mazeWidth)/2;

        Log.e(TAG, "screen height, screen width: " + screenHeight + ", " + screenWidth);
    }

    void drawMazeWalls(Canvas mazeCanvas) {
        String TAG = "MazeView.drawMazeWalls";
        Log.i(TAG, "Trying to draw walls");
        mazeCanvas.translate(horizontalMargin, verticalMargin);
        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[0].length; col++) {
                if (mazeGrid[row][col].getHasTopWall()) {
                    drawTopWall(row, col, mazeCanvas);
                }
                if (mazeGrid[row][col].getHasBottomWall()) {
                    drawBottomWall(row, col, mazeCanvas);
                }
                if (mazeGrid[row][col].getHasLeftWall()) {
                    drawLeftWall(row, col, mazeCanvas);
                }
                if (mazeGrid[row][col].getHasRightWall()) {
                    drawRightWall(row, col, mazeCanvas);
                }
            }
        }
    }

    private void drawTopWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(col*mazeSectionLength,
                row*mazeSectionLength,
                (col + 1)*mazeSectionLength,
                row*mazeSectionLength, mazeBrush);
    }

    private void drawBottomWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(col*mazeSectionLength,
                (row + 1)*mazeSectionLength,
                (col + 1)*mazeSectionLength,
                (row + 1)*mazeSectionLength, mazeBrush);
    }

    private void drawLeftWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine(col*mazeSectionLength,
                row*mazeSectionLength,
                col*mazeSectionLength,
                (row + 1)*mazeSectionLength, mazeBrush);
    }

    private void drawRightWall(int row, int col, Canvas mazeCanvas) {
        mazeCanvas.drawLine((col + 1)*mazeSectionLength,
                row*mazeSectionLength,
                (col + 1)*mazeSectionLength,
                (row + 1)*mazeSectionLength, mazeBrush);
    }

//    /**
//     * The part of the program managing time.
//     */
//    private MainMazeThread thread;
//    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
//    private CharacterSprite character;
//
//    MazeManager mazeManager = new MazeManager(screenHeight, screenWidth);
//
////    /**
////     * The Presenter managing actions such as updating this MazeView.
////     */
////    private MazePresenter mazePresenter;
//
//    /**
//     * The SurfaceHolder object controls actions of the display surface of this MazeView.
//     */
//    private SurfaceHolder surfaceHolder = getHolder();
//
//    Canvas mazeCanvas;
//
//    /**
//     * Create a new Maze in the context environment.
//     * @param context the environment in which this Maze must be seen (MazeActivity.java)
//     */
//    public MazeView(Context context) {
//        super(context);
//        setFocusable(true);
//        surfaceHolder.addCallback(this);
//
//        thread = new MainMazeThread(surfaceHolder, this);
////        mazePresenter = new MazePresenter(this);
//        this.setBackgroundColor(Color.BLACK);
//
//    }
//
//    /**
//     * This is called immediately after the surface is first created.
//     * Implementations of this should start up whatever rendering code
//     * they desire.  Note that only one thread can ever draw into
//     * a {@link MazeView}, so you should not draw into the Surface here
//     * if your normal rendering will be in another thread.
//     *
//     * @param holder The SurfaceHolder whose surface is being created.
//     */
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        character= new CharacterSprite();
//        mazeManager = new MazeManager(screenHeight, screenWidth);
//        mazeManager.setUpMaze();  // creates frame structure for maze
//
//
//        thread.setIsRunning(true);
//        thread.start();
//    }
//
//    /**
//     * This is called immediately after any structural changes (format or
//     * size) have been made to the surface.  You should at this point update
//     * the imagery in the surface.  This method is always called at least
//     * once, after {@link #surfaceCreated}.
//     *
//     * @param holder The SurfaceHolder whose surface has changed.
//     * @param format The new PixelFormat of the surface.
//     * @param width  The new width of the surface.
//     * @param height The new height of the surface.
//     */
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//    }
//
//    /**
//     * This is called immediately before a surface is being destroyed. After
//     * returning from this call, you should no longer try to access this
//     * surface.  If you have a rendering thread that directly accesses
//     * the surface, you must ensure that thread is no longer touching the
//     * Surface before returning from this function.
//     *
//     * @param holder The SurfaceHolder whose surface is being destroyed.
//     */
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        boolean retry = true;
//        while (retry) {
//            try{
//                thread.setIsRunning(false);
//                thread.join();
//            }
//            catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            retry = false;
//        }
//    }
//
//
//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//
//        if (canvas != null) {
//            mazeManager.draw(canvas);
//        }
//    }

}

