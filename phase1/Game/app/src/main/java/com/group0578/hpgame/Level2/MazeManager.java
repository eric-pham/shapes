//package com.group0578.hpgame.Level2;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.Log;
//
//import java.util.ArrayList;
//
//public class MazeManager {
//
//    /** All the locations where a maze item can be. */
//    public ArrayList<MazeItem> mazeItems;
//    /** The screen width. */
//    private int screenWidth;
//    /** The screen height. */
//    private int screenHeight;
//    /** The array representing the maze; structured as a grid where each element is a MazeSection. */
//    private MazeSection[][] mazeGrid;
//    /** The number of rows in the maze. */
//    private static final int ROWS = 7;
//    /** The number of columns in the maze. */
//    private static final int COLS = 4;
//    /** the paint object used for the maze */
//    private Paint mazeBrush;
//
//    private float mazeSectionLength;
//
//    private float verticalMargin;
//
//    private float horizontalMargin;
//
//
//    /**
//     *
//     * @param screenHeight
//     * @param screenWidth
//     */
//
//
//    MazeManager(int screenHeight, int screenWidth) {
//        this.screenHeight = screenHeight;
//        this.screenWidth = screenWidth;
//
//        mazeItems = new ArrayList<>();
//    }
//
//    void draw(Canvas canvas) {
//        drawMazeWalls(canvas);
//    }
//
//    void update(){
//        for (MazeItem mazeItem: mazeItems){
//            mazeItem.update();
//        }
//    }
//
////    void createMazeItems(){
////        for (int i = 0; i < 10; i++){
////            mazeItems.add(new Hoops(gridWidth,gridHeight));
////        }
////    }
//
//    void setUpMaze() {
//        createMazeGrid();
//        prepareMazeBrush();
//        determineMazeDimensions();
//    }
//
//    void createMazeGrid() {
//        mazeGrid = new MazeSection[ROWS][COLS];
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLS; col++) {
//                this.mazeGrid[row][col] = new MazeSection(row, col);
//            }
//        }
//    }
//
//    void prepareMazeBrush() {
//        mazeBrush = new Paint(); // maybe instantiate where declared
//        mazeBrush.setColor(Color.WHITE);
//        mazeBrush.setStrokeWidth(3);
//    }
//
//    void determineMazeDimensions() {
//
//        String TAG = "MazeView.determineMazeDimensions";
//
//        if (screenHeight/screenWidth < ROWS/COLS) {
//            mazeSectionLength = screenWidth/(COLS + 1);
//        } else {
//            mazeSectionLength = screenHeight/(ROWS + 1);
//        }
//
//        float mazeWidth = COLS*mazeSectionLength;
//        float mazeHeight = ROWS*mazeSectionLength;
//
//        verticalMargin = (screenHeight - mazeHeight)/2;
//        horizontalMargin = (screenWidth - mazeWidth)/2;
//
//        Log.e(TAG, "screen height, screen width: " + screenHeight + ", " + screenWidth);
//    }
//
//    void drawMazeWalls(Canvas canvas) {
//        String TAG = "MazeView.drawMazeWalls";
//        Log.i(TAG, "Trying to draw walls");
//        canvas.translate(horizontalMargin, verticalMargin);
//        for (int row = 0; row < ROWS; row++) {
//            for (int col = 0; col < COLS; col++) {
//                if (mazeGrid[row][col].getHasTopWall()) {
//                    drawTopWall(row, col, canvas);
//                }
//                if (mazeGrid[row][col].getHasBottomWall()) {
//                    drawBottomWall(row, col, canvas);
//                }
//                if (mazeGrid[row][col].getHasLeftWall()) {
//                    drawLeftWall(row, col, canvas);
//                }
//                if (mazeGrid[row][col].getHasRightWall()) {
//                    drawRightWall(row, col, canvas);
//                }
//            }
//        }
//    }
//
//    private void drawTopWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine(col*mazeSectionLength,
//                row*mazeSectionLength,
//                (col + 1)*mazeSectionLength,
//                row*mazeSectionLength, mazeBrush);
//    }
//
//    private void drawBottomWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine(col*mazeSectionLength,
//                (row + 1)*mazeSectionLength,
//                (col + 1)*mazeSectionLength,
//                (row + 1)*mazeSectionLength, mazeBrush);
//    }
//
//    private void drawLeftWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine(col*mazeSectionLength,
//                row*mazeSectionLength,
//                col*mazeSectionLength,
//                (row + 1)*mazeSectionLength, mazeBrush);
//    }
//
//    private void drawRightWall(int row, int col, Canvas mazeCanvas) {
//        mazeCanvas.drawLine((col + 1)*mazeSectionLength,
//                row*mazeSectionLength,
//                (col + 1)*mazeSectionLength,
//                (row + 1)*mazeSectionLength, mazeBrush);
//    }
//}
