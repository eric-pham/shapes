package com.group0578.hpgame.level3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

class PlayerObject extends Items {

    /**
     * How the player appears on the screen.
     */
    private boolean isCircle;

    /**
     * Indicates whether the player is moving right.
     */
    private boolean goingRight;

    /**
     * Paint for this player.
     */
    private Paint paint = new Paint();

    /**
     * The constructor for this player.
     *
     * @param x This player's first coordinate.
     * @param y This player's second coordinate
     */
    PlayerObject(int x, int y, String appearance) {
        super(x, y);
        paint.setColor(Color.YELLOW);
        goingRight = true;
        this.isCircle = appearance.equals("Circle");
    }


    /**
     * Changes the direction of the player's horizontal movement.
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

    /**
     * Getter for goingRight.
     *
     * @return whether the player is going right.
     */
    boolean getDirection() {
        return goingRight;
    }

    /**
     * Causes this item to move horizontally on the screen and to turn around
     * if it reached the edge of the screen.
     *
     * @param manager the manager of the screen on which the player is located.
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
     * @param manager the manager of the screen on which the player is located.
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
     * @param manager the manager of the screen on which the player is located.
     */
    void moveLeft(Manager manager) {
        if (!(getX() == 0)) {
            turnAround();
            move(manager);
        }
    }

    /**
     * Causes a an instance of the Blast class to be created at the tip of the player.
     *
     * @param manager the manager of the screen on which the player is located.
     */
    void shoot(Manager manager) {
        Blast b = new Blast(getX(), getY());
        System.out.println(getX() + " " + getY());
        ArrayList<Blast> items = manager.getMyBlasts();
        items.add(b);
    }
}
