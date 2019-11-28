package com.group0578.hpgame.Level1;

import android.graphics.Color;

public class BonusBall extends FlyingBall {
    BonusBall(String theme) {
        super();
        this.radius = 30;
        this.speed = 50;
        if (theme.equalsIgnoreCase("Light"))
            paint.setColor(Color.MAGENTA);
        else
            paint.setColor(Color.GRAY);
    }

    public void update(int width, int height) {

        int minCharY = 100;
        int maxCharY = height - 40;

        this.x = this.x - this.speed;

        if (this.x < 0) {
            this.x = width + 21;
            this.y = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
        }

        System.out.println("Bonus Ball");
        System.out.println(this.x);
        System.out.println(this.y);
    }
}
