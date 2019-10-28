package com.group0578.hpgame.Level1;

import android.graphics.Canvas;

import java.util.ArrayList;

public class SkyManager {
    /** All the locations where a fish can be. */
    public ArrayList<SkyItem> skyItems;
    /** The width of myLittleFishes. */
    private int gridWidth;
    /** The height of myLittleFishes. */
    private int gridHeight;

    SkyManager(int height, int width) {
        gridHeight = height;
        gridWidth = width;
        skyItems = new ArrayList<>();
    }

    void draw(Canvas canvas) {
        for (SkyItem skyItem : skyItems) {
            skyItem.draw(canvas);
        }
    }

    void update(){
        for (SkyItem skyItem: skyItems){
            skyItem.update();
        }
    }

    void createSkyItems(){
        for (int i = 0; i < 10; i++){
            skyItems.add(new Hoops(gridWidth,gridHeight));
        }
    }
}
