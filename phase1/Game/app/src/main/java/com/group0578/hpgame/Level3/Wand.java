package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Wand {

    /** How the wand appears on the screen. */
    private String appearance;

    /** Indicates whether the wand is moving right. */
    private boolean goingRight;

    /** This wand's first coordinate. row*/
    private int x;
    /** This wand's second coordinate. col*/
    private int y;

    Paint paintText = new Paint();

    public Wand(int x, int y) {
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        this.x = x;
        this.y = y;
        paintText.setColor(Color.YELLOW);
        goingRight = true;
        this.appearance = "|";
    }

    /**
     * Draws the given string in the given graphics context at at the given cursor location.
     *
     * @param canvas the graphics context in which to draw the string.
     * @param s the string to draw.
     * @param x the x-coordinate of the string's cursor location.
     * @param y the y-coordinate of the string's cursor location.
     */
    void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, y * RoomView.getCharWidth(), x * RoomView.getCharHeight(), paintText);
    }
}
