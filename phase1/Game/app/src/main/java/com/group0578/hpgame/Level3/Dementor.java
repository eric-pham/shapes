package com.group0578.hpgame.Level3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import  android.graphics.Color;

public class Dementor {
    private String appearance;
    private int x;
    private int y;
    private boolean isAlive;
    private Paint paintText = new Paint();

    Dementor(){
        this.appearance = "<OO>";
        this.isAlive = true;
        paintText.setTextSize(36);
        paintText.setColor(Color.WHITE);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
    }

    void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getRow(){
        return this.y;
    }

    int getColumn(){
        return this.x;
    }

    void move(){
        this.y += 4;
    }

    void kill(){
        this.isAlive = false;
    }

    void draw(Canvas canvas) {

        drawString(canvas, appearance, getRow(), getColumn());
    }

    void drawString(Canvas canvas, String appearance, int x, int y) {

        canvas.drawText(appearance, y * ScreenView.getCharWidth(),
                x * ScreenView.getCharHeight(), paintText);
    }



}
