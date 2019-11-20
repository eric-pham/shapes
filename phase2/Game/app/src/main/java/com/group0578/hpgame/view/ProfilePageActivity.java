package com.group0578.hpgame.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.ProfilePagePresenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for handling user interactions (button clicks etc.) with the Profile Page.
 */
public class ProfilePageActivity extends AppCompatActivity implements ProfilePage.View {

  /**
   * The presenter associated with this View that handles the user's interactions with the UI.
   */
  ProfilePagePresenter profilePagePresenter;

  /** The username belonging to the user currently logged in and viewing the profile page. */
  private String username;

  /** Called immediately when the activity is started. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile_page);
    profilePagePresenter = new ProfilePagePresenter(this);

    // extracts the information that was passed from the previous activity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      // the username of the user currently logged in
      this.username = extras.getString("username");
    }

    // display the user's name
    final TextView usernameTextView = findViewById(R.id.usernameTextView);
    usernameTextView.setText(String.format("User: %s", username));

    // displays the date
    final TextView dateTextView = findViewById(R.id.date);
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    dateTextView.setText(String.format("Date: %s", today.format(formatter)));
  }

  /**
   * Called when the user attempts to hit the android device 'back' button
   * Prevents users from going back to startup page after signing in.
   */
  @Override
  public void onBackPressed() {
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

  /**
   * Called when the user presses the 'Resume Game' button. Resumes the previous game for the
   * current user playing if they exited the app.
   *
   * @param view the view displaying this activity.
   */
  public void onClickResumeGame(View view) {
    profilePagePresenter.resumePreviousGame();
  }

  /**
   * Called when the user presses the 'Player Stats' button. Displays the user's (currently playing)
   * statistics from previous games they have played.
   *
   * @param view the view displaying this activity.
   */
  public void onClickPlayerStats(View view) {
    profilePagePresenter.displayPlayerStats();
  }

  /**
   * Called when the user presses the 'Customize' button. Changes the user's customization settings
   * for light/dark colour scheme, level difficulty etc.
   *
   * @param view the view displaying this activity.
   */
  public void onClickCustomize(View view) {
    profilePagePresenter.changeUserCustomization();
  }

  /**
   * Called by the method profilePagePresenter.changeUserCustomization() Starts the new activity to
   * show the customization page.
   *
   * @param createCustomizePage the intent creating the CustomizeActivity class
   */
  @Override
  public void goToCustomizePage(Intent createCustomizePage) {
    createCustomizePage.putExtra("username", this.username);
    startActivity(createCustomizePage);
  }
}
