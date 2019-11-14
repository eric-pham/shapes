package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import java.util.ArrayList;

class Manager {

  /** List of all dementors. */
  private ArrayList<Dementor> myLittledementors;
  /** List of all blasts. */
  private ArrayList<Blast> myBlasts;
  /** The wand on the screen. */
  private Wand wand;
  /** The width of the screen. */
  private int gridWidth;
  /** The height of the screen. */
  private int gridHeight;
  /** The number of dementors created. */
  private int cnt;

  /**
   * The constructor for this manager.
   *
   * @param width The width of the screen this manager is managing.
   * @param height The height of the screen this manager is managing.
   */
  Manager(int width, int height) {
    gridWidth = width;
    gridHeight = height;
    myLittledementors = new ArrayList<>();
    myBlasts = new ArrayList<>();
    wand = new Wand(gridWidth / 2, gridHeight - 10);
    cnt = 0;
  }

  ArrayList<Blast> getMyBlasts() {
    return myBlasts;
  }

  int getGridWidth() {
    return gridWidth;
  }

  /**
   * Draws the wand, the blasts and the dementors created by this manager.
   *
   * @param canvas the graphics context in which to draw this item.
   */
  void draw(Canvas canvas) {
    wand.draw(canvas);

    for (int a = 0; a != myLittledementors.size(); a++) {
      myLittledementors.get(a).draw(canvas);
    }
    for (int a = 0; a != myBlasts.size(); a++) {
      myBlasts.get(a).draw(canvas);
    }
  }

  /**
   * Updates the dementors in myLittleDementors; if a dementor has reached the bottom of a screen or
   * has been hit with a blast removes the dementor and if not, moves the dementor.
   */
  void updateDementor() {
    if (myLittledementors.size() > 0) {
      ArrayList<Integer> dementors = new ArrayList<>();
      // Get the y coordinate of the dementor that is positioned lowest on the screen.
      int y = myLittledementors.get(0).getY();
      int size = myLittledementors.size();
      // check if the bottommost dementor is at the bottom of the screen. If it is, remove all
      // dementors that are in the same row as this dementor.
      if (y + 4 >= gridHeight - 10) {
        for (int i = 0; i < size; i++) {
          if (y == myLittledementors.get(i).getY()) {
            dementors.add(i);
          }
        }
        for (int j = 0; j < dementors.size(); j++) {
          myLittledementors.remove(0);
        }
      }
      // check if more dementors need to be created
      if (myLittledementors.get(0).getY() >= 5) {
        createDementors();
      }
      ArrayList<Dementor> killeddementors = new ArrayList<>();
      for (int i = 0; i < myBlasts.size(); i++) {
        for (int j = 0; j < myLittledementors.size(); j++) {
          if (myBlasts.get(i).getX() == myLittledementors.get(j).getX()
              && myBlasts.get(i).getY() == myLittledementors.get(j).getY()) {
            killeddementors.add(myLittledementors.get(j));
          }
        }
        for (int k = 0; k < killeddementors.size(); k++) {
          myLittledementors.remove(killeddementors.get(k));
        }
      }
      for (int i = 0; i < myLittledementors.size(); i++) {
        myLittledementors.get(i).move();
      }
    }
  }

  /** Updates wand by moving it. */
  void updateWand() {
    wand.move(this);
  }

  /** Updates the blasts in myBlasts by moving them. */
  void updateBlasts() {
    for (int i = 0; i < myBlasts.size(); i++) {
      myBlasts.get(i).move();
    }
  }

  /** Creates dementors and stores them in myLittleDementors. */
  void createDementors() {
    int i = myLittledementors.size() + 1;
    if (cnt < 5) {
      if (cnt == 3 || cnt == 4) {
        Dementor d = new Dementor(i * 5 + 1, 0);
        this.myLittledementors.add(d);

      } else {
        Dementor d = new Dementor(i * 5, 0);
        this.myLittledementors.add(d);
      }
    }
    cnt += 1;
  }

  /** Creates blasts and stores them in myBlasts. */
  void createBlast() {
    wand.shoot(this);
  }

  /** moves wand to the right. */
  void moveWandRight() {
    if (!wand.getDirection()) {
      wand.moveRight(this);
    }
  }

  /** moves wand to the left. */
  void moveWandLeft() {
    if (wand.getDirection()) {
      wand.moveLeft(this);
    }
  }
}
