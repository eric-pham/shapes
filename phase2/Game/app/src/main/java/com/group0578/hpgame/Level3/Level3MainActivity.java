package com.group0578.hpgame.Level3;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import java.util.Timer;
import java.util.TimerTask;

public class Level3MainActivity extends AppCompatActivity {

  private Handler handler = new Handler();
  private static final long Interval = 30;
  private Level3ScreenView screenView;
  /** The username belonging to the user currently logged in and viewing the profile page. */
  private String username;

  /** The sql database helper that has methods that can operate on the database. */
  private SQLiteHelper sqlHelper = new SQLiteHelper(this);

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
                    if (Level3ScreenView.getRoomManager().getMyLittledementors().isEmpty()) {
                      Button leftButton = findViewById(R.id.button4);
                      leftButton.setVisibility(View.INVISIBLE);
                      Button rightButton = findViewById(R.id.button6);
                      rightButton.setVisibility(View.INVISIBLE);
                      Button shootButton = findViewById(R.id.button5);
                      shootButton.setVisibility(View.INVISIBLE);
                      TextView message = findViewById(R.id.editText);
                      message.setVisibility(View.VISIBLE);
                    }
                  }
                });
          }
        },
        0,
        Interval);

    setComponentColours();
  }

  /** Changes the background and text colour depending on the colour scheme. */
  private void setComponentColours() {
    String colourScheme = sqlHelper.findColourScheme(username);
    if (colourScheme.equalsIgnoreCase("Light")) {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
      Level3ScreenView.setBackground(255, 204, 212, 255);
      // ((TextView) findViewById(R.id.level3_congrats_message_textView))
      // .setTextColor(Color.argb(255, 68, 0, 102));
    } else {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 100, 30, 250));
      Level3ScreenView.setBackground(255, 100, 30, 250);
      // ((TextView) findViewById(R.id.level2_congrats_message_textView))
      //        .setTextColor(Color.argb(255, 255, 179, 204));
      //      ((TextView) findViewById(R.id.level2_congrats_message_textView))
      //              .setTextColor(Color.argb(255, 239, 222, 205));
    }
  }


  public void createBlast(View view) {
    Manager manager = Level3ScreenView.getRoomManager();
    manager.createBlast();
  }

  public void moveWandRight(View view) {
    Manager manager = Level3ScreenView.getRoomManager();
    manager.moveWandRight();
  }

  public void moveWandLeft(View view) {
    Manager manager = Level3ScreenView.getRoomManager();
    manager.moveWandLeft();
  }

  public String getUsername() {
    return this.username;
  }

  public SQLiteHelper getSqlHelper() {
    return this.sqlHelper;
  }
}
