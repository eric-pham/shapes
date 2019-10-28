package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class SkyItem {
    String appearance;
    private int x;
    private int y;
    Paint paintText = new Paint();

    SkyItem(){
        paintText.setTextSize(36);
    }

    void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getX(){
        return this.x;
    }

    int getY(){
        return this.y;
    }

    public void draw(Canvas canvas){}

    public void update(){}
}
