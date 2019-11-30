package com.group0578.hpgame.level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

class Wand extends Items {

  /** How the wand appears on the screen. */
  private String appearance;

  /** Indicates whether the wand is moving right. */
  private boolean goingRight;

  private Paint paintText = new Paint();

  /**
   * The constructor for this wand.
   *
   * @param x This wand's first coordinate.
   * @param y This wand's second coordinate
   */
  Wand(int x, int y, String apperance) {
    super(x, y);
    paintText.setTextSize(60);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    paintText.setColor(Color.YELLOW);
    goingRight = true;
    if (apperance.equals("Circle")){
      this.appearance = "O";
    }
    else {
      this.appearance = "[]";
    }
  }


  /** Changes the direction of the wand's horizontal movement. */
  private void turnAround() {
    goingRight = !goingRight;
  }

  void draw(Canvas canvas) {
    drawString(canvas, appearance, getX(), getY());
  }

  boolean getDirection() {
    return goingRight;
  }
  /**
   * Draws the given string in the given graphics context at at the given cursor location.
   *
   * @param canvas the graphics context in which to draw the string.
   * @param s the string to draw.
   * @param x the x-coordinate of the string's cursor location.
   * @param y the y-coordinate of the string's cursor location.
   */
  private void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, x * Level3ScreenView.getCharWidth(), y * Level3ScreenView.getCharHeight(), paintText);
  }

  /**
   * Causes this item to move horizontally on the screen
   *
   * @param manager the manager of the screen on which the wand is located.
   */
  void move(Manager manager) {
    // Move one spot to the right or left in the direction I'm going. If I bump into a wall,
    // turn around.
    int x = getX();
    if (x == 0 || x == manager.getGridWidth()) {
      turnAround();
    }

    if (goingRight) {
      setX(x + 1);
    } else {
      setX(x - 1);
    }
  }

  /**
   * Causes this item to change its direction to the right. If it's already moving right do nothing.
   *
   * @param manager the manager of the screen on which the wand is located.
   */
  void moveRight(Manager manager) {
    if (!(getX() == manager.getGridWidth())) {
      turnAround();
      move(manager);
    }
  }

  /**
   * Causes this item to change its direction to the left. If it's already moving left do nothing.
   *
   * @param manager the manager of the screen on which the wand is located.
   */
  void moveLeft(Manager manager) {
    if (!(getX() == 0)) {
      turnAround();
      move(manager);
    }
  }

  /**
   * Causes a an instance of the Blast class to be created at the tip of the wand.
   *
   * @param manager the manager of the screen on which the wand is located.
   */
  void shoot(Manager manager) {
    Blast b = new Blast(getX(), getY());
    System.out.println(getX() + " " + getY());
    ArrayList<Blast> items = manager.getMyBlasts();
    items.add(b);
  }
}
