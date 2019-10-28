package com.group0578.hpgame.Level1;

import android.graphics.Color;
import android.graphics.Typeface;

public class Character extends SkyItem {

    Character(){
        super();
        this.appearance = "CHARACTER";
        paintText.setColor(Color.RED);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    void moveUp(){
        setLocation(this.getX(),this.getY() + 40);
    }

}
