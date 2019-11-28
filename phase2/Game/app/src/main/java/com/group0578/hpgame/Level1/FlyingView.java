package com.group0578.hpgame.Level1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.Timer;

public class FlyingView extends View {
    /**
     * Initialize a Timer for the level
     */
    private Timer timer = new Timer();

    /**
     * Presenter for the level
     */
    private FlyingPresenter flyingPresenter;

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

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            flyingPresenter.setCharSpeed(-22);
        }
        return true;
    }
}
