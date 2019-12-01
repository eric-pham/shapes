package com.group0578.hpgame.level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

/**
 * Handles all the calculations for the game
 */
class FlyingPresenter {

    /**
     * Stores FlyingView, the view for the
     */
    private FlyingView flyingView;

    /**
     * Stores FlyingInteractor, helps facilitate database changes
     */
    private FlyingInteractor flyingInteractor;


    /**
     * Integer variables that keep track of collected objects, lives, goal and bonus
     */
    private int collected, lives, goal, bonus;

    /**
     * Paint for background
     */
    private Paint background = new Paint();

    /**
     * Paint for score
     */
    private Paint scorePaint = new Paint();

    /**
     * Array that stores FlyingBall objects
     */
    private ArrayList<FlyingBall> items = new ArrayList<>();

    /**
     * Store the PlayerBall
     */
    private PlayerBall playerBall;

    /**
     * Create a new FlyingPresenter
     *
     * @param flyingView       View, the display for the game
     * @param flyingInteractor Interactor, helps facilitate database changes
     */
    FlyingPresenter(FlyingView flyingView, FlyingInteractor flyingInteractor) {
        this.flyingView = flyingView;
        this.flyingInteractor = flyingInteractor;

        setComponents();
    }


    void updateGameState(int width, int height) {
        for (FlyingBall item : items) {
            item.update(width, height);
            if (collisionChecker(playerBall, item)) {
                if (item instanceof PointBall) {
                    collected = collected + 1;
                    item.x = -100;
                } else if (item instanceof DeathBall) {
                    lives--;
                    item.x = -100;
                } else if (item instanceof BonusBall) {
                    bonus = bonus + 1;
                    item.x = -100;
                }
            }
        }
    }

    void draw(Canvas canvas) {
        for (FlyingBall item : items) {
            item.draw(canvas);
        }
    }

    /**
     * Draws the legend
     *
     * @param canvas to draw to
     */
    void drawLegend(Canvas canvas, int canvasHeight, int canvasWidth) {
        String theme = flyingInteractor.getTheme();


        PointBall pointBall = new PointBall(theme);
        BonusBall bonusBall = new BonusBall(theme);
        DeathBall deathBall = new DeathBall(theme);

        pointBall.setX(40);
        pointBall.setY(canvasHeight - 40);
        pointBall.draw(canvas);

        bonusBall.setX((canvasWidth / 3) + 20);
        bonusBall.setY(canvasHeight - 40);
        bonusBall.draw(canvas);

        deathBall.setX(canvasWidth - 360);
        deathBall.setY(canvasHeight - 40);
        deathBall.draw(canvas);
    }

    private boolean collisionChecker(FlyingBall character, FlyingBall ball) {
        return (character.getX() < ball.getX() &&
                ball.getX() < (character.getX() + character.getRadius()) &&
                character.getY() < ball.getY() && ball.getY() < (character.getY() + character.getRadius()));
    }

    private void setComponents() {
        // Setting FlyingView background colours based on colour scheme selected by user
        if (this.flyingInteractor.getTheme().equalsIgnoreCase("Light")) {
            background.setARGB(255, 204, 212, 255);
            scorePaint.setColor(Color.BLACK);
        } else {
            //Dark scheme
            background.setARGB(255, 100, 30, 250);
            scorePaint.setColor(Color.WHITE);
        }

        playerBall = new PlayerBall(this.flyingInteractor.getTheme(), this.flyingInteractor.getCharacter());
        PointBall pointBall = new PointBall(this.flyingInteractor.getTheme());
        DeathBall deathBall = new DeathBall(this.flyingInteractor.getTheme());
        BonusBall bonusBall = new BonusBall(this.flyingInteractor.getTheme());

        items.add(bonusBall);
        items.add(pointBall);
        items.add(deathBall);
        items.add(playerBall);

        lives = this.flyingInteractor.getLives();
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);

        collected = 0;
        bonus = 0;
        goal = 10;
    }


    void setCharSpeed(int charSpeed) {
        this.playerBall.speed = charSpeed;
    }

    /**
     * Returns number of collected objects
     *
     * @return collected
     */
    int getCollected() {
        return collected;
    }

    /**
     * Sets required goal
     *
     * @return goal
     */
    int getGoal() {
        return goal;
    }

    /**
     * Returns number of lives
     *
     * @return lives
     */
    int getLives() {
        return lives;
    }

    /**
     * Returns the background Paint
     *
     * @return background
     */
    Paint getBackground() {
        return background;
    }

    /**
     * Returns the score Paint
     *
     * @return scorePaint
     */
    Paint getScorePaint() {
        return scorePaint;
    }

    /**
     * Returns the bonus
     *
     * @return bonus
     */
    int getBonus() {
        return bonus;
    }
}
