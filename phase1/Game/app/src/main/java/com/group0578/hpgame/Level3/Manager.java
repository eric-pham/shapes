package com.group0578.hpgame.Level3;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Manager {
    /**
     * List of all dementors.
     */
    private ArrayList<Dementor> myLittledementors;
    private ArrayList<Blast> myBlasts;
    private Wand wand;
    private int gridWidth;
    private int gridHeight;

    Manager(int width, int height) {
        gridWidth = width;
        gridHeight = height;
        myLittledementors = new ArrayList<>();
        myBlasts = new ArrayList<>();
        wand = new Wand(gridWidth / 2, gridHeight - 10);
    }

    ArrayList<Dementor> getMyLittledementors() {
        return myLittledementors;
    }

    ArrayList<Blast> getMyBlasts() {
        return myBlasts;
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
        System.out.println("Paul Gries");
        wand.draw(canvas);

        for (int a = 0; a != myLittledementors.size(); a++) {
            myLittledementors.get(a).draw(canvas);
        }
        for (int a = 0; a != myBlasts.size(); a++) {
            myBlasts.get(a).draw(canvas);
        }
    }

    void updateDementor() {
        int size = myLittledementors.size();
        for (int i = 0; i < myLittledementors.size(); i++) {
            for (int j = 0; j < myBlasts.size(); j++) {
                if(myBlasts.get(i).getX() == myLittledementors.get(i).getColumn() &&
                        myBlasts.get(i).getY() == myLittledementors.get(i).getRow()) {
                    myLittledementors.remove(i);
                }
            }
            if (myLittledementors.get(i).getRow() + 4 >= gridHeight - 10) {
                if (size == 7){
                    for (int j = 0; j < 1; j++){
                        myLittledementors.remove(0);
                    }
                }
                else if (size == 6){
                    for(int j = 0; j < 2; j++){
                        myLittledementors.remove(0);
                    }
                }
                else{
                    myLittledementors.clear();
                }
            }
            else {
                if (myLittledementors.get(i).getRow() >= 4) {
                    createDementors();
                }
                myLittledementors.get(i).move();
            }
        }
    }

    void updateWand() {
        wand.move(this);
    }

    void updateBlasts() {
        for (int i = 0; i < myBlasts.size(); i++) {
            myBlasts.get(i).move();
        }
    }

    void createDementors() {
        int i = myLittledementors.size();
        if (i<10) {
            for (int j = 1; j <= i + 1; j++) {
                Dementor d = new Dementor(gridWidth * j / (i + 2), 0);
                this.myLittledementors.add(d);
            }
        }

    }

    void createBlast() {
        wand.shoot(this);
    }

    void moveWandRight() {
        if (!wand.getDirection()){
            wand.moveRight(this);
        }
    }

    void moveWandLeft() {
        if(wand.getDirection()){
            wand.moveLeft(this);
        }
    }


}

