package com.group0578.hpgame.Level2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.Level3.Level3MainActivity;
import com.group0578.hpgame.R;

/** The startup activity for beginning the level 2 maze of this game. */
public class MazeActivity extends AppCompatActivity implements Maze.View {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);

    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    // Sets this activity's display as activity_maze.xml
    setContentView(R.layout.activity_maze);

    /* Hide the app title bar. */
    getSupportActionBar().hide();
  }

  /**
   * The method called when clicking the enterLevelThree button inside activity_maze.xml
   *
   * @param view the view for the enterLevelThree button
   */
  public void onClickNextLevel(View view) {
    // Calling Level3MainActivity class
    Intent nextLevelIntent = new Intent(this, Level3MainActivity.class);
    startActivity(nextLevelIntent);
    finish();
  }
}
