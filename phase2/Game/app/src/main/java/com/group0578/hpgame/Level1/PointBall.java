package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;

public class PointBall extends FlyingBall {

    PointBall(int theme){
        super();
        this.radius = 20;
        this.speed = 16;
        if (theme == 1)
            paint.setColor(Color.WHITE);
        else
            paint.setColor(Color.BLACK);
    }
}
