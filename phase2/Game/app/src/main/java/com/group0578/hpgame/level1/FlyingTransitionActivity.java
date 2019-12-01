package com.group0578.hpgame.level1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group0578.hpgame.level2.MazeActivity;
import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;

/** Helps facilitate transition to the next level */
public class FlyingTransitionActivity extends AppCompatActivity {

  /** The sql database helper that has methods that can operate on the database. */
  private SQLiteHelper sqlHelper = new SQLiteHelper(this);

  /** The username belonging to the user currently logged in and viewing the profile page. */
  private String username;

  /** Called when the activity is started */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_flying_transition);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      // the username of the user currently logged in
      this.username = extras.getString("username");
    }
  }

  /**
   * Proceeds to the next level passing in required information
   *
   * @param view View for next level screen
   */
  public void onClickNextLevel(View view) {
    // Calling MazeActivity class
    Intent nextLevelIntent = new Intent(this, MazeActivity.class);
    nextLevelIntent.putExtra("username", this.username);
    startActivity(nextLevelIntent);
    finish();
  }
}
