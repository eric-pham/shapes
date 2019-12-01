package com.group0578.hpgame.level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

class Blast extends Items {

    /**
     * How the blast appears on the screen.
     */
    private String appearance;
    /**
     * Paint for this blast.
     */
    private Paint paintText = new Paint();

    /**
     * The constructor for this blast.
     *
     * @param x This blast's first coordinate.
     * @param y This blast's second coordinate
     */
    Blast(int x, int y) {
        super(x, y);
        paintText.setTextSize(50);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setColor(Color.BLACK);
        appearance = "*";
    }

    /**
     * Draws the given string in the given graphics context at at the given cursor location.
     *
     * @param canvas the graphics context in which to draw the string.
     * @param s      the string to draw.
     * @param x      the x-coordinate of the string's cursor location.
     * @param y      the y-coordinate of the string's cursor location.
     */
    private void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, x * Level3ScreenView.getCharWidth(), y * Level3ScreenView.getCharHeight(), paintText);
    }

    /**
     * Draws the blast in the given graphics context.
     *
     * @param canvas the graphics context in which to draw.
     */
    void draw(Canvas canvas) {
        drawString(canvas, appearance, getX(), getY());
    }

    /**
     * Causes this item to move up on the screen.
     */
    void move() {
        setY(getY() - 1);
    }
}
