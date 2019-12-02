package com.group0578.hpgame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

/** Displays the user statistics screen. */
public class ScoreboardActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener {

  /** The username of the user who is logged in and currently viewing the scoreboard. */
  private String username;

  /**
   * The SQLiteHelper class reads the database allowing this activity to access and display users'
   * info.
   */
  private SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

  /** The constructor for the activity that displays the user statistics screen. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("Scoreboard");
    setContentView(R.layout.activity_scoreboard);

    // extracts the information that was passed from the previous activity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      // the username of the user currently logged in
      this.username = extras.getString("username");
    }

    Spinner scoreboardSpinner = findViewById(R.id.scoreBoardSpinner);
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            this, R.array.scoreboardFilters, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    scoreboardSpinner.setAdapter(adapter);
    scoreboardSpinner.setOnItemSelectedListener(this);

    setComponentColours();
    displayUsersByScore();
  }

  /**
   * Sets the background and text colours of the Scoreboard based on the light/dark colour scheme
   * selected by the user currently logged in.
   */
  private void setComponentColours() {
    String colourScheme = sqLiteHelper.findColourScheme(username);
    findViewById(R.id.username).setBackgroundColor(Color.argb(255, 255, 255, 255));
    findViewById(R.id.rankBy).setBackgroundColor(Color.argb(255, 255, 255, 255));
    ((TextView) findViewById(R.id.username)).setTextColor(Color.BLACK);
    ((TextView) findViewById(R.id.rankBy)).setTextColor(Color.BLACK);
    if (colourScheme.equalsIgnoreCase("Light")) {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
      findViewById(R.id.scoreboard).setBackgroundColor(Color.argb(255, 176, 191, 26));
    } else {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 51, 153));
      findViewById(R.id.scoreboard).setBackgroundColor(Color.argb(255, 0, 51, 143));
    }
  }

  /** Pressing the back button will bring you to the ProfilePageActivity. */
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
  public void onClickMyStats(View view) {
    Intent myStats = new Intent(this, MyStatsActivity.class);
    myStats.putExtra("username", this.username);
    startActivity(myStats);
  }

  /** Displays the Player Scores on the scoreboard */
  private void displayUsersByScore() {
    // set the title for the column of the scoreboard to 'Score'.
    ((TextView) findViewById(R.id.rankBy)).setText(R.string.title_score);

    // Gets the sorted TreeMap
    TreeMap<Integer, String> treeMap = sqLiteHelper.findAllScores();

    // Two ArrayList for username and scores
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> scores = new ArrayList<>();

    // Fills the two arrays, converts the scores to strings
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

    // Check if < 20 size, if yes fill in empty spots with blank strings
    if (usernames.size() < 20) {
      int difference = 20 - usernames.size();
      for (int i = 0; i < difference; i++) {
        usernames.add("");
        scores.add("");
      }
    }

    setScoreBoardValues(usernames, scores);
  }

  /** Ranks the users on the scoreboard based on the total time they completed the game. */
  private void displayUsersByTotalTime() {
    // set the title for the column of the scoreboard to 'Total Time'.
    ((TextView) findViewById(R.id.rankBy)).setText(R.string.title_total_time);

    // Gets the sorted TreeMap
    TreeMap<Double, String> treeMap = sqLiteHelper.findAllTotalTimes();

    // Two ArrayList for username and total times
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> totalTimes = new ArrayList<>();

    // Fills the two arrays, converts the total time to a String
    for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
      Double key = entry.getKey();
      String value = entry.getValue();

      if (key > 0) {
        usernames.add(value);
        totalTimes.add(String.valueOf(key));
      }
    }

    // Check if < 20 size, if yes fill in empty spots with blank strings
    if (usernames.size() < 20) {
      int difference = 20 - usernames.size();
      for (int i = 0; i < difference; i++) {
        usernames.add("");
        totalTimes.add("");
      }
    }

    setScoreBoardValues(usernames, totalTimes);
  }

  /** Ranks the users on the scoreboard based on the average time they completed the game. */
  private void displayUsersByAvgTime() {
    // set the title for the column of the scoreboard to 'Average Time'.
    ((TextView) findViewById(R.id.rankBy)).setText(R.string.title_avg_time);

    // Gets the sorted TreeMap
    TreeMap<Double, String> treeMap = sqLiteHelper.findAllAvgTimes();

    // Two ArrayList for username and avg times
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> avgTimes = new ArrayList<>();

    // Fills the two arrays, converts the avg time to a String
    for (Map.Entry<Double, String> entry : treeMap.entrySet()) {
      Double key = entry.getKey();
      String value = entry.getValue();

      if (key > 0) {
        usernames.add(value);
        avgTimes.add(String.valueOf(key));
      }
    }

    // Check if < 20 size, if yes fill in empty spots with blank strings
    if (usernames.size() < 20) {
      int difference = 20 - usernames.size();
      for (int i = 0; i < difference; i++) {
        usernames.add("");
        avgTimes.add("");
      }
    }

    setScoreBoardValues(usernames, avgTimes);
  }
  /**
   * Sets the text of all the TextViews making up the scoreboard.
   *
   * @param usernames the array list containing all the users to be displayed on the scoreboard.
   * @param values the array list containing the values by which the users are being ranked
   */
  private void setScoreBoardValues(ArrayList<String> usernames, ArrayList<String> values) {
    // Sets all TextViews accordingly
    TableLayout scoreTable = findViewById(R.id.scoreboard);

    // loops through each table row
    for (int i = 1; i < scoreTable.getChildCount() - 1; i++) {
      TableRow row = (TableRow) scoreTable.getChildAt(i);
      // loops through each cell in the row
      for (int j = 0; j < row.getChildCount(); j++) {
        TextView cell = (TextView) row.getChildAt(j);
        if (j % 2 == 0) {
          cell.setText(usernames.get(i - 1));
        } else {
          cell.setText(values.get(i - 1));
        }
      }
    }
  }

  /**
   * Called when an item in the spinner is selected. Depending on the item selected the scoreboard
   * is ranked by different criteria.
   *
   * @param parent The AdapterView where the selection happened
   * @param view The view within the AdapterView that was clicked
   * @param position The position of the view in the adapter
   * @param id The row id of the item that is selected
   */
  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String selection = parent.getItemAtPosition(position).toString();
    if (selection.equalsIgnoreCase("Score")) {
      displayUsersByScore();
    } else if (selection.equalsIgnoreCase("Total Time")) {
      displayUsersByTotalTime();
    } else if (selection.equalsIgnoreCase("Average Time")) {
      displayUsersByAvgTime();
    }
  }

  /**
   * Callback method to be invoked when the selection disappears from this view. The selection can
   * disappear for instance when touch is activated or when the adapter becomes empty.
   *
   * @param parent The AdapterView that now contains no selected item.
   */
  @Override
  public void onNothingSelected(AdapterView<?> parent) {}
}
