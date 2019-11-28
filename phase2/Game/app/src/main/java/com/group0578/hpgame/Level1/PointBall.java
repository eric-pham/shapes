package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;

public class PointBall extends FlyingBall {

    PointBall(String theme){
        super();
        this.radius = 20;
        this.speed = 16;
        if (theme.equalsIgnoreCase("Light"))
            paint.setColor(Color.WHITE);
        else
            paint.setColor(Color.BLACK);
    }
}
