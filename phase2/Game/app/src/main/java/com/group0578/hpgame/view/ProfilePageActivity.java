package com.group0578.hpgame.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.ProfilePagePresenter;

import java.util.Calendar;

public class ProfilePageActivity extends AppCompatActivity implements ProfilePage.View {

  /**
   * The presenter associated with this View that handles the user's interactions with the UI.
   */
  ProfilePagePresenter profilePagePresenter;

  private String username;

  /**
   * Called immediately when the activity is started.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile_page);
    profilePagePresenter = new ProfilePagePresenter(this);

    // extracts the information that was passed from the previous activity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      this.username = extras.getString("username");
    }

    // display the user's name
    final TextView usernameTextView = findViewById(R.id.usernameTextView);
    usernameTextView.setText(String.format("User: %s", username));

    // displays the date
    final TextView dateTextView = findViewById(R.id.date);
    dateTextView.setText(String.format("Date: %s/%s/%s", Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR));
  }

  /**
   * Starts the game by calling the method that will create the intent for the first level.
   *
   * @param view the view
   */
  public void onClickPlayGame(View view) {
    profilePagePresenter.createLevel1();
  }

  /**
   * Starts the Level1Activity.
   *
   * @param levelOne the intent for the Level1Activity
   */
  public void goToLevel1(Intent levelOne) {
    levelOne.putExtra("username", this.username);
    startActivity(levelOne);
  }

}
