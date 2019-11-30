package com.group0578.hpgame.level3;

import android.graphics.Canvas;

import java.util.ArrayList;

class Manager {

    /**
     * List of all dementors.
     */
    private ArrayList<Dementor> myLittledementors;
    /**
     * List of all objects.
     */
    private ArrayList<CollectibleObject> objects;
    /**
     * List of all blasts.
     */
    private ArrayList<Blast> myBlasts;
    /**
     * The wand on the screen.
     */
    private Wand wand;
    /**
     * The width of the screen.
     */
    private int gridWidth;
    /**
     * The height of the screen.
     */
    private int gridHeight;
    /**
     * The number of dementors that have been killed.
     */
    private static int numDementorsKilled;
    private PlayerLevel3 player;

    /**
     * The constructor for this manager.
     *
     * @param width  The width of the screen this manager is managing.
     * @param height The height of the screen this manager is managing.
     */
    Manager(int width, int height, PlayerLevel3 player) {
        gridWidth = width;
        gridHeight = height;
        myLittledementors = new ArrayList<>();
        objects = new ArrayList<>();
        myBlasts = new ArrayList<>();
        wand = new Wand(gridWidth / 2, gridHeight - 10);
        numDementorsKilled = 0;
        this.player = player;
    }

    static int getNumDementorsKilled() {
        return numDementorsKilled;
    }

    public ArrayList<CollectibleObject> getObjects() {
        return objects;
    }

    ArrayList<Blast> getMyBlasts() {
        return myBlasts;
    }

    int getGridWidth() {
        return gridWidth;
    }

    /**
     * Draws the wand, the blasts and the dementors created by this manager.
     *
     * @param canvas the graphics context in which to draw this item.
     */
    void draw(Canvas canvas) {
        wand.draw(canvas);

        for (int a = 0; a != myLittledementors.size(); a++) {
            myLittledementors.get(a).draw(canvas);
        }
        for (int a = 0; a != objects.size(); a++) {
            objects.get(a).draw(canvas);
        }
        for (int a = 0; a != myBlasts.size(); a++) {
            myBlasts.get(a).draw(canvas);
        }
    }

    ArrayList<Dementor> getMyLittledementors() {
        return myLittledementors;
    }

    /**
     * Updates the dementors in myLittleDementors; if a dementor has reached the bottom of a screen or
     * has been hit with a blast removes the dementor and if not, moves the dementor.
     */
    void updateDementor() {
        if (myLittledementors.size() > 0) {
            // Get the y coordinate of the dementor that is positioned lowest on the screen.
            int y = myLittledementors.get(0).getY();
            // check if the bottommost dementor is at the bottom of the screen. If it is, remove it.
            if (y >= gridHeight - 15) {
                this.player.reduceLives();
                myLittledementors.remove(0);
            }
            // check if more dementors need to be created
            int size = myLittledementors.size();
            if (myLittledementors.get(size - 1).getY() >= 4) {
                createDementors();
            }
            killDementorbyBlast();
            // move the remaining dementors.
            for (int i = 0; i < myLittledementors.size(); i++) {
                myLittledementors.get(i).move();
            }
        }
    }

    /**
     * Checks if any dementor has been hit by a blast. If it has, remove that dementor from
     * the screen.
     */
    private void killDementorbyBlast() {
        ArrayList<Dementor> killeddementors = new ArrayList<>();
        for (int i = 0; i < myBlasts.size(); i++) {
            for (int j = 0; j < myLittledementors.size(); j++) {
                if (myBlasts.get(i).getX() == myLittledementors.get(j).getX()
                        && myBlasts.get(i).getY() == myLittledementors.get(j).getY()) {
                    killeddementors.add(myLittledementors.get(j));
                }
            }
            for (int k = 0; k < killeddementors.size(); k++) {
                myLittledementors.remove(killeddementors.get(k));
            }
        }
        numDementorsKilled += killeddementors.size();
    }

    /**
     * Updates wand by moving it.
     */
    void updateWand() {
        wand.move(this);
    }

    /**
     * Updates the blasts in myBlasts by moving them.
     */
    void updateBlasts() {
        for (int i = 0; i < myBlasts.size(); i++) {
            myBlasts.get(i).move();
        }
    }

    void updateObjects() {
        if (!objects.isEmpty()) {
            for (int i = 0; i < myBlasts.size(); i++) {
                if (myBlasts.get(i).getX() == objects.get(0).getX()
                        && myBlasts.get(i).getY() == objects.get(0).getY()) {
                    objects.remove(0);
                }
            }
            if (!objects.isEmpty()) {
                objects.get(0).move();
            }
        }
    }

    /**
     * Creates dementors and stores them in myLittleDementors.
     */
    void createDementors() {
        Dementor d = new Dementor((int) (Math.random() * (gridWidth - 2) + 2), 0);
        myLittledementors.add(d);
    }

    void createObjects() {
        CollectibleObject collectibleObject = new CollectibleObject(gridWidth / 2, 0);
        objects.add(collectibleObject);
    }

    /**
     * Creates blasts and stores them in myBlasts.
     */
    void createBlast() {
        wand.shoot(this);
    }

    /**
     * moves wand to the right.
     */
    void moveWandRight() {
        if (!wand.getDirection()) {
            wand.moveRight(this);
        }
    }

    /**
     * moves wand to the left.
     */
    void moveWandLeft() {
        if (wand.getDirection()) {
            wand.moveLeft(this);
        }
    }
}
