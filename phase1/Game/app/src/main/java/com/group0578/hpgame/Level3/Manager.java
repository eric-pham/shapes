package com.group0578.hpgame.Level3;

import android.graphics.Canvas;

import java.lang.reflect.Array;
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
        //System.out.println("Paul Gries");
        wand.draw(canvas);

        for (int a = 0; a != myLittledementors.size(); a++) {
            myLittledementors.get(a).draw(canvas);
        }
        for (int a = 0; a != myBlasts.size(); a++) {
            myBlasts.get(a).draw(canvas);
        }
    }

    //void updateDementor() {
      //  int size = myLittledementors.size();
       // for (int i = 0; i < myLittledementors.size(); i++) {
         //   for (int j = 0; j < myBlasts.size(); j++) {
           //     if(myBlasts.get(i).getX() == myLittledementors.get(i).getColumn() &&
             //           myBlasts.get(i).getY() == myLittledementors.get(i).getRow()) {
               //     myLittledementors.remove(i);
               // }
            //}
            //if (myLittledementors.get(i).getRow() + 4 >= gridHeight - 10) {

            //}
            //else {
              //  if (myLittledementors.get(i).getRow() >= 4) {
                //    createDementors();
                //}
                //myLittledementors.get(i).move();
            //}
        //}
    //}

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
            ArrayList<Integer> dementors2  = new ArrayList<>();
            for (int i = 0; i < myBlasts.size(); i++) {
                for (int j = 0; j < myLittledementors.size(); j++){
                    if (myBlasts.get(i).getX() == myLittledementors.get(j).getColumn()
                            && myBlasts.get(i).getY() == myLittledementors.get(j).getRow()) {
                        dementors2.add(j);
                    }
                }
                for(int m = dementors2.size() - 1; m >= 0 ; m--){
                    myLittledementors.remove((int) dementors2.get(m));
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
        //ArrayList<Integer> dementors2  = new ArrayList<>();
        for (int i = 0; i < myBlasts.size(); i++) {
            myBlasts.get(i).move();
        }
    }

  void createDementors() {
    int i = myLittledementors.size() + 1;
    if (cnt < 5) {
      Dementor d = new Dementor(i * 5, 0);
      this.myLittledementors.add(d);
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

