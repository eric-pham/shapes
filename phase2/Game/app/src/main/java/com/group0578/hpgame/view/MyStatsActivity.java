package com.group0578.hpgame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;

public class MyStatsActivity extends AppCompatActivity {

    /**
     * The username of the user who is logged in and currently viewing the scoreboard.
     */
    private String username;

    /**
     * The SQLiteHelper class reads the database allowing this activity to access the user's info
     */
    private SQLiteHelper sqlHelper = new SQLiteHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("My Best Records");
        setContentView(R.layout.activity_my_stats);

        // extracts the information that was passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            this.username = extras.getString("username");
        }

        displayUserStats();
        setComponentColours();
    }

    /**
     * Displays the users best records.
     */
    private void displayUserStats() {
        ((TextView) findViewById(R.id.bestTotalValue)).setText(String.format("%s seconds", sqlHelper.findTotalTime(username)));
        ((TextView) findViewById(R.id.bestAverageValue)).setText(String.format("%s seconds", sqlHelper.findAvgTime(username)));
        ((TextView) findViewById(R.id.highScoreValue)).setText(String.format("%s points", sqlHelper.findScore(username)));
    }

    /**
     * Sets the background and text colours of the Scoreboard based on the light/dark colour
     * scheme selected by the user currently logged in.
     */
    private void setComponentColours() {
        String colourScheme = sqlHelper.findColourScheme(username);
        if (colourScheme.equalsIgnoreCase("Light")) {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 188, 231, 232));
            ((TextView) findViewById(R.id.myStatsTitle)).setTextColor(Color.argb(255, 78, 0, 94));
            ((TextView) findViewById(R.id.bestTotal)).setTextColor(Color.argb(255, 34, 78, 117));
            ((TextView) findViewById(R.id.bestTotalValue)).setTextColor(Color.argb(255, 76, 61, 117));
            ((TextView) findViewById(R.id.bestAverage)).setTextColor(Color.argb(255, 34, 78, 117));
            ((TextView) findViewById(R.id.bestAverageValue)).setTextColor(Color.argb(255, 34, 78, 117));
            ((TextView) findViewById(R.id.highScore)).setTextColor(Color.argb(255, 34, 78, 117));
            ((TextView) findViewById(R.id.highScoreValue)).setTextColor(Color.argb(255, 34, 78, 117));
        } else { // colour scheme is 'Dark'
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 50, 31, 87));
            ((TextView) findViewById(R.id.myStatsTitle)).setTextColor(Color.argb(255, 219, 184, 200));
            ((TextView) findViewById(R.id.bestTotal)).setTextColor(Color.argb(255, 207, 255, 204));
            ((TextView) findViewById(R.id.bestTotalValue)).setTextColor(Color.argb(255, 180, 219, 219));
            ((TextView) findViewById(R.id.bestAverage)).setTextColor(Color.argb(255, 207, 255, 204));
            ((TextView) findViewById(R.id.bestAverageValue)).setTextColor(Color.argb(255, 180, 219, 219));
            ((TextView) findViewById(R.id.highScore)).setTextColor(Color.argb(255, 207, 255, 204));
            ((TextView) findViewById(R.id.highScoreValue)).setTextColor(Color.argb(255, 180, 219, 219));
        }
    }

    /**
     * Pressing this button will bring you to the ProfilePageActivity.
     */
    public void onClickProfileReturn(View view) {
        Intent profile = new Intent(this, ProfilePageActivity.class);
        profile.putExtra("username", this.username);
        startActivity(profile);
        finish();
    }

    /**
     * Pressing this button will bring you to the ScoreboardActivity.
     */
    public void onClickScoreboardReturn(View view) {
        Intent scoreboard = new Intent(this, ScoreboardActivity.class);
        scoreboard.putExtra("username", this.username);
        startActivity(scoreboard);
        finish();
    }
}
