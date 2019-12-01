package com.group0578.hpgame.level1;

import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * The parent class for all the "Ball" objects in the first Level.
 */
public abstract class FlyingBall {

    /**
     * The x position of the ball.
     */
    int x;

    /**
     * The y position of the ball.
     */
    int y;

    /**
     * The paint/color of the ball.
     */
    Paint paint = new Paint();

    /**
     * The radius of the ball.
     */
    int radius;

    /**
     * The speed of the ball.
     */
    int speed;

    /**
     * Construct a new instance of FlyingBall, abstract should never be called.
     */
    FlyingBall(){}

    /**
     * Gets radius of ball.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Gets x position of ball.
     */
    public int getX() {
        return x;
    }


    /**
     * Gets x position of ball.
     */
    public int getY() {
        return y;
    }


    /**
     * Updates the the location of the ball
     */
    public void update(int width,int height)
    {

        int minCharY = 140;
        int maxCharY = height - 70;

        this.x = this.x - this.speed;

        if (this.x < 0) {
            this.x = width + 21;
            this.y = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
        }
    }
    /**
     * Abstract draw method to be implemented by each child class
     */
    public void draw(Canvas canvas){
        canvas.drawCircle(x,y,radius,paint);
    }
}
