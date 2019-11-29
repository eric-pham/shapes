package com.group0578.hpgame.Level2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.Level3.Level3MainActivity;
import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.view.GameOverActivity;

/** The startup activity for beginning the level 2 maze of this game. */
public class MazeActivity extends AppCompatActivity {

  /**
   * The username belonging to the user currently logged in and viewing the profile page.
   */
  private String username;

  /**
   * The sql database helper that has methods that can operate on the database.
   */
  private SQLiteHelper sqlHelper = new SQLiteHelper(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    getWindow()
            .setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // extracts the information that was passed from the previous activity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      // the username of the user currently logged in
      this.username = extras.getString("username");
    }

    // Sets this activity's display as activity_maze.xml
    setContentView(R.layout.activity_maze);

    setComponentColours();

    /* Hide the app title bar. */
    getSupportActionBar().hide();

      // initiate the Toast with context, message and duration for the Toast
      Toast toast = Toast.makeText(this, "X marks the spot ;)", Toast.LENGTH_LONG);
      // set gravity for the Toast.
      toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
      // display the Toast
      toast.show();
  }

  /**
   * Changes the background and text colour depending on the colour scheme.
   */
  private void setComponentColours() {
    String colourScheme = sqlHelper.findColourScheme(username);
    if (colourScheme.equalsIgnoreCase("Light")) {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
      ((TextView) findViewById(R.id.level2_congrats_message_textView))
              .setTextColor(Color.argb(255, 68, 0, 102));
    } else {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 100, 30, 250));
      ((TextView) findViewById(R.id.level2_congrats_message_textView))
              .setTextColor(Color.argb(255, 255, 179, 204));
      //      ((TextView) findViewById(R.id.level2_congrats_message_textView))
      //              .setTextColor(Color.argb(255, 239, 222, 205));
    }
  }

  /**
   * The method called when clicking the enterLevelThree button inside activity_maze.xml
   *
   * @param view the view for the enterLevelThree button
   */
  public void onClickNextLevel(View view) {
    // Calling Level3MainActivity class
    Intent nextLevelIntent = new Intent(this, Level3MainActivity.class);
    nextLevelIntent.putExtra("username", this.username);
    startActivity(nextLevelIntent);
    finish();
  }

  public String getUsername() {
    return this.username;
  }

  public SQLiteHelper getSqlHelper() {
    return this.sqlHelper;
  }

  /**
   * Starts the GameOverActivity and passes the user's username.
   */
  public void goToGameOver() {
    System.out.println("goToGameOver reached");
    Intent gameOver = new Intent(this, GameOverActivity.class);
    gameOver.putExtra("username", this.username);
    startActivity(gameOver);
    finish();
  }
}
