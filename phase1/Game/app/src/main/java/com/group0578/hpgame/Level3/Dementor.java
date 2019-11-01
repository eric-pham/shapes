package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import android.graphics.Color;

class Dementor {

  /** How the dementor appears on the screen. */
  private String appearance;
  /** This dementor's first coordinate. row */
  private int x;
  /** This dementor's second coordinate. col */
  private int y;

  private Paint paintText = new Paint();

  /**
   * The constructor for this dementor.
   *
   * @param x This dementor's first coordinate.
   * @param y This dementor's second coordinate
   */
  Dementor(int x, int y) {
    this.appearance = "D";
    paintText.setTextSize(36);
    paintText.setColor(Color.WHITE);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    this.x = x;
    this.y = y;
  }

  int getRow() {
    return this.y;
  }

  int getColumn() {
    return this.x;
  }

  /** Causes this item to move down on the screen. */
  void move() {
    this.y += 1;
  }

  /**
   * Draws this dementor.
   *
   * @param canvas the graphics context in which to draw this item.
   */
  void draw(Canvas canvas) {
    drawString(canvas, appearance, getRow(), getColumn());
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
        appearance, y * ScreenView.getCharWidth(), x * ScreenView.getCharHeight(), paintText);
  }
}
