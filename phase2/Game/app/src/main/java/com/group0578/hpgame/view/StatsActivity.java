package com.group0578.hpgame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.StatsPresenter;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

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

    /**
     * Displays the Player Scores on the scoreboard
     */
    private void displayPlayerScores() {
        //Gets the sorted TreeMap
        TreeMap<Integer, String> treeMap = sqLiteHelper.findAllScores();

        //Two ArrayList for username and scores
        ArrayList<String> usernames = new ArrayList<String>();
        ArrayList<String> scores = new ArrayList<String>();

        //Fills the two arrays, converts the scores to strings
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            usernames.add(value);
            scores.add(String.valueOf(key));
        }

        //Check if < 20 size, if yes fill in empty spots with blank strings
        if (usernames.size() < 20) {
            int difference = 20 - usernames.size();
            for (int i = 0; i < difference; i++) {
                usernames.add("");
                scores.add("");
            }
        }


        //Sets all TextViews accordingly
        ((TextView) findViewById(R.id.user1)).setText(usernames.get(0));
        ((TextView) findViewById(R.id.score1)).setText(scores.get(0));

        ((TextView) findViewById(R.id.user2)).setText(usernames.get(1));
        ((TextView) findViewById(R.id.score2)).setText(scores.get(1));

        ((TextView) findViewById(R.id.user3)).setText(usernames.get(2));
        ((TextView) findViewById(R.id.score3)).setText(scores.get(2));

        ((TextView) findViewById(R.id.user4)).setText(usernames.get(3));
        ((TextView) findViewById(R.id.score4)).setText(scores.get(3));

        ((TextView) findViewById(R.id.user5)).setText(usernames.get(4));
        ((TextView) findViewById(R.id.score5)).setText(scores.get(4));

        ((TextView) findViewById(R.id.user6)).setText(usernames.get(5));
        ((TextView) findViewById(R.id.score6)).setText(scores.get(5));

        ((TextView) findViewById(R.id.user7)).setText(usernames.get(6));
        ((TextView) findViewById(R.id.score7)).setText(scores.get(6));

        ((TextView) findViewById(R.id.user8)).setText(usernames.get(7));
        ((TextView) findViewById(R.id.score8)).setText(scores.get(7));

        ((TextView) findViewById(R.id.user9)).setText(usernames.get(8));
        ((TextView) findViewById(R.id.score9)).setText(scores.get(8));

        ((TextView) findViewById(R.id.user10)).setText(usernames.get(9));
        ((TextView) findViewById(R.id.score10)).setText(scores.get(9));

        ((TextView) findViewById(R.id.user11)).setText(usernames.get(10));
        ((TextView) findViewById(R.id.score11)).setText(scores.get(10));

        ((TextView) findViewById(R.id.user12)).setText(usernames.get(11));
        ((TextView) findViewById(R.id.score12)).setText(scores.get(11));

        ((TextView) findViewById(R.id.user13)).setText(usernames.get(12));
        ((TextView) findViewById(R.id.score13)).setText(scores.get(12));

        ((TextView) findViewById(R.id.user14)).setText(usernames.get(13));
        ((TextView) findViewById(R.id.score14)).setText(scores.get(13));

        ((TextView) findViewById(R.id.user15)).setText(usernames.get(14));
        ((TextView) findViewById(R.id.score15)).setText(scores.get(14));

        ((TextView) findViewById(R.id.user16)).setText(usernames.get(15));
        ((TextView) findViewById(R.id.score16)).setText(scores.get(15));

        ((TextView) findViewById(R.id.user17)).setText(usernames.get(16));
        ((TextView) findViewById(R.id.score17)).setText(scores.get(16));

        ((TextView) findViewById(R.id.user18)).setText(usernames.get(17));
        ((TextView) findViewById(R.id.score18)).setText(scores.get(17));

        ((TextView) findViewById(R.id.user19)).setText(usernames.get(18));
        ((TextView) findViewById(R.id.score19)).setText(scores.get(18));

        ((TextView) findViewById(R.id.user20)).setText(usernames.get(19));
        ((TextView) findViewById(R.id.score20)).setText(scores.get(19));


    }
}
