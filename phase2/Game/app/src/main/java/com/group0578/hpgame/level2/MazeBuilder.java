package com.group0578.hpgame.level2;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/** The MazeBuilder responsible for creating maze sections, walls, dimensions etc. */
class MazeBuilder {

  /** An enum for the relative location between two MazeSections. */
  private enum RelativeLocation {
    ABOVE,
    BELOW,
    LEFT,
    RIGHT
  }

  /** The Maze object being built by this MazeBuilder. */
  private Maze maze;

  MazeBuilder() {
    this.maze = new Maze();
  }

  /**
   * The series of instructions to create all the tools and objects needed to draw the maze
   *
   * @param difficulty the level difficulty
   * @param colourScheme the string representing the colour scheme of the game
   * @param character the character representing the shape of the player in this maze.
   * @param playerLives the number of lives left for the player in the maze level.
   */
  Maze build(String difficulty, String colourScheme, String character, int playerLives) {
    // gets the screen height and width
    this.maze.setScreenWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
    this.maze.setScreenHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
    this.maze.setCharacter(character);
    this.maze.setColourScheme(colourScheme); // might not need this

    // Building the maze
    buildMazeGrid(difficulty);
    buildMaze();

    // Initializing new Player object with coordinates (0,0) in mazeGrid array
    this.maze.setPlayer(new Player(0, 0, playerLives));

    // prepares the exit
    setExitLocation();

    // prepares the brushes for the maze outline and the text
    prepareMazeBrushes(colourScheme);

    // calculates the dimensions of the margins and the lengths of the walls
    determineMazeDimensions();

    // prepares the Paint objects for the player and exit
    makePlayer(colourScheme);
    makeExitPoint(colourScheme);

    return this.maze;
  }

  /**
   * Initializes a 2D array to hold the required number of MazeSection objects that will be used to
   * build the maze.
   */
  private void buildMazeGrid(String difficulty) {

    if (difficulty.equalsIgnoreCase("Easy")) { // the difficulty is set to 'Easy'
      this.maze.setRows(4);
      this.maze.setCols(5);
    } else { // the difficulty is set to 'Hard'
      this.maze.setRows(8);
      this.maze.setCols(5);
    }
    this.maze.setMazeGrid(new MazeSection[maze.getRows()][maze.getCols()]);
  }

  /**
   * Creates the design of the maze. This method was derived from Parts 3 and 4 of Y-Key's "Android
   * Programming - Maze Game" 5 part Tutorial and modified to suit the needs of our project. Source:
   * Part 3: https://www.youtube.com/watch?v=kiG1BUa34lc&t=1s Part 4:
   * https://www.youtube.com/watch?v=IMan30VNi3A&t=1s
   */
  private void buildMaze() {
    // a stack to hold the current path being taken to navigate through the maze
    Stack<MazeSection> mazePath = new Stack<>();
    // variables to represent the current MazeSection that will have its walls modified to
    // create the mazePath and the next MazeSection to have its wall modified.
    MazeSection currSection, nextSection;

    // creates the base structure of the maze by populating the mazeGrid with MazeSection objects.
    for (int row = 0; row < this.maze.getRows(); row++) {
      for (int col = 0; col < this.maze.getCols(); col++) {
        this.maze.getMazeGrid()[row][col] = new MazeSection(row, col);
      }
    }

    // sets the first currSection to be the upper left MazeSection
    currSection = this.maze.getMazeGrid()[0][0];
    // sets modified to true
    currSection.setModified(true);

    // loops through possible paths through the maze and builds the maze by taking click the
    // necessary walls, while there still exist non-modified MazeSections.
    do {
      // chooses the next MazeSection
      nextSection = getNextMazeSection(currSection);

      // if there is a nextSection
      if (nextSection != null) {
        // finds its location relative to the currSection
        RelativeLocation relativeLocation = specifyLocation(currSection, nextSection);
        // takes click the necessary walls
        modifyWalls(currSection, nextSection, relativeLocation);
        // adds currSection to the mazePath stack
        mazePath.push(currSection);
        // sets nextSection to be the new currSection
        currSection = nextSection;
        // sets the new currSection to be modified
        currSection.setModified(true);
      } else { // there are no more adjacent non-modified MazeSections
        // backtracks through the mazePath until a MazeSection with non-modified adjacent
        // MazeSections is found
        currSection = mazePath.pop();
      }
    } while (!mazePath.isEmpty());
  }

