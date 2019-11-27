package com.group0578.hpgame.Level1;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.Toast;

import androidx.room.Ignore;

public class FlyingPresenter {

    private FlyingView flyingView;
    private FlyingInteractor flyingInteractor;

    private Paint character = new Paint();
    private int charX = 50;
    private int charY;
    private int charSpeed;

    private int yellowX, yellowY, yellowSpeed = 16;
    private Paint yellowPaint = new Paint();

    private int greenX, greenY, greenSpeed = 20;
    private Paint greenPaint = new Paint();

    private int collected, lives, goal;

    private Paint background = new Paint();
    private Paint scorePaint = new Paint();

    FlyingPresenter(FlyingView flyingView, FlyingInteractor flyingInteractor){
        this.flyingView = flyingView;
        this.flyingInteractor = flyingInteractor;
        character.setColor(Color.RED);

        background.setARGB(255, 0, 191, 230);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);

        yellowPaint.setColor(Color.YELLOW);
        greenPaint.setColor(Color.GREEN);

        collected = 0;
        lives = 3;
        goal = 10;

        charY = 100;
    }

    /*public void setComponents(){
        String difficulty = flyingInteractor.difficulty();
        String theme = flyingInteractor.theme();
        String character = flyingInteractor.character();

        if (difficulty.equalsIgnoreCase("Easy"))
        {
            this.lives = 6;
        }
        else
        {
            this.lives = 3;
        }

        if (theme.equalsIgnoreCase("Light"))
        {
            this.background.setARGB(255, 0, 191, 230);
            this.character.setColor(Color.RED);
            this.yellowPaint.setColor(Color.YELLOW);
            this.greenPaint.setColor(Color.GREEN);
        }
        else
            {
            this.background.setARGB(255, 0, 191, 230);
            this.character.setColor(Color.RED);
            this.yellowPaint.setColor(Color.YELLOW);
            this.greenPaint.setColor(Color.GREEN);
        }

        this.charY = 100;
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        collected = 0;
        goal = 10;
    }*/

    public void updateGreenBall(int width,int height) {

        int minCharY = 100;
        int maxCharY = height - 40;

        greenX = greenX - greenSpeed;

        if (collisionChecker(greenX, greenY)) {
            greenX = -100;
            lives--;

            if (lives == 0) {
//                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
            }
        }

        if (greenX < 0) {
            greenX = width + 21;
            greenY = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
        }
    }

    public void updateYellowBall(int width,int height)
    {

        int minCharY = 100;
        int maxCharY = height - 40;

        yellowX = yellowX - yellowSpeed;

        if (collisionChecker(yellowX, yellowY)) {
            collected = collected + 1;
            yellowX = -100;
        }

        if (yellowX < 0) {
            yellowX = width + 21;
            yellowY = (int) Math.floor(Math.random() * (maxCharY - minCharY) + minCharY);
        }
    }

    public void updatePlayer(int height)
    {
        int minCharY = 100;
        int maxCharY = height - 40;

        charY = charY + charSpeed;

        if (charY < minCharY) {
            charY = minCharY;
        }
        if (charY > maxCharY) {
            charY = maxCharY;
        }
        charSpeed = charSpeed + 2;
    }

    public boolean collisionChecker(int x, int y) {
        return (charX < x && x < (charX + 40) && charY < y && y < (charY + 40));
    }

    public void setCharSpeed(int charSpeed) {
        this.charSpeed = charSpeed;
    }

    public Paint getCharacter() {
        return character;
    }

    public int getCharX() {
        return charX;
    }

    public int getCharY() {
        return charY;
    }

    public int getCharSpeed() {
        return charSpeed;
    }

    public int getCollected() {
        return collected;
    }

    public int getGoal() {
        return goal;
    }

    public int getGreenSpeed() {
        return greenSpeed;
    }

    public int getYellowX() {
        return yellowX;
    }

    public int getGreenX() {
        return greenX;
    }

    public int getGreenY() {
        return greenY;
    }

    public int getYellowSpeed() {
        return yellowSpeed;
    }

    public int getYellowY() {
        return yellowY;
    }

    public int getLives() {
        return lives;
    }

    public Paint getYellowPaint() {
        return yellowPaint;
    }

    public Paint getBackground() {
        return background;
    }

    public Paint getGreenPaint() {
        return greenPaint;
    }

    public Paint getScorePaint() {
        return scorePaint;
    }
}
