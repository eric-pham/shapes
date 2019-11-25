package com.group0578.hpgame.Level1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.group0578.hpgame.R;

import java.util.Timer;
import java.util.TimerTask;

public class FlyingActivity extends AppCompatActivity {

    private FlyingView gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flying_game);
        gameView = new FlyingView(this);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        gameView.invalidate();
                    }
                });
            }
        },0, Interval);
    }
}
