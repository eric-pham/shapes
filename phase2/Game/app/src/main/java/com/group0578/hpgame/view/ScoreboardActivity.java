package com.group0578.hpgame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.ScoreboardPresenter;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

/**
 * Displays the user statistics screen.
 */
public class ScoreboardActivity extends AppCompatActivity implements Scoreboard.View {

    /**
     * The presenter associated with this View that handles the user's interactions with the UI.
     */
    private ScoreboardPresenter scoreboardPresenter;

    /**
     * The username of the user who is logged in and currently viewing the scoreboard.
     */
    private String username;

    /**
     * The SQLiteHelper class reads the database allowing this activity to access and display users'
     * info.
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
        scoreboardPresenter = new ScoreboardPresenter(this);

        // extracts the information that was passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            this.username = extras.getString("username");
        }

        setComponentColours();
        displayUsersByScore();
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
            findViewById(R.id.scoreboard).setBackgroundColor(Color.argb(255, 176, 191, 26));
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
     * Pressing this button will bring you to the MyStatsActivity that displays all your best records.
     */
    public void onClickDisplayScoreboard(View view) {
        // needs to be implemented
    }

    /**
     * Pressing this button will bring you to the MyStatsActivity that displays all your best records.
     */
    public void onClickMyStats(View view) {
        Intent myStats = new Intent(this, MyStatsActivity.class);
        myStats.putExtra("username", this.username);
        startActivity(myStats);
    }

    /**
     * Displays the Player Scores on the scoreboard
     */
    private void displayUsersByScore() {
        //Gets the sorted TreeMap
        TreeMap<Integer, String> treeMap = sqLiteHelper.findAllScores();

        //Two ArrayList for username and scores
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> scores = new ArrayList<>();

        //Fills the two arrays, converts the scores to strings
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            if (key > 0) {
                usernames.add(value);
                scores.add(String.valueOf(key));
            }
        }

        Collections.reverse(scores);
        Collections.reverse(usernames);

        //Check if < 20 size, if yes fill in empty spots with blank strings
        if (usernames.size() < 20) {
            int difference = 20 - usernames.size();
            for (int i = 0; i < difference; i++) {
                usernames.add("");
                scores.add("");
            }
        }

//        setScoreBoardValues(usernames, scores);

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

    /**
     * Ranks the users on the scoreboard based on the total time they completed the game.
     */
    private void displayUsersByTotalTime() {
        //Gets the sorted TreeMap
        TreeMap<Double, String> treeMap = sqLiteHelper.findAllTotalTimes();

        //Two ArrayList for username and total times
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> totalTimes = new ArrayList<>();

        //Fills the two arrays, converts the total time to a String
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            Double key = entry.getKey();
            String value = entry.getValue();

            if (key > 0) {
                usernames.add(value);
                totalTimes.add(String.valueOf(key));
            }
        }

//        Collections.reverse(totalTimes);
//        Collections.reverse(usernames);

        //Check if < 20 size, if yes fill in empty spots with blank strings
        if (usernames.size() < 20) {
            int difference = 20 - usernames.size();
            for (int i = 0; i < difference; i++) {
                usernames.add("");
                totalTimes.add("");
            }
        }

        setScoreBoardValues(usernames, totalTimes);
    }

    /**
     * Ranks the users on the scoreboard based on the average time they completed the game.
     */
    private void displayUsersByAvgTime() {
        //Gets the sorted TreeMap
        TreeMap<Double, String> treeMap = sqLiteHelper.findAllAvgTimes();

        //Two ArrayList for username and avg times
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> avgTimes = new ArrayList<>();

        //Fills the two arrays, converts the avg time to a String
        for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
            Double key = entry.getKey();
            String value = entry.getValue();

            if (key > 0) {
                usernames.add(value);
                avgTimes.add(String.valueOf(key));
            }
        }
        // we want times from least to greatest
//        Collections.reverse(avgTimes);
//        Collections.reverse(usernames);

        //Check if < 20 size, if yes fill in empty spots with blank strings
        if (usernames.size() < 20) {
            int difference = 20 - usernames.size();
            for (int i = 0; i < difference; i++) {
                usernames.add("");
                avgTimes.add("");
            }
        }

        setScoreBoardValues(usernames, avgTimes);
    }


    // still working on this, produces errors
    /**
     * Sets the text of all the TextViews making up the scoreboard.
     * @param usernames the array list containing all the users to be displayed on the scoreboard.
     * @param values the array list containing the values by which the users are being ranked
     */
    private void setScoreBoardValues(ArrayList<String> usernames, ArrayList<String> values) {
        //Sets all TextViews accordingly
        TableLayout scoreTable = findViewById(R.id.scoreboard);
        for (int i = 1; i < scoreTable.getChildCount() - 1; i++) {
            TableRow row = (TableRow) scoreTable.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                TextView cell = (TextView) row.getChildAt(j);
                cell.setText(usernames.get(i));
                cell.setText(values.get(i));
            }
        }
    }
}
