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
    private int cnt;

    Manager(int width, int height) {
        gridWidth = width;
        gridHeight = height;
        myLittledementors = new ArrayList<>();
        myBlasts = new ArrayList<>();
        wand = new Wand(gridWidth / 2, gridHeight - 10);
        cnt = 0;

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
        wand.draw(canvas);

        for (int a = 0; a != myLittledementors.size(); a++) {
            myLittledementors.get(a).draw(canvas);
        }
        for (int a = 0; a != myBlasts.size(); a++) {
            myBlasts.get(a).draw(canvas);
        }
    }

    void updateDementor() {
        if (myLittledementors.size() > 0){
            ArrayList<Integer> dementors = new ArrayList<>();
            // Get the y coordinate of the dementor that is positioned lowest on the screen.
            int y = myLittledementors.get(0).getRow();
            int size = myLittledementors.size();
            // check if the bottommost dementor is at the bottom of the screen. If it is, remove all
            // dementors that are in the same row as this dementor.
            if ( y + 4 >= gridHeight - 10){
                for (int i = 0; i < size; i++){
                    if (y == myLittledementors.get(i).getRow()){
                        dementors.add(i);
                    }
                }
                for (int j = 0; j< dementors.size(); j++){
                    myLittledementors.remove(0);
                }
            }
            // check if more dementors need to be created
            if (myLittledementors.get(0).getRow() >= 5){
                createDementors();
            }
            ArrayList<Dementor> killeddementors  = new ArrayList<>();
            for (int i = 0; i < myBlasts.size(); i++) {
                for (int j = 0; j < myLittledementors.size(); j++){
                    if (myBlasts.get(i).getX() == myLittledementors.get(j).getColumn()
                            && myBlasts.get(i).getY() == myLittledementors.get(j).getRow()) {
                        killeddementors.add(myLittledementors.get(j));
                    }
                }
                for (int k = 0; k < killeddementors.size(); k++){
                    myLittledementors.remove(killeddementors.get(k));
                }
            }
            for (int i = 0; i < myLittledementors.size(); i++){
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
    int i = myLittledementors.size() + 1;
    if (cnt < 5) {
      if (cnt == 3 || cnt == 4) {
        Dementor d = new Dementor(i * 5 + 1, 0);
        this.myLittledementors.add(d);

      } else {
        Dementor d = new Dementor(i * 5, 0);
        this.myLittledementors.add(d);
      }
        }
    cnt += 1;
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

