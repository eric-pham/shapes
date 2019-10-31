package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;

public class CharacterSprite extends SkyItem {

    private float width;

    CharacterSprite(){
        super();
        this.appearance = "CHARACTER";
        paintText.setColor(Color.RED);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        int start_x = 100;
        int start_y = 100;
        width = paintText.measureText(appearance);

        setLocation(start_x,start_y);
    }

    public void draw(Canvas canvas){
        canvas.drawText(appearance,getX(), getY(), paintText);
    }

    public void update(int x, int y, float width){
        setLocation(getX(),getY());
    }

    public float getWidth(){
        return this.width;
    }
}