  /**
   * Takes click the walls between the current MazeSection and the next MazeSection by setting the
   * relevant boolean variables to false.
   *
   * @param currSection The current MazeSection.
   * @param nextSection The next MazeSection.
   * @param relativeLocation A RelativeLocation enum value that describes the location of
   *     nextSection with respect t currSection.
   */
  private void modifyWalls(
      MazeSection currSection, MazeSection nextSection, RelativeLocation relativeLocation) {

    switch (relativeLocation) {
      case ABOVE: // nextSection is above currSection
        // removes the walls between currSection & nextSection
        currSection.setHasTopWall(false);
        nextSection.setHasBottomWall(false);
        break;
      case BELOW: // nextSection is below currSection
        // removes the walls between currSection & nextSection
        currSection.setHasBottomWall(false);
        nextSection.setHasTopWall(false);
        break;
      case LEFT: // nextSection is to the left of currSection
        // removes the walls between currSection & nextSection
        currSection.setHasLeftWall(false);
        nextSection.setHasRightWall(false);
        break;
      case RIGHT: // nextSection is to the right of currSection
        // removes the walls between currSection & nextSection
        currSection.setHasRightWall(false);
        nextSection.setHasLeftWall(false);
        break;
    }
  }

  /**
   * Depending on the current MazeSection, a list of possible adjacent and non-modified (instance
   * variable modified = false) MazeSections are gathered into an array list and one is chosen at
   * random to be the next MazeSection.
   *
   * @param currSection The current MazeSection.
   * @return Null if there are no candidates left for the next MazeSection, or a MazeSection if
   *     there still exist non-modified MazeSections.
   */
  private MazeSection getNextMazeSection(MazeSection currSection) {
    // local array list to hold the possible candidates for nextSection
    ArrayList<MazeSection> candidates = new ArrayList<>();

    // check section above
    if (currSection.getRow() > 0) {
      MazeSection upSection =
          this.maze.getMazeGrid()[currSection.getRow() - 1][currSection.getCol()];
      if (!upSection.isModified()) {
        candidates.add(upSection);
      }
    }

    // check section below
    if (currSection.getRow() < this.maze.getRows() - 1) {
      MazeSection downSection =
          this.maze.getMazeGrid()[currSection.getRow() + 1][currSection.getCol()];
      if (!downSection.isModified()) {
        candidates.add(downSection);
      }
    }

    // check section to the left
    if (currSection.getCol() > 0) {
      MazeSection leftSection =
          this.maze.getMazeGrid()[currSection.getRow()][currSection.getCol() - 1];
      if (!leftSection.isModified()) {
        candidates.add(leftSection);
      }
    }

    // check section to the right
    if (currSection.getCol() < this.maze.getCols() - 1) {
      MazeSection rightSection =
          this.maze.getMazeGrid()[currSection.getRow()][currSection.getCol() + 1];
      if (!rightSection.isModified()) {
        candidates.add(rightSection);
      }
    }

    // randomly chooses one of the candidates to be the nextSection.
    if (candidates.size() > 0) {
      Random randNum = new Random();
      return candidates.get(randNum.nextInt(candidates.size()));
    }
    return null;
  }

  /**
   * Determines the locational relationship between the current MazeSection and the next
   * MazeSection.
   *
   * @param currSection The current MazeSection.
   * @param nextSection The next MazeSection.
   * @return A location that describes where nextSection is with respect to currSection.
   */
  private RelativeLocation specifyLocation(MazeSection currSection, MazeSection nextSection) {

    int currCol = currSection.getCol();
    int currRow = currSection.getRow();
    int nextCol = nextSection.getCol();
    int nextRow = nextSection.getRow();

    if (currCol == nextCol && currRow == nextRow + 1) {
      return RelativeLocation.ABOVE;
    } else if (currCol == nextCol && currRow == nextRow - 1) {
      return RelativeLocation.BELOW;
    } else if (currCol == nextCol + 1 && currRow == nextRow) {
      return RelativeLocation.LEFT;
    } else {
      return RelativeLocation.RIGHT;
    }
  }

