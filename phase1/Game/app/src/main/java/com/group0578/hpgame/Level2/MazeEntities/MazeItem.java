package com.group0578.hpgame.Level2.MazeEntities;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class MazeItem {

    String appearance;
    private int x;
    private int y;
    Paint paintText = new Paint();

    MazeItem(){
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
