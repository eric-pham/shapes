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

    /**
     * Getter for this item's first coordinate.
     *
     * @return This item's first coordinate.
     */
    int getX() {
        return x;
    }

    /**
     * Getter for this item's second coordinate.
     *
     * @return This item's second coordinate.
     */
    int getY() {
        return y;
    }

    /**
     * Setter for this item's first coordinate.
     *
     * @param x This item's new first coordinate.
     */
    void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for this item's second coordinate.
     *
     * @param y This item's new second coordinate.
     */
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
