package com.group0578.hpgame.level1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.group0578.hpgame.model.SQLiteHelper;

import java.util.Timer;
import java.util.TimerTask;

public class FlyingActivity extends AppCompatActivity {

    private FlyingView gameView;
    private Handler handler = new Handler();
    private final static long Interval = 30;

    /**
     * The sql database helper that has methods that can operate on the database.
     */
    private SQLiteHelper sqlHelper = new SQLiteHelper(this);

    /**
     * The username belonging to the user currently logged in and viewing the profile page.
     */
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //

        // extracts the information that was passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            this.username = extras.getString("username");
        }

        gameView = new FlyingView(this, sqlHelper, username);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, Interval);
    }
}
