package com.group0578.hpgame.Level3;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Manager {

    /** List of all dementors. */
    private ArrayList<Dementor> myLittleDementors;
    /** The wand. */
    private Wand myWand;



    Manager(int width, int height) {
        myLittleDementors = new ArrayList<>();
        myWand = new Wand(width / 2, height);
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
