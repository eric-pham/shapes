package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

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
        paintText.setTextSize(60);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        this.x = x;
        this.y = y;
        paintText.setColor(Color.YELLOW);
        goingRight = true;
        this.appearance = "|";
    }

    private void turnAround() {
        goingRight = !goingRight;
    }

    /**
     * Draws this fish tank item.
     *
     * @param canvas the mazeCanvas on which to draw this item.
     */
    void draw(Canvas canvas) {
        drawString(canvas, appearance, x, y);
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
        canvas.drawText(s, x * ScreenView.getCharWidth(), y * ScreenView.getCharHeight(), paintText);
    }

    /** Causes this item to take its turn in the fish-tank simulation. */
    void move(Manager manager) {
        // Move one spot to the right or left in the direction I'm going. If I bump into a wall,
        // turn around.
        if (x == 0 || x == manager.getGridWidth()) {
            turnAround();
        }

        if (goingRight) {
            x += 1;
        } else {
            x -= 1;
        }
    }

    public void shoot(Manager manager) {
        Blast b = new Blast(x, y);
        System.out.println(x + " " + y);
        ArrayList<Blast> items = manager.getMyBlasts();
        items.add(b);
    }
}
