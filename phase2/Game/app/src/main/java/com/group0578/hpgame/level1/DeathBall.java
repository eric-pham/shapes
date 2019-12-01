package com.group0578.hpgame.level1;

import android.graphics.Color;
/**
 * The ball responsible deducting lives
 */
class DeathBall extends FlyingBall {

    /**
     * Constructor that creates the death ball and colour
     */
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
