package com.group0578.hpgame.Level2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

/** The Maze's view or visual appearance on the screen for the user. */
public class MazeView extends SurfaceView implements Runnable, Maze.View, View.OnTouchListener{

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
    private MazeSection exitPoint;
    private Paint playerPaint, exitPointPaint;
    private Paint textBrush = new Paint();
    private Player player;
//    private float playerX, playerY;

    public MazeView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        mazePresenter = new MazePresenter(this);
        textBrush.setColor(Color.WHITE);
        textBrush.setStrokeWidth(10);
        textBrush.setTextSize(50);
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
        player = new Player(0,0);
        setPlayerExitLocations();
        prepareMazeBrush();
        determineMazeDimensions();
        createPlayer();
        createExitPoint();

        while (isRunning) {
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }

            Canvas mazeCanvas = surfaceHolder.lockCanvas();
            mazeCanvas.drawARGB(255, 100, 30, 250);
            mazeCanvas.drawText("Player = Circle", 100, 100, textBrush);
            mazeCanvas.drawText("EndPoint = Square", 100, 160, textBrush);
            drawMazeWalls(mazeCanvas);
            drawPlayer(mazeCanvas, mazeSectionLength/10);
            drawExitPoint(mazeCanvas, mazeSectionLength/10);
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

    private void setPlayerExitLocations() {
        int rows = mazePresenter.getRowColumnAttributes()[0];
        int cols = mazePresenter.getRowColumnAttributes()[1];
        exitPoint = mazeGrid[rows-1][cols-1];
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

    private void createPlayer() {
        playerPaint = new Paint();
        playerPaint.setColor(Color.RED);
    }

    private void createExitPoint() {
        exitPointPaint = new Paint();
        exitPointPaint.setColor(Color.GREEN);
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

    private void drawPlayer(Canvas mazeCanvas, float margin) {
        if (player.hasMoved()) {
            float left = player.getCol() * mazeSectionLength + margin;
            float right = (player.getCol() + 1) * mazeSectionLength - margin;
            float top = player.getRow() * mazeSectionLength + margin;
            float bottom = (player.getRow() + 1) * mazeSectionLength - margin;
            mazeCanvas.drawCircle((left+right)/2, (top+bottom)/2,
                    mazeSectionLength/3, playerPaint);
        } else {  // player has already moved at least once
            float firstX = mazeSectionLength / 2;
            float firstY = mazeSectionLength / 2;
            mazeCanvas.drawCircle(firstX, firstY,
                    mazeSectionLength/3, playerPaint);
        }

    }

    private void drawExitPoint(Canvas mazeCanvas, float margin) {
        float left = exitPoint.getCol() * mazeSectionLength + margin;
        float right = (exitPoint.getCol() + 1) * mazeSectionLength - margin;
        float top = exitPoint.getRow() * mazeSectionLength + margin;
        float bottom = (exitPoint.getRow() + 1) * mazeSectionLength - margin;
        mazeCanvas.drawRect(left, top, right, bottom, exitPointPaint);
    }

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!player.hasMoved()) {
            player.setHasMoved(true);
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float touchX = event.getX(), touchY = event.getY();

            float currPlayerX = horizontalMargin + (player.getCol()+0.5f) * mazeSectionLength;
            float currPlayerY = verticalMargin + (player.getRow()+0.5f) * mazeSectionLength;

            float displacementX = touchX - currPlayerX, displacementY = touchY - currPlayerY;

            float absDisplacementX = Math.abs(displacementX),
                    absDisplacementY = Math.abs(displacementY);

            if (absDisplacementX > mazeSectionLength || absDisplacementY > mazeSectionLength) {

                if (absDisplacementX > absDisplacementY) {  // move horizontally
                    if (displacementX > 0) {
                        movePlayerRight();
                    } else {
                        movePlayerLeft();
                    }
                } else {    // move vertically
                    if (displacementY > 0) {
                        movePlayerDown();
                    } else {
                        movePlayerUp();
                    }
                }

            }
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }

        return true;
    }

    private void movePlayerUp() {
        if (!mazeGrid[player.getRow()][player.getCol()].getHasTopWall()) {
            player.setRow(player.getRow() - 1);
        }
    }

    private void movePlayerDown() {
        if (!mazeGrid[player.getRow()][player.getCol()].getHasBottomWall()) {
            player.setRow(player.getRow() + 1);
        }
    }

    private void movePlayerLeft() {
        if (!mazeGrid[player.getRow()][player.getCol()].getHasLeftWall()) {
            player.setCol(player.getCol() - 1);
        }
    }

    private void movePlayerRight() {
        if (!mazeGrid[player.getRow()][player.getCol()].getHasRightWall()) {
            player.setCol(player.getCol() + 1);
        }
    }


}
