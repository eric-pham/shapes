package com.group0578.hpgame.level3;

import android.graphics.Canvas;

abstract class Items {

    /**
     * This item's first coordinate. row
     */
    private int x;
    /**
     * This item's second coordinate. col
     */
    private int y;

    /**
     * The constructor for this item.
     *
     * @param x This item's first coordinate.
     * @param y This item's second coordinate.
     */
    Items(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    /**
     * Draws this item.
     *
     * @param canvas the graphics context in which to draw this item.
     */
    abstract void draw(Canvas canvas);
}