  /** Initializes the exitPoint attribute to last column and last row in the mazeGrid array. */
  private void setExitLocation() {
    int rows = this.maze.getRows() - 1, cols = this.maze.getCols() - 1;
    this.maze.setExitPoint(this.maze.getMazeGrid()[rows][cols]);
  }

  /**
   * Initializes and performs actions on mazeBrush attribute which is used to draw the maze.
   * Initializes and performs actions on textBrush attribute which is used to draw the text.
   *
   * @param colourScheme the string representing the colour scheme of the game
   */
  private void prepareMazeBrushes(String colourScheme) {
    int colour;
    if (colourScheme.equalsIgnoreCase("Light")) {
      colour = Color.BLACK;
    } else {
      colour = Color.WHITE;
    }
    // Prepare the mazeBrush object to draw maze on the screen in run() method of the MazeThread
    Paint mazeBrush = new Paint();
    mazeBrush.setColor(colour);
    mazeBrush.setStrokeWidth(3);
    this.maze.setMazeBrush(mazeBrush);

    // Prepare the textBrush object to draw text on the screen in run() method of the MazeThread
    Paint textBrush = new Paint();
    textBrush.setColor(colour);
    textBrush.setStrokeWidth(10);
    textBrush.setTextSize(50);
    this.maze.setTextBrush(textBrush);
  }

  /**
   * Determines the dimensions for the Maze by deciding:
   *
   * <p>1) How large each mazeSection should be on the screen (setting mazeSectionLength attribute)
   * 2) Vertical and horizontal margins between the maze and edges of the screen
   */
  private void determineMazeDimensions() {

    String TAG = "MazeBuilder.determineMazeDimensions";

    int rows = this.maze.getMazeGrid().length;
    int cols = this.maze.getMazeGrid()[0].length;

    int screenHeight = this.maze.getScreenHeight();
    int screenWidth = this.maze.getScreenWidth();
    // Phone screen rotated sideways or landscape mode
    if (screenHeight <= screenWidth) {
      this.maze.setMazeSectionLength(screenHeight / (rows + 1));
    } else { // phone screen vertically oriented or portrait mode
      this.maze.setMazeSectionLength(screenWidth / (cols + 1));
    }

    float mazeWidth = cols * this.maze.getMazeSectionLength();
    float mazeHeight = rows * this.maze.getMazeSectionLength();

    // Setting margins based on mazeWidth and mazeHeight attributes
    this.maze.setVerticalMargin((screenHeight - mazeHeight) / 2);
    this.maze.setHorizontalMargin((screenWidth - mazeWidth) / 2);

    Log.e(TAG, "screen height, screen width: " + screenHeight + ", " + screenWidth);
  }

  /**
   * Initializes a paint object used to draw the player.
   *
   * @param colourScheme the string representing the colour scheme of the game
   */
  private void makePlayer(String colourScheme) {
    int colour;
    if (colourScheme.equalsIgnoreCase("Light")) {
      colour = Color.argb(255, 0, 191, 230);
    } else {
      colour = Color.RED;
    }

    Paint playerPaint = new Paint();
    playerPaint.setColor(colour);
    this.maze.setPlayerPaint(playerPaint);
    String TAG = "MazeBuilder.makePlayer";
    Log.e(TAG, "test");
  }

  /**
   * Initializes a paint object used to draw the exit point in the maze.
   *
   * @param colourScheme the string representing the colour scheme of the game
   */
  private void makeExitPoint(String colourScheme) {
    int colour;
    if (colourScheme.equalsIgnoreCase("Light")) {
      //      colour = Color.argb(255, 230, 190, 170);
      colour = Color.argb(255, 255, 77, 106);
    } else {
      colour = Color.GREEN;
    }

    Paint exitPointPaint = new Paint();
    exitPointPaint.setColor(colour);
    this.maze.setExitPointPaint(exitPointPaint);
    String TAG = "MazeBuilder.makeExitPoint";
    Log.e(TAG, "test");
  }
}
