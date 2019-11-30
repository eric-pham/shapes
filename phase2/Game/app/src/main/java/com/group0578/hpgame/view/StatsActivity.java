package com.group0578.hpgame.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
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
    String username;

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
    }
}
