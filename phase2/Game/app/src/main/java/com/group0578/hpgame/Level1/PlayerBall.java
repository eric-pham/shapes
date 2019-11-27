package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;

public class PlayerBall extends FlyingBall {

    private int theme;

    PlayerBall(int theme){
        super();
        this.y = 100;
        this.radius = 40;
        this.speed = 0;
        this.theme = theme;
        if (theme == 1)
            this.paint.setColor(Color.RED);
        else
            this.paint.setColor(Color.BLACK);
    }

    public void update(int width, int height)
    {
        int minCharY = 100;
        int maxCharY = height - 40;

        this.y = this.y + this.speed;

        if (this.y < minCharY) {
            this.y = minCharY;
        }
        if (this.y > maxCharY) {
            this.y = maxCharY;
        }
        this.speed = this.speed + 2;
    }

    @Override
    public void draw(Canvas canvas) {
            canvas.drawCircle(radius,y,radius,paint);
    }
}
