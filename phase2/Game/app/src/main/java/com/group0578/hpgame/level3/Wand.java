package com.group0578.hpgame.level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

class Wand extends Items {

    /**
     * How the wand appears on the screen.
     */
    private boolean isCircle;

    /**
     * Indicates whether the wand is moving right.
     */
    private boolean goingRight;

    private Paint paint = new Paint();

    /**
     * The constructor for this wand.
     *
     * @param x This wand's first coordinate.
     * @param y This wand's second coordinate
     */
    Wand(int x, int y, String apperance) {
        super(x, y);
        paint.setColor(Color.YELLOW);
        goingRight = true;
        this.isCircle = apperance.equals("Circle");
    }


    /**
     * Changes the direction of the wand's horizontal movement.
     */
    private void turnAround() {
        goingRight = !goingRight;
    }

    void draw(Canvas canvas) {
        if (isCircle) {
            int radius = 25;
            canvas.drawCircle(getX() * Level3ScreenView.getCharWidth(), getY() * Level3ScreenView.getCharHeight(), radius, paint);
        } else {
            paint.setTextSize(60);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText("[]", getX() * Level3ScreenView.getCharWidth(), getY() * Level3ScreenView.getCharHeight(), paint);
        }
    }

    boolean getDirection() {
        return goingRight;
    }

    /**
     * Causes this item to move horizontally on the screen and to turn around
     * if it reached the edge of the screen.
     *
     * @param manager the manager of the screen on which the wand is located.
     */
    void move(Manager manager) {
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
