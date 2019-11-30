package com.group0578.hpgame.level1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.Timer;
import com.group0578.hpgame.view.GameOverActivity;

/**
 * Handles visual appearance of the Flying Game
 */
class FlyingView extends View {
    /**
     * Initialize a Timer for the level
     */
    private Timer timer = new Timer();

    /**
     * Presenter for the level
     */
    private FlyingPresenter flyingPresenter;

    /**
     * Store the username String to pass to next level later
     */
    private String username;

    /**
     *
     */
    private FlyingInteractor flyingInteractor;

    /**
     * Initialize a new FlyingView
     *
     * @param context   context
     * @param sqlHelper required to help pull data from the database
     * @param username  required to pull database data based on username
     */
    public FlyingView(Context context, SQLiteHelper sqlHelper, String username) {
        super(context);
        timer.start();
        this.username = username;
        this.flyingInteractor = new FlyingInteractor(sqlHelper, username);
        flyingPresenter = new FlyingPresenter(this, this.flyingInteractor);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(flyingPresenter.getBackground());

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        flyingPresenter.updateGameState(canvasWidth, canvasHeight);
        flyingPresenter.draw(canvas);

        canvas.drawText("Collected : " + flyingPresenter.getCollected(), 0, 60, flyingPresenter.getScorePaint());
        canvas.drawText("Goal : " + flyingPresenter.getGoal(), (canvasWidth / 3) + 70, 60, flyingPresenter.getScorePaint());
        canvas.drawText("Lives : " + flyingPresenter.getLives(), (canvasWidth / 3) * 2 + 50, 60, flyingPresenter.getScorePaint());
        canvas.drawText(timer.getSecondsPassedString(), 0, 120, flyingPresenter.getScorePaint());

        if (flyingPresenter.getLives() == 0)
            goToGameOver();
        if (flyingPresenter.getCollected() == 10 || flyingPresenter.getBonus() == 1)
            goToTransition();


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            flyingPresenter.setCharSpeed(-22);
        }
        return true;
    }

    /**
     * Lost all lives, transition to GameOver
     */
    public void goToGameOver() {
        System.out.println("goToGameOver reached");

        //Update the database with default values
        flyingInteractor.updateDatabase(0, 0.0, "none");


        Intent gameOver = new Intent(getContext(), GameOverActivity.class);
        gameOver.putExtra("username", this.username);
        gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(gameOver);
    }

    /**
     * Goal reached transition to next level
     */
    public void goToTransition() {
        System.out.println("Transition reached");

        //Update the database with new data for lives and time
        flyingInteractor.updateDatabase(flyingPresenter.getLives(), timer.getSecondsPassed(), "one");

        Intent transition = new Intent(getContext(), FlyingTransitionActivity.class);
        transition.putExtra("username", this.username);
        transition.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(transition);
    }
}
