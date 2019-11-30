package com.group0578.hpgame.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.StatsPresenter;

/**
 * Displays the user statistics screen.
 */
public class StatsActivity extends AppCompatActivity implements Stats.View {

    /**
     * The presenter associated with this View that handles the user's interactions with the UI.
     */
    private StatsPresenter statsPresenter;

    /**
     * The username of the user who is logged in and currently viewing the scoreboard.
     */
    private String username;

    /**
     * The SQLiteHelper class reads the database allowing this activity to display users' scores
     */
    private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

    /**
     * The constructor for the activity that displays the user statistics screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Scoreboard");
        setContentView(R.layout.activity_stats);
        statsPresenter = new StatsPresenter(this);

        // extracts the information that was passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            this.username = extras.getString("username");
        }

        setComponentColours();
        displayPlayerScores();
    }

    /**
     * Sets the background and text colours of the Scoreboard based on the light/dark colour
     * scheme selected by the user currently logged in.
     */
    private void setComponentColours() {
        String colourScheme = sqLiteHelper.findColourScheme(username);
        findViewById(R.id.username).setBackgroundColor(Color.argb(255, 255, 255, 255));
        findViewById(R.id.score).setBackgroundColor(Color.argb(255, 255, 255, 255));
        ((TextView) findViewById(R.id.username)).setTextColor(Color.BLACK);
        ((TextView) findViewById(R.id.score)).setTextColor(Color.BLACK);
        if (colourScheme.equalsIgnoreCase("Light")) {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
            findViewById(R.id.scoreboard).setBackgroundColor(Color.argb(255, 204, 212, 240));
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 51, 153));
            findViewById(R.id.scoreboard).setBackgroundColor(Color.argb(255, 0, 51, 143));
        }
    }

    private void displayPlayerScores() {

    }
}
