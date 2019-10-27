package com.group0578.hpgame.Level3;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Manager {
    /**
     * List of all tank items.
     */
    private ArrayList<Dementor> myLittledementors;
    private Wand wand;
    private int gridWidth;
    private int gridHeight;
    private ArrayList<Blast> bullets;


    Manager(int width, int height) {
        gridWidth = width;
        gridHeight = height;
        myLittledementors = new ArrayList<>();
        wand = new Wand(gridWidth / 2, gridHeight);
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
        wand.draw(canvas);
        for (int a = 0; a != myLittledementors.size(); a++) {
            myLittledementors.get(a).draw(canvas);
        }
    }

    void updateDementor() {
        for (int i = 0; i < myLittledementors.size(); i++) {
            if (myLittledementors.get(i).getRow() + 4 >= gridHeight) {
                EndGame();
            }
            myLittledementors.get(i).move();
        }
    }

    void createDementors() {
        int i = myLittledementors.size();
        for (int j = 1; j <= i + 1; j++) {
            Dementor d = new Dementor();
            d.setLocation(gridWidth * j / (i + 2), 0);
            this.myLittledementors.add(d);
        }

    }

    void EndGame() {
        // Game over. Display stats.
    }
}

