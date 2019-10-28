package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;

public class CharacterSprite extends SkyItem {

    CharacterSprite(){
        super();
        this.appearance = "CHARACTER";
        paintText.setColor(Color.RED);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        int start_x = 100;
        int start_y = 100;

        setLocation(start_x,start_y);
    }

    public void draw(Canvas canvas){
        canvas.drawText(appearance,getX(), getY(), paintText);
    }

    public void update(){
        setLocation(getX(),getY());
    }
}
