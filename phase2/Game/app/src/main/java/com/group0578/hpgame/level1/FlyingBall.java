package com.group0578.hpgame.level1;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class FlyingBall {

    int x;
    int y;
    Paint paint = new Paint();
    int radius;
    int speed;

    FlyingBall() {
    }

    int getRadius() {
        return radius;
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

    public void update(int width, int height) {

        int minCharY = 140;
        int maxCharY = height - 70;

        this.x = this.x - this.speed;

        if (this.x < 0) {
            this.x = width + 21;
            this.y = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    boolean collisionChecker(FlyingBall character, FlyingBall ball) {
        return (character.getX() < ball.getX() &&
                ball.getX() < (character.getX() + character.getRadius()) &&
                character.getY() < ball.getY() && ball.getY() < (character.getY() + character.getRadius()));
    }
}
