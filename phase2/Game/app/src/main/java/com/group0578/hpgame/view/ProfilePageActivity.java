package com.group0578.hpgame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.ProfilePagePresenter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for handling user interactions (button clicks etc.) with the Profile Page.
 */
public class ProfilePageActivity extends AppCompatActivity implements ProfilePage.View {

  /** The presenter associated with this View that handles the user's interactions with the UI. */
  ProfilePagePresenter profilePagePresenter;

  /** The username belonging to the user currently logged in and viewing the profile page. */
  private String username;

  /** SQL helper associated with this CustomizeActivity */
  private SQLiteHelper sqlHelper = new SQLiteHelper(this);

  /**
   * Request for the colour scheme used in goToCustomizePage(), and overridden onActivityResult()
   */
  final int COLOUR_SCHEME_REQUEST = 1;

    /**
     * The Toast responsible for displaying messages to the user.
     */
    Toast toast;

  /** Called immediately when the activity is started. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile_page);
    setTitle("Profile Page");
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

    // there is a bug that needs to be fixed: pressing back button from customize page doesn't
    // change the colour scheme of the profile page after a new colour scheme is selected
    setComponentColours();
  }

  /** Changes the background and text colour depending on the colour scheme. */
  private void setComponentColours() {
    String colourScheme = sqlHelper.findColourScheme(username);
    if (colourScheme.equalsIgnoreCase("Light")) {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
      ((TextView) findViewById(R.id.date)).setTextColor(Color.argb(255, 68, 0, 102));
      ((TextView) findViewById(R.id.usernameTextView)).setTextColor(Color.argb(255, 68, 0, 102));
    } else {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 51, 153));
      //      ((TextView) findViewById(R.id.level2_congrats_message_textView))
      //              .setTextColor(Color.argb(255, 255, 179, 204));
      ((TextView) findViewById(R.id.date)).setTextColor(Color.argb(255, 239, 222, 205));
      ((TextView) findViewById(R.id.usernameTextView)).setTextColor(Color.argb(255, 239, 222, 205));
    }
  }

  /**
   * Called when the user attempts to hit the android device 'back' button.
   * Prevents users from going back to startup page after signing in.
   */
  @Override
  public void onBackPressed() {
      displayToast("Invalid request. Logout to return to the Startup screen.");
  }

    /**
     * Returns the user to the startup screen, logging them out.
     *
     * @param view the view displaying this activity.
     */
    public void onClickLogout(View view) {
        Intent logout = new Intent(this, StartupActivity.class);
        startActivity(logout);
    }

  /**
   * Starts the game by calling the method that will create the intent for the first level.
   *
   * @param view the view displaying this activity.
   */
  public void onClickPlayGame(View view) {
      //If there exists previous progress, set database values to default except for customization
    if (!sqlHelper.findProgress(username).equalsIgnoreCase("none")) {
        profilePagePresenter.resetDefaults(sqlHelper, username);
    }
    profilePagePresenter.createLevel1();
  }

  /**
   * Called when the user presses the 'Resume Game' button. Resumes the previous game for the
   * current user playing if they exited the app.
   *
   * @param view the view displaying this activity.
   */
  public void onClickResumeGame(View view) {
    String progress = sqlHelper.findProgress(username);
    profilePagePresenter.resumePreviousGame(progress);
  }

  /**
   * Called when the user presses the 'PlayerLevel3 Stats' button. Displays the user's (currently playing)
   * statistics from previous games they have played.
   *
   * @param view the view displaying this activity.
   */
  public void onClickPlayerStats(View view) {
    System.out.println("onClickPlayerStats method reached");
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
     * Starts the FlyingActivity.
     *
     * @param levelOne the intent for the Level1Activity
     */
    public void goToLevel1(Intent levelOne) {
        levelOne.putExtra("username", this.username);
//         toast.cancel();  // causes errors
        startActivity(levelOne);
    }

    /**
     * Starting the activity that begins the level unfinished from the previous game for the
     * user who is currently logged in.
     *
     * @param previousLevel Intent for the unfinished level's main activity
     */
    public void resumePreviousLevel(Intent previousLevel) {
        previousLevel.putExtra("username", this.username);
        startActivity(previousLevel);
    }

  /**
   * Receiving the intent creating the Player Stats Page and starting the activity to move
   * to the new screen.
   *
   * @param playerStatsPage the Intent starting the Player Stats Page.
   */
  public void goToPlayerStatsPage(Intent playerStatsPage) {
    System.out.println("Entering Player Stats Page!");
    playerStatsPage.putExtra("username", this.username);
      playerStatsPage.putExtra("userOnScoreboard", "N/A");
    startActivity(playerStatsPage);
  }

    /**
     * Displays a message when the user attempts to perform an unavailable action.
     *
     * @param message a String representing the message to be displayed.
     */
    @Override
    public void displayToast(String message) {  // still needs to be tested for the longer message.
        // initiate the Toast with context, message and duration for the Toast
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        // set gravity for the Toast.
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        // display the Toast
        toast.show();
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
    startActivityForResult(createCustomizePage, COLOUR_SCHEME_REQUEST);
  }

  /**
   * Called by CustomizeActivity.onBackPressed to update the profile page's colour scheme
   *
   * @param requestCode should equal COLOUR_SCHEME_REPORT instance attribute
   * @param resultCode  if the request was successful
   * @param data        the intent (CustomizeActivity) calling this activity
   */
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == COLOUR_SCHEME_REQUEST) {
      if (resultCode == RESULT_OK) {
        changeColourScheme(data.getExtras());
      }
    }
  }

  /**
   * Called by onActivityResult to update the profile page's colour scheme
   *
   * @param extras the extras sent by CustomizeActivity with the new colour scheme
   */
  private void changeColourScheme(Bundle extras) {
    if (extras != null) {
      String newColour = extras.getString("colourScheme");
      if (!newColour.equals("not changed")) {
        setComponentColours(); // changes this screen's background colours
      }
    }
  }
}
