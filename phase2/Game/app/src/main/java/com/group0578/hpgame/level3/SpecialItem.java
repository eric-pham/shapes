package com.group0578.hpgame.level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

class SpecialItem extends Items {
  /** How the item appears on the screen. */
  private String appearance;

  /** Paint for this item. */
  private Paint paintText = new Paint();

  /**
   * The constructor for this item.
   *
   * @param x This item's first coordinate.
   * @param y This item's second coordinate.
   */
  SpecialItem(int x, int y) {
    super(x, y);
    this.appearance = "O";
    paintText.setTextSize(50);
    paintText.setColor(Color.GREEN);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
  }

  /** Causes this item to move down on the screen. */
  void move() {
    setY(getY() + 3);
  }

  /**
   * Draws the item in the given graphics context.
   *
   * @param canvas the graphics context in which to draw.
   */
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
        appearance,
        y * Level3ScreenView.getCharWidth(),
        x * Level3ScreenView.getCharHeight(),
        paintText);
  }
}
