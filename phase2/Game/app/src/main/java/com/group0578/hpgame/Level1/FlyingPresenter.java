package com.group0578.hpgame.Level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.group0578.hpgame.model.SQLiteHelper;

import java.util.ArrayList;

public class FlyingPresenter {

    private FlyingView flyingView;
    private FlyingInteractor flyingInteractor;


    private int collected, lives, goal;

    private Paint background = new Paint();
    private Paint scorePaint = new Paint();

    private ArrayList<FlyingBall> items = new ArrayList<>();
    private PlayerBall playerBall;

    FlyingPresenter(FlyingView flyingView, FlyingInteractor flyingInteractor, SQLiteHelper sqlHelper, String username) {
        this.flyingView = flyingView;
        this.flyingInteractor = flyingInteractor;

        background.setARGB(255, 0, 191, 230);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);

        collected = 0;
//        System.out.println("--------------");
//        System.out.println(sqlHelper.findLives(username));
//        System.out.println("--------------");
        lives = sqlHelper.findLives(username);
        goal = 10;

        playerBall = new PlayerBall(1);
        PointBall pointBall = new PointBall(1);
        DeathBall deathBall = new DeathBall(1);

        items.add(pointBall);
        items.add(deathBall);
        items.add(playerBall);
    }

    public void updateGameState(int width, int height) {
        for (FlyingBall item : items) {
            item.update(width, height);
            if (collisionChecker(playerBall, item)) {
                if (item instanceof PointBall) {
                    collected = collected + 1;
                    item.x = -100;
                } else if (item instanceof DeathBall) {
                    lives--;
                    item.x = -100;
                }
            }
        }
    }

    public void draw(Canvas canvas) {
        for (FlyingBall item : items) {
            item.draw(canvas);
        }
    }

    boolean collisionChecker(FlyingBall character, FlyingBall ball) {
        return (character.getX() < ball.getX() &&
                ball.getX() < (character.getX() + character.getRadius()) &&
                character.getY() < ball.getY() && ball.getY() < (character.getY() + character.getRadius()));
    }


    public void setCharSpeed(int charSpeed) {
        this.playerBall.speed = charSpeed;
    }

    public int getCollected() {
        return collected;
    }

    public int getGoal() {
        return goal;
    }

    public int getLives() {
        return lives;
    }

    public Paint getBackground() {
        return background;
    }

    public Paint getScorePaint() {
        return scorePaint;
    }
}
