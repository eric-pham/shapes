package com.group0578.hpgame.Level1;

import android.graphics.Color;
import android.graphics.ColorSpace;

public class DeathBall extends FlyingBall {

    DeathBall(String theme){
        super();
        this.radius = 30;
        this.speed = 20;
        if (theme.equalsIgnoreCase("Light"))
            paint.setColor(Color.GREEN);
        else
            paint.setColor(Color.CYAN);
    }

}
