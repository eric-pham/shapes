package com.group0578.hpgame.Level3;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Manager {

    /** List of all dementors. */
    private ArrayList<Dementor> myLittleDementors;
    /** The wand. */
    private Wand myWand;
    /** The width of myLittleFishes. */
    private int gridWidth;
    /** The height of myLittleFishes. */
    private int gridHeight;



    Manager(int width, int height) {
        myLittleDementors = new ArrayList<>();
        gridHeight = height;
        gridWidth = width;
        myWand = new Wand(width / 2, height);
    }

    /**
     * Return the width of a row of locations.
     *
     * @return the width of a column of locations.
     */
    public int getGridWidth() {
        return gridWidth;
    }

    /**
     * Return the height of a column of locations.
     *
     * @return the height of a column of locations.
     */
    public int getGridHeight() {
        return gridHeight;
    }

    ArrayList<Dementor> getMyLittledementors() {
        return myLittleDementors;
    }

    void draw(Canvas canvas) {
        for (int a = 0; a != myLittleDementors.size(); a++) {
            myLittleDementors.get(a).draw(canvas);
        }
    }

    void update() {
    }

    void createDementor() {
    }
}
