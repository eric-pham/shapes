//package com.group0578.hpgame.Level2;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//
//import com.group0578.hpgame.Level2.MazeEntities.MazeSection;
//
///**
// * The Maze's view or visual appearance on the screen for the user.
// *
// * Implementing interface Runnable -- allows performing actions on Thread objects.
// * Implementing View.OnTouchListener -- for performing actions in response to user clicks
// */
//public class MazeView extends SurfaceView implements Runnable, Maze.View, View.OnTouchListener{
//
//    /**
//     * The thread that begins running the Maze Level the user sees on the screen.
//     */
//    Thread mazeThread = null;
//
//    /**
//     * The surfaceHolder containing the surface of this MazeView screen.
//     */
//    SurfaceHolder surfaceHolder;
//
//    /**
//     * Whether the Thread mazeThread object is currently running or not.
//     */
//    boolean isRunning = false;
//
//    /**
//     * The width of the screen representing this MazeView.
//     */
//    private int screenWidth;
//
//    /**
//     * The height of the screen representing this MazeView.
//     */
//    private int screenHeight;
//
//    /**
//     * The array representing the maze; structured as a grid where each element is a MazeSection.
//     */
//    private MazeSection[][] mazeGrid;
//
//    /**
//     * The Paint object used to draw the grid lines of the maze on the screen.
//     */
//    private Paint mazeBrush;
//
//    /**
//     * The numerical value for the length of each MazeSection in the mazeGrid array.
//     */
//    private float mazeSectionLength;
//
//    /**
//     * The length between the top of the screen and beginning of the Maze.
//     */
//    private float verticalMargin;
//
//    /**
//     * The length between the sides of the screen and the Maze.
//     */
//    private float horizontalMargin;
//
//    /**
//     * An instance of the mazePresenter responsible for handling user's actions.
//     */
//    private MazePresenter mazePresenter;
//
//    /**
//     * The final location in the mazeGrid array the user reaches upon completion of the Maze.
//     */
//    private MazeSection exitPoint;
//
//    /**
//     * The Paint objects used to draw and distinguish the Player's current location from
//     * the exit Point.
//     */
//    private Paint playerPaint, exitPointPaint;
//
//    /**
//     * The Paint object used to draw text on the screen -- used for creating the legend.
//     */
//    private Paint textBrush = new Paint();
//
//    /**
//     * The Player object that is currently playing the Maze.
//     * Used for keeping track of current location in the Maze.
//     */
//    private Player player;
//
//    /**
//     * Construct a new instance of a MazeView.
//     * @param context the environment making this MazeView appear on the screen.
//     */
//    public MazeView(Context context) {
//        super(context);
//
//        // Initialize instance attributes
//        surfaceHolder = getHolder();
//        screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//        screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
//        mazePresenter = new MazePresenter(this);
//
//        // Prepare the textBrush object to draw text on the screen in run() method
//        textBrush.setColor(Color.WHITE);
//        textBrush.setStrokeWidth(10);
//        textBrush.setTextSize(50);
//    }
//
//    /**
//     * When an object implementing interface <code>Runnable</code> is used
//     * to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * The general contract of the method <code>run</code> is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread #run()
//     */
//    @Override
//    public void run() {
//
//        // Receiving mazeGrid from Presenter
//        mazeGrid = mazePresenter.getMazeGrid();
//        // Initializing new Player object with coordinates 0, 0 in mazeGrid array
//        player = new Player(0,0);
//
//        setExitLocation();
//        prepareMazeBrush();
//        determineMazeDimensions();
//        makePlayer();
//        makeExitPoint();
//
//        // Only draw the Maze when the thread is currently running
//        while (isRunning) {
//
//            // If surface is null, then don't draw the maze
//            if (!surfaceHolder.getSurface().isValid()) {
//                continue;
//            }
//
//            // Creating a canvas object to draw the Maze and other components on the screen
//            Canvas mazeCanvas = surfaceHolder.lockCanvas();
//            mazeCanvas.drawARGB(255, 100, 30, 250);
//            mazeCanvas.drawText("Player = Circle", 100, 100, textBrush);
//            mazeCanvas.drawText("EndPoint = Square", 100, 160, textBrush);
//            drawMazeWalls(mazeCanvas);
//            drawPlayer(mazeCanvas, mazeSectionLength/10);
//            drawExitPoint(mazeCanvas, mazeSectionLength/10);
//
//            // After drawing and performing actions on mazeCanvas, show mazeCanvas on the screen
//            surfaceHolder.unlockCanvasAndPost(mazeCanvas);
//        }
//
//    }
//
//    /**
//     * Stops the current thread from running and sets instance attribute
//     * mazeThread to a null object.
//     */
//    public void pause() {
//        isRunning = false;
//            try {
//                mazeThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        mazeThread = null;
//    }
//
//    /**
//     * Begins running a new thread and sets mazeThread to a new Thread object.
//     */
//    public void resume() {
//        isRunning = true;
//        mazeThread = new Thread(this);
//        mazeThread.start();
//    }
//
//    /**
//     * Initializes the exitPoint attribute to last column and last row in the mazeGrid array.
//     */
//    private void setExitLocation() {
//        int rows = mazePresenter.getRowColumnAttributes()[0];
//        int cols = mazePresenter.getRowColumnAttributes()[1];
//        exitPoint = mazeGrid[rows-1][cols-1];
//    }
//
//    /**
//     * Initializes and performs actions on mazeBrush attribute which is used to draw the maze.
//     */
//    void prepareMazeBrush() {
//        mazeBrush = new Paint();
//        mazeBrush.setColor(Color.WHITE);
//        mazeBrush.setStrokeWidth(3);
//    }
//
//    /**
//     * Determines the dimensions for the Maze by deciding:
//     *
//     * 1) How large each mazeSection should be on the screen (setting mazeSectionLength attribute)
//     * 2) Vertical and horizontal margins between the maze and edges of the screen
//     */
//    void determineMazeDimensions() {
//
//        String TAG = "MazeView.determineMazeDimensions";
//
//        int rows = mazeGrid.length;
//        int cols = mazeGrid[0].length;
//
//        // Phone screen rotated sideways or landscape mode
//        if (screenHeight <= screenWidth) {
//            mazeSectionLength = screenHeight/(rows + 1);
//        } else {  // phone screen vertically oriented or portrait mode
//            mazeSectionLength = screenWidth/(cols + 1);
//        }
//
//        float mazeWidth = cols*mazeSectionLength;
//        float mazeHeight = rows*mazeSectionLength;
//
//        // Setting margins based on mazeWidth and mazeHeight attributes
//        verticalMargin = (screenHeight - mazeHeight)/2;
//        horizontalMargin = (screenWidth - mazeWidth)/2;
//
//        Log.e(TAG, "screen height, screen width: " + screenHeight + ", " + screenWidth);
//    }
//
//    /**
//     * Initializes a paint object used to draw the player.
//     */
//    private void makePlayer() {
//        playerPaint = new Paint();
//        playerPaint.setColor(Color.RED);
//    }
//
//    /**
//     * Initializes a paint object used to draw the exit point in the maze.
//     */
//    private void makeExitPoint() {
//        exitPointPaint = new Paint();
//        exitPointPaint.setColor(Color.GREEN);
//    }
//
//    /**
//     * Drawing the walls of the maze based on if each mazeSection in the mazeGrid has a
//     * top, bottom, left or right wall
//     * @param mazeCanvas the canvas on which to draw the walls of the maze.
//     */
//    void drawMazeWalls(Canvas mazeCanvas) {
//        String TAG = "MazeView.drawMazeWalls";
//        Log.i(TAG, "Trying to draw walls");
//
//        // translating canvas makes drawing walls easier
//        // moves 'origin' of where objects are drawn on the screen to top-left corner of the maze
//        mazeCanvas.translate(horizontalMargin, verticalMargin);
//
//        // Drawing the walls for each of the mazeSections inside mazeGrid array
//        for (int row = 0; row < mazeGrid.length; row++) {
//            for (int col = 0; col < mazeGrid[0].length; col++) {
//                if (mazeGrid[row][col].getHasTopWall()) {
//                    drawTopWall(row, col, mazeCanvas);
//                }
//                if (mazeGrid[row][col].getHasBottomWall()) {
//                    drawBottomWall(row, col, mazeCanvas);
//                }
//                if (mazeGrid[row][col].getHasLeftWall()) {
//                    drawLeftWall(row, col, mazeCanvas);
//                }
//                if (mazeGrid[row][col].getHasRightWall()) {
//                    drawRightWall(row, col, mazeCanvas);
//                }
//            }
//        }
//
//    }
//
//    /**
//     * Drawing a line on the screen representing the top wall of some mazeSection
//     * in the mazeGrid array.
//     *
//     * @param row the row in the mazeGrid of where to draw the top wall
//     * @param col the column in the mazeGrid of where to draw the top wall
//     * @param mazeCanvas the canvas on which to draw the top wall
//     */
//    private void drawTopWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine(col*mazeSectionLength,
//                row*mazeSectionLength,
//                (col + 1)*mazeSectionLength,
//                row*mazeSectionLength, mazeBrush);
//    }
//
//    /**
//     * Drawing a line on the screen representing the bottom wall of some mazeSection
//     * in the mazeGrid array.
//     *
//     * @param row the row in the mazeGrid of where to draw the bottom wall
//     * @param col the column in the mazeGrid of where to draw the bottom wall
//     * @param mazeCanvas the canvas on which to draw the bottom wall
//     */
//    private void drawBottomWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine(col*mazeSectionLength,
//                (row + 1)*mazeSectionLength,
//                (col + 1)*mazeSectionLength,
//                (row + 1)*mazeSectionLength, mazeBrush);
//    }
//
//    /**
//     * Drawing a line on the screen representing the left wall of some mazeSection
//     * in the mazeGrid array.
//     *
//     * @param row the row in the mazeGrid of where to draw the left wall
//     * @param col the column in the mazeGrid of where to draw the left wall
//     * @param mazeCanvas the canvas on which to draw the left wall
//     */
//    private void drawLeftWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine(col*mazeSectionLength,
//                row*mazeSectionLength,
//                col*mazeSectionLength,
//                (row + 1)*mazeSectionLength, mazeBrush);
//    }
//
//    /**
//     * Drawing a line on the screen representing the right wall of some mazeSection
//     * in the mazeGrid array.
//     *
//     * @param row the row in the mazeGrid of where to draw the right wall
//     * @param col the column in the mazeGrid of where to draw the right wall
//     * @param mazeCanvas the canvas on which to draw the right wall
//     */
//    private void drawRightWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine((col + 1)*mazeSectionLength,
//                row*mazeSectionLength,
//                (col + 1)*mazeSectionLength,
//                (row + 1)*mazeSectionLength, mazeBrush);
//    }
//
//    /**
//     * Drawing a circle on the screen representing the player's current location in the maze.
//     * @param mazeCanvas the canvas on which to draw the circle/player
//     * @param margin the margin between the player and the maze walls.
//     */
//    private void drawPlayer(Canvas mazeCanvas, float margin) {
//        if (player.hasMoved()) {
//            float left = player.getCol() * mazeSectionLength + margin;
//            float right = (player.getCol() + 1) * mazeSectionLength - margin;
//            float top = player.getRow() * mazeSectionLength + margin;
//            float bottom = (player.getRow() + 1) * mazeSectionLength - margin;
//            mazeCanvas.drawCircle((left+right)/2, (top+bottom)/2,
//                    mazeSectionLength/3, playerPaint);
//        } else {  // player has already moved at least once
//            float firstX = mazeSectionLength / 2;
//            float firstY = mazeSectionLength / 2;
//            mazeCanvas.drawCircle(firstX, firstY,
//                    mazeSectionLength/3, playerPaint);
//        }
//
//    }
//
//    /**
//     * Drawing a rectangle on the screen representing the exit point location in the maze.
//     * @param mazeCanvas the canvas on which to draw the square/exit location.
//     * @param margin the margin between the exitPoint location and the maze walls.
//     */
//    private void drawExitPoint(Canvas mazeCanvas, float margin) {
//        float left = exitPoint.getCol() * mazeSectionLength + margin;
//        float right = (exitPoint.getCol() + 1) * mazeSectionLength - margin;
//        float top = exitPoint.getRow() * mazeSectionLength + margin;
//        float bottom = (exitPoint.getRow() + 1) * mazeSectionLength - margin;
//        mazeCanvas.drawRect(left, top, right, bottom, exitPointPaint);
//    }
//
//    /**
//     * Called when a touch event is dispatched to a view. This allows users to click/drag the
//     * player on the screen.
//     *
//     * @param v The view the touch event has been dispatched to.
//     * @param event The MotionEvent object containing full information about the event.
//     * @return True if the listener has consumed the event, false otherwise.
//     */
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//        // If the user clicks and drags the mouse, then an action has been detected
//        if (event.getAction() == MotionEvent.ACTION_MOVE) {
//
//            if (!player.hasMoved()) {
//                player.setHasMoved(true); // Action move event detected so player moved.
//            }
//
//            // Gets the x and y coordinates of where the user clicked on the screen.
//            float touchX = event.getX(), touchY = event.getY();
//
//            // Player's current x and y locations in the Maze
//            float currPlayerX = horizontalMargin + (player.getCol()+0.5f) * mazeSectionLength;
//            float currPlayerY = verticalMargin + (player.getRow()+0.5f) * mazeSectionLength;
//
//            // The difference between where the user clicked and player's location
//            float displacementX = touchX - currPlayerX, displacementY = touchY - currPlayerY;
//
//            float absDisplacementX = Math.abs(displacementX),
//                    absDisplacementY = Math.abs(displacementY);
//
//            // Checks if user clicked/dragged "far enough" to move the player
//            // Only when the user clicks/drags the cursor a length larger than mazeSectionLength
//            // does the player on the screen begin to move.
//            if (absDisplacementX > mazeSectionLength || absDisplacementY > mazeSectionLength) {
//
//                if (absDisplacementX > absDisplacementY) {  // move horizontally
//                    if (displacementX > 0) {
//                        movePlayerRight();
//                    } else {
//                        movePlayerLeft();
//                    }
//                } else {    // move vertically
//                    if (displacementY > 0) {
//                        movePlayerDown();
//                    } else {
//                        movePlayerUp();
//                    }
//                }
//
//            }
//        }
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            return true;
//        }
//
//        return true; // returns true so the user can continue dragging/clicking to move the player
//    }
//
//    /**
//     * Moves the player's current location up which means one row higher in the mazeGrid array.
//     */
//    private void movePlayerUp() {
//        if (!mazeGrid[player.getRow()][player.getCol()].getHasTopWall()) {
//            player.setRow(player.getRow() - 1);
//        }
//    }
//
//    /**
//     * Moves the player's current location down which means one row larger in the mazeGrid array.
//     */
//    private void movePlayerDown() {
//        if (!mazeGrid[player.getRow()][player.getCol()].getHasBottomWall()) {
//            player.setRow(player.getRow() + 1);
//        }
//    }
//
//    /**
//     * Moves the player's current location left: One column smaller in the mazeGrid array
//     */
//    private void movePlayerLeft() {
//        if (!mazeGrid[player.getRow()][player.getCol()].getHasLeftWall()) {
//            player.setCol(player.getCol() - 1);
//        }
//    }
//
//    /**
//     * Moves the player's current location right: One column larger in the mazeGrid array
//     */
//    private void movePlayerRight() {
//        if (!mazeGrid[player.getRow()][player.getCol()].getHasRightWall()) {
//            player.setCol(player.getCol() + 1);
//        }
//    }
//
//}
