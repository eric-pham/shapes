package com.group0578.hpgame.view;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.CustomizePresenter;

/**
 * Responsible for handling user actions like button clicks on the Customization page
 */
public class CustomizeActivity extends AppCompatActivity implements Customize.View {

  /**
   * The presenter associated with this View that handles the user's interactions with the UI.
   */
  CustomizePresenter customizePresenter;

  /**
   * The username belonging to the user currently logged in and viewing the profile page.
   */
  private String username;

  /**
   * SQL helper associated with this CustomizeActivity
   */
  private SQLiteHelper sqlHelper = new SQLiteHelper(this);

  /**
   * Called immediately when the activity is started.
   */
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
  }

  /**
   * Checks what the colour scheme is for the user currently logged in, and checks the light and
   * dark radio buttons accordingly.
   */
  private void setButtonsClicked() {
    String colourScheme = sqlHelper.findColourScheme(username);
    if (colourScheme.equalsIgnoreCase("Light")) {
      ((RadioButton) findViewById(R.id.lightButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.darkButton)).setChecked(false);
    } else {
      ((RadioButton) findViewById(R.id.darkButton)).setChecked(true);
      ((RadioButton) findViewById(R.id.lightButton)).setChecked(false);
    }
  }

  /**
   * Called when the user currently logged in clicks the 'light' button for the colour scheme.
   * Presenter for this activity changes colour scheme.
   *
   * @param view the view displaying this activity.
   */
  public void onClickLightButton(View view) {
    customizePresenter.changeColourScheme(sqlHelper, username, "Light");
//    if (!((RadioButton) findViewById(R.id.lightButton)).isChecked()) {
//      ((RadioButton) findViewById(R.id.lightButton)).setChecked(true);
//      ((RadioButton) findViewById(R.id.darkButton)).setChecked(false);
//    }
  }

  /**
   * Called when the user currently logged in clicks the 'dark' button for the colour scheme.
   * Presenter for this activity changes colour scheme.
   *
   * @param view the view displaying this activity.
   */
  public void onClickDarkButton(View view) {
    customizePresenter.changeColourScheme(sqlHelper, username, "Dark");
//    if (!((RadioButton) findViewById(R.id.darkButton)).isChecked()) {
//      ((RadioButton) findViewById(R.id.darkButton)).setChecked(true);
//      ((RadioButton) findViewById(R.id.lightButton)).setChecked(false);
//    }
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
