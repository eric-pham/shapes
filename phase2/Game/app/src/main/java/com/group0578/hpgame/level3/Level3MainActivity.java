package com.group0578.hpgame.level3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.TextView;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.view.ProfilePageActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Level3MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private static final long Interval = 30;

    /**
     * The view of the level 3 game.
     */
    private Level3ScreenView screenView;

    /**
     * The username belonging to the user currently logged in and viewing the profile page.
     */
    private String username;

    /**
     * The sql database helper that has methods that can operate on the database.
     */
    private SQLiteHelper sqlHelper = new SQLiteHelper(this);

    /**
     * The user playing the game.
     */
    UserLevel3 player;

    /**
     * Called when the activity is started. Creates the game level.
     *
     * @param savedInstanceState The instance state of the game.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.level3);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            this.username = extras.getString("username");
        }
        screenView = new Level3ScreenView(this);
        this.player = new UserLevel3(sqlHelper, username);

        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        screenView.invalidate();
                                    }
                                });
                    }
                },
                0,
                Interval);

        setComponentColours();
        setLevelDifficulty();
    }

    /**
     * Changes the background and text colour depending on the colour scheme.
     */
    private void setComponentColours() {
        String colourScheme = sqlHelper.findColourScheme(username);
        if (colourScheme.equalsIgnoreCase("Light")) {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
            Level3ScreenView.setBackground(255, 204, 212, 255);
            ((TextView) findViewById(R.id.editText)).setTextColor(Color.argb(255, 68, 0, 102));
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 100, 30, 250));
            Level3ScreenView.setBackground(255, 100, 30, 250);
            ((TextView) findViewById(R.id.editText)).setTextColor(Color.argb(255, 255, 179, 204));
        }
    }

    /**
     * Changes the level difficulty depending on the customization.
     */
    private void setLevelDifficulty() {
        String difficulty = sqlHelper.findDifficulty(username);
        if (difficulty.equalsIgnoreCase("Easy")) {
            Dementor.setSpeed(1);
        } else {
            Dementor.setSpeed(3);
        }
    }

    /**
     * onCreate method for the shoot button; shoots a blast from the player.
     *
     * @param view The view on which to create the blast.
     */
    public void createBlast(View view) {
        Manager manager = Level3ScreenView.getRoomManager();
        manager.createBlast();
    }

    /**
     * onCreate method for the right button; sets the players direction of motion to right.
     *
     * @param view The view on which to move the player.
     */
    public void moverPlayerRight(View view) {
        Manager manager = Level3ScreenView.getRoomManager();
        manager.movePlayerRight();
    }

    /**
     * onCreate method for the left button; sets the players direction of motion to left.
     *
     * @param view The view on which to move the player.
     */
    public void moverPlayerLeft(View view) {
        Manager manager = Level3ScreenView.getRoomManager();
        manager.movePlayerLeft();
    }

    /**
     * Getter for the username of user playing the game.
     *
     * @return The username of the user playing the game.
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Getter for the SQLiteHelper object..
     *
     * @return The SQLiteHelper object.
     */
    public SQLiteHelper getSqlHelper() {
        return this.sqlHelper;
    }
    /**
     * Getter for the Player that is currently on level 3.
     *
     * @return The Player that is currently on level 3.
     */
    public UserLevel3 getPlayer() {
        return this.player;
    }

    /**
     * Pressing the back button will bring you to the ProfilePageActivity.
     */
    @Override
    public void onBackPressed() {
        Intent profile = new Intent(this, ProfilePageActivity.class);
        profile.putExtra("username", this.username);
        startActivity(profile);
        finish();
    }


}
