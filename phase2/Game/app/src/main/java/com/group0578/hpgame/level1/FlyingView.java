package com.group0578.hpgame.level1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.Timer;
import com.group0578.hpgame.view.GameOverActivity;

public class FlyingView extends View {
    /**
     * Initialize a Timer for the level
     */
    private Timer timer = new Timer();

    /**
     * Presenter for the level
     */
    private FlyingPresenter flyingPresenter;
    private String username;
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
        flyingPresenter = new FlyingPresenter(this, new FlyingInteractor(sqlHelper, username));
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

    public void goToGameOver() {
        System.out.println("goToGameOver reached");
        Intent gameOver = new Intent(getContext(), GameOverActivity.class);
        gameOver.putExtra("username", this.username);
        gameOver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(gameOver);
    }
    public void goToTransition() {
        System.out.println("Transition reached");
        Intent transition = new Intent(getContext(), FlyingTransitionActivity.class);
        transition.putExtra("username", this.username);
        transition.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(transition);
    }
}
