package com.group0578.hpgame.level1;

import android.graphics.Color;

public class DeathBall extends FlyingBall {

    DeathBall(String theme){
        super();
        this.radius = 30;
        this.speed = 20;
        if (theme.equalsIgnoreCase("Light"))
            paint.setColor(Color.RED);
        else
            paint.setColor(Color.CYAN);
    }

}
