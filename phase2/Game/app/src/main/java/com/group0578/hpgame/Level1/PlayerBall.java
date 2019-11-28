package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;

public class PlayerBall extends FlyingBall {

    private String character;

    PlayerBall(String theme,String character){
        super();
        this.y = 100;
        this.radius = 70;
        this.speed = 0;
        this.character = character;
        if (theme.equalsIgnoreCase("Light"))
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
        if (this.character.equalsIgnoreCase("Circle"))
            canvas.drawCircle(radius,y,radius,paint);
        else
            canvas.drawRect(0,y + radius,140, y -radius,paint);
    }
}
