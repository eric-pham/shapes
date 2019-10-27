package com.group0578.hpgame.Level3;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Manager {

    /** List of all tank items. */
    private ArrayList<Dementor> myLittledementors;
    /** The width of myLittleFishes. */
    private int gridWidth;
    /** The height of myLittleFishes. */
    private int gridHeight;


    Manager(int height, int width) {
        gridHeight = height;
        gridWidth = width;
        myLittledementors = new ArrayList<>();
    }

    ArrayList<Dementor> getMyLittledementors() {
        return myLittledementors;
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

    void draw(Canvas canvas) {
        for (int a = 0; a != myLittledementors.size(); a++) {
            myLittledementors.get(a).draw(canvas);
        }
    }

    void update() {
    }

    void createDementor() {
    }
}
