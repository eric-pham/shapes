package com.group0578.hpgame.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.CustomizePresenter;

/**
 * Responsible for handling user actions like button clicks on the Customization page
 */
public class CustomizeActivity extends AppCompatActivity implements Customize.View {

  /** The presenter associated with this View that handles the user's interactions with the UI. */
  CustomizePresenter customizePresenter;

  /** The username belonging to the user currently logged in and viewing the profile page. */
  private String username;

  /** SQL helper associated with this CustomizeActivity */
  private SQLiteHelper sqlHelper = new SQLiteHelper(this);

  /**
   * Boolean specifying whether the colour scheme has been changed by selected light/dark buttons
   */
  private boolean colourSchemeChanged = false;

  /** Called immediately when the activity is started. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customize);
    setTitle("Customization");
    // Presenter stores reference of this CustomizeActivity as its View (send 'this')
    customizePresenter = new CustomizePresenter(this);

    // extracts the information that was passed from the previous activity
    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      // the username of the user currently logged in
      this.username = extras.getString("username");
    }

    setButtonsClicked();
    setComponentColours();
  }

  /**
   * Checks what the colour scheme is for the user currently logged in, and checks the light and
   * dark radio buttons accordingly.
   */
  private void setButtonsClicked() {
    String colourScheme = sqlHelper.findColourScheme(username);
    String difficulty = sqlHelper.findDifficulty(username);
    String character = sqlHelper.findCharacter(username);

    if (colourScheme.equalsIgnoreCase("Light")) {
      ((RadioButton) findViewById(R.id.lightButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.darkButton)).setChecked(false);
    } else {
      ((RadioButton) findViewById(R.id.darkButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.lightButton)).setChecked(false);
    }

    if (difficulty.equalsIgnoreCase("Easy")) {
      ((RadioButton) findViewById(R.id.easyButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.hardButton)).setChecked(false);
    } else {
      ((RadioButton) findViewById(R.id.hardButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.easyButton)).setChecked(false);
    }

    if (character.equalsIgnoreCase("A")) {
      ((RadioButton) findViewById(R.id.charAButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.charBButton)).setChecked(false);
    } else {
      ((RadioButton) findViewById(R.id.charBButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.charAButton)).setChecked(false);
    }
  }

  /**
   * Changes the background and text colour depending on the colour scheme.
   */
  private void setComponentColours() {
    String colourScheme = sqlHelper.findColourScheme(username);
    if (colourScheme.equalsIgnoreCase("Light")) {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
      ((TextView) findViewById(R.id.chooseColourSchemeText))
              .setTextColor(Color.argb(255, 68, 0, 102));
      ((TextView) findViewById(R.id.chooseDifficultyText))
              .setTextColor(Color.argb(255, 68, 0, 102));
      ((TextView) findViewById(R.id.chooseCharacterText)).setTextColor(Color.argb(255, 68, 0, 102));
    } else {
      getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 51, 153));
      //      ((TextView) findViewById(R.id.level2_congrats_message_textView))
      //              .setTextColor(Color.argb(255, 255, 179, 204));
      ((TextView) findViewById(R.id.chooseColourSchemeText))
              .setTextColor(Color.argb(255, 239, 222, 205));
      ((TextView) findViewById(R.id.chooseDifficultyText))
              .setTextColor(Color.argb(255, 239, 222, 205));
      ((TextView) findViewById(R.id.chooseCharacterText))
              .setTextColor(Color.argb(255, 239, 222, 205));
      ((RadioButton) findViewById(R.id.lightButton)).setTextColor(Color.WHITE);
      ((RadioButton) findViewById(R.id.darkButton)).setTextColor(Color.WHITE);
      ((RadioButton) findViewById(R.id.easyButton)).setTextColor(Color.WHITE);
      ((RadioButton) findViewById(R.id.hardButton)).setTextColor(Color.WHITE);
      ((RadioButton) findViewById(R.id.charAButton)).setTextColor(Color.WHITE);
      ((RadioButton) findViewById(R.id.charBButton)).setTextColor(Color.WHITE);
    }
  }

  /**
   * Called when the user currently logged in hits their android device 'back' button. Sends the new
   * colour scheme back to Profile Page activity for updating the light/dark theme
   */
  @Override
  public void onBackPressed() {
    System.out.println("Method CustomizeActivity.onBackPressed reached");
    Intent backToProfilePage = new Intent(this, ProfilePageActivity.class);
    if (colourSchemeChanged) {
      backToProfilePage.putExtra("colourScheme", sqlHelper.findColourScheme(username));
    } else {
      backToProfilePage.putExtra("colourScheme", "not changed");
    }
    setResult(Activity.RESULT_OK, backToProfilePage);
    finish();
  }

  /**
   * Called when the user currently logged in clicks the 'light' button for the colour scheme.
   * Presenter for this activity changes colour scheme.
   *
   * @param view the view displaying this activity.
   */
  public void onClickLightButton(View view) {
    // presenter returns true if colour scheme was changed
    if (customizePresenter.changeColourScheme(sqlHelper, username, "Light")) {
      colourSchemeChanged = true;
      setComponentColours(); // changes background colours immediately
    } else {
      colourSchemeChanged = false;
    }
  }

  /**
   * Called when the user currently logged in clicks the 'dark' button for the colour scheme.
   * Presenter for this activity changes colour scheme.
   *
   * @param view the view displaying this activity.
   */
  public void onClickDarkButton(View view) {
    // presenter returns true if colour scheme was changed
    if (customizePresenter.changeColourScheme(sqlHelper, username, "Dark")) {
      colourSchemeChanged = true;
      setComponentColours(); // changes background colours immediately
    } else {
      colourSchemeChanged = false;
    }
  }

  /**
   * Called when the user currently logged in clicks the 'easy' button for the level difficulty.
   * Presenter for this activity changes level difficulty.
   *
   * @param view the view displaying this activity.
   */
  public void onClickEasyButton(View view) {
    customizePresenter.changeLevelDifficulty(sqlHelper, username, "Easy");
  }

  /**
   * Called when the user currently logged in clicks the 'hard' button for the level difficulty.
   * Presenter for this activity changes level difficulty.
   *
   * @param view the view displaying this activity.
   */
  public void onClickHardButton(View view) {
    customizePresenter.changeLevelDifficulty(sqlHelper, username, "Hard");
  }

  /**
   * Called when the user currently logged in selects character option "A" for custom character.
   * Presenter for this activity changes character's appearance
   *
   * @param view the view displaying this activity.
   */
  public void onClickCharAButton(View view) {
    customizePresenter.changeCustomCharacter(sqlHelper, username, "A");
  }

  /**
   * Called when the user currently logged in selects character option "B" for custom character.
   * Presenter for this activity changes character's appearance
   *
   * @param view the view displaying this activity.
   */
  public void onClickCharBButton(View view) {
    customizePresenter.changeCustomCharacter(sqlHelper, username, "B");
  }
}
