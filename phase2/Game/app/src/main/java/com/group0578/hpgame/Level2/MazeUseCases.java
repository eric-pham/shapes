package com.group0578.hpgame.Level2;

import com.group0578.hpgame.Level2.MazeEntities.MazeSection;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/** The business logic of the Level 2 Maze Game. */
public class MazeUseCases {

  /** The number of rows in the maze. */
  private final int ROWS = 4;
  /** The number of columns in the maze. */
  private final int COLS = 4;

  /** An enum for the relative location between two MazeSections. */
  private enum RelativeLocation {
    ABOVE,
    BELOW,
    LEFT,
    RIGHT
  }

  /** Constructor for the MazeUseCases class */
  public MazeUseCases() {}

  /**
   * Initializes a 2D array to hold the required number of MazeSection objects that will be used to
   * build the maze.
   *
   * @return A modified mazeGrid (a 2D array of type MazeSection) with random walls.
   */
  public MazeSection[][] buildMazeGrid() {
    MazeSection[][] mazeGrid = new MazeSection[ROWS][COLS];
    return createMazeGrid(mazeGrid);
  }

  /**
   * Creates the design if the maze.
   *
   * @param mazeGrid The 2D array of the MazeSections that define the maze.
   * @return A modified mazeGrid (a 2D array of type MazeSection) with random walls.
   */
  private MazeSection[][] createMazeGrid(MazeSection[][] mazeGrid) {
    // a stack to hold the current path being taken to navigate through the maze
    Stack<MazeSection> mazePath = new Stack<>();
    // variables to represent the current MazeSection that will have its walls modified to
    // create the mazePath and the next MazeSection to have its wall modified.
    MazeSection currSection, nextSection;

    // creates the base structure of the maze by populating the mazeGrid with MazeSection objects.
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        mazeGrid[row][col] = new MazeSection(row, col);
      }
    }

    // sets the first currSection to be the upper left MazeSection
    currSection = mazeGrid[0][0];
    // sets modified to true
    currSection.setModified(true);

    // loops through possible paths through the maze and builds the maze by taking down the
    // necessary walls, while there still exist non-modified MazeSections.
    do {
      // chooses the next MazeSection
      nextSection = getNextMazeSection(currSection, mazeGrid);

      // if there is a nextSection
      if (nextSection != null) {
        // finds its location relative to the currSection
        RelativeLocation relativeLocation = specifyLocation(currSection, nextSection);
        // takes down the necessary walls
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

    // return the modified mazeGrid 2D array
    return mazeGrid;
  }

  /**
   * Takes down the walls between the current MazeSection and the next MazeSection by setting the
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
   * @param mazeGrid The 2D array of the MazeSections that define the maze.
   * @return Null if there are no candidates left for the next MazeSection, or a MazeSection if
   *     there still exist non-modified MazeSections.
   */
  private MazeSection getNextMazeSection(MazeSection currSection, MazeSection[][] mazeGrid) {
    // local array list to hold the possible candidates for nextSection
    ArrayList<MazeSection> candidates = new ArrayList<>();

    // check section above
    if (currSection.getRow() > 0) {
      MazeSection upSection = mazeGrid[currSection.getRow() - 1][currSection.getCol()];
      if (!upSection.isModified()) {
        candidates.add(upSection);
      }
    }

    // check section below
    if (currSection.getRow() < ROWS - 1) {
      MazeSection downSection = mazeGrid[currSection.getRow() + 1][currSection.getCol()];
      if (!downSection.isModified()) {
        candidates.add(downSection);
      }
    }

    // check section to the left
    if (currSection.getCol() > 0) {
      MazeSection leftSection = mazeGrid[currSection.getRow()][currSection.getCol() - 1];
      if (!leftSection.isModified()) {
        candidates.add(leftSection);
      }
    }

    // check section to the right
    if (currSection.getCol() < COLS - 1) {
      MazeSection rightSection = mazeGrid[currSection.getRow()][currSection.getCol() + 1];
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

  /**
   * Getter for rows instance attribute
   *
   * @return number of rows in the Maze Grid
   */
  public int getROWS() {
    return ROWS;
  }

  /**
   * Getter for cols instance attribute
   *
   * @return number of columns in the Maze Grid
   */
  public int getCOLS() {
    return COLS;
  }
}
