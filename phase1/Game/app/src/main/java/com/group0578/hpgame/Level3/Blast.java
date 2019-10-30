package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Blast {
    /**
     * How the blast appears on the screen.
     */
    private String appearance;
    /**
     * This blast's first coordinate. row
     */
    private int x;
    /**
     * This blast's second coordinate. col
     */
    private int y;

    Paint paintText = new Paint();

    Blast(int x, int y) {
        this.x = x;
        this.y = y;
        paintText.setTextSize(50);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setColor(Color.BLUE);
        appearance = "*";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Draws the given string in the given graphics context at at the given cursor location.
     *
     * @param canvas the graphics context in which to draw the string.
     * @param s      the string to draw.
     * @param x      the x-coordinate of the string's cursor location.
     * @param y      the y-coordinate of the string's cursor location.
     */
    void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, y * ScreenView.getCharWidth(), x * ScreenView.getCharHeight(), paintText);
    }

    /**
     * Draws this blast.
     *
     * @param canvas the graphics context in which to draw this item.
     */
    void draw(Canvas canvas) {
        drawString(canvas, appearance, x, y);
    }

    void move() {
        y--;
    }
}