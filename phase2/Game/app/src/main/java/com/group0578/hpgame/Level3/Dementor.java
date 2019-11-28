package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import android.graphics.Color;

class Dementor extends Items {

  /** How the dementor appears on the screen. */
  private String appearance;

  private Paint paintText = new Paint();

  /**
   * The constructor for this dementor.
   *
   * @param x This dementor's first coordinate.
   * @param y This dementor's second coordinate
   */
  Dementor(int x, int y) {
    super(x, y);
    this.appearance = "X";
    paintText.setTextSize(50);
    paintText.setColor(Color.WHITE);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
  }

  /** Causes this item to move down on the screen. */
  void move() {
    setY(getY() + 1);
  }

  void draw(Canvas canvas) {
    drawString(canvas, appearance, getY(), getX());
  }

  /**
   * Draws the given string in the given graphics context at at the given cursor location.
   *
   * @param canvas the graphics context in which to draw the string.
   * @param appearance the string to draw.
   * @param x the x-coordinate of the string's cursor location.
   * @param y the y-coordinate of the string's cursor location.
   */
  private void drawString(Canvas canvas, String appearance, int x, int y) {

    canvas.drawText(
        appearance, y * Level3ScreenView.getCharWidth(), x * Level3ScreenView.getCharHeight(), paintText);
  }
}
