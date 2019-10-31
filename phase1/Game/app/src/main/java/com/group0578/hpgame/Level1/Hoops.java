package com.group0578.hpgame.Level1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;

public class Hoops extends SkyItem {

    private Bitmap hoop;
    private int velocity;
    private static int num = 0;
    private static int collected = 0;

    Hoops(int screenWidth, int screenHeight) {
        super();
        paintText.setColor(Color.YELLOW);
        velocity = 15;
        num = num + 1;

        int first = (screenHeight / 3);
        int second = (screenHeight / 3) * 2;
        double rand = Math.random();
        if (rand < 0.33) {
            setLocation(screenWidth + 500 * num, first / 2);
        } else if (rand >= 0.33 && rand < 0.66) {
            setLocation(screenWidth + 500 * num, second / 2 + first / 2);
        } else {
            setLocation(screenWidth + 500 * num, screenHeight - first / 2);
        }
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(getX(),getY(),5,paintText);
    }

    public void update(int x, int y) {

        if (this.getX() == x && this.getY() == y) {
            setLocation(0, 0);
            collected++;
        }
        else{
            setLocation(getX() - velocity,getY());
        }
        System.out.println(collected);
    }
}

