package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.group0578.hpgame.R;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.LoginPresenter;
/** Displays the login screen. */
public class LoginActivity extends AppCompatActivity implements Login.View {

  /** The presenter associated with this View that handles the user's interactions with the UI. */
  private LoginPresenter loginPresenter;

  /** SQL helper associated with this LoginActivity */
  SQLiteHelper sqlHelper = new SQLiteHelper(this);

  /** The constructor for the activity that displays the Login Screen. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    loginPresenter = new LoginPresenter(this);

  }


  /**
   * When login button is clicked, verify if password is valid with given username, launch level 1
   * if login succeeds
   *
   * @param v view
   */
  public void onClickLogin(View v) {
    // Get username and password from user inputted EditText
    final EditText loginUser = findViewById(R.id.loginUser);
    String username = loginUser.getText().toString();

    final EditText loginPassword = findViewById(R.id.loginPassword);
    String password = loginPassword.getText().toString();

    if (loginPresenter.checkLogin(sqlHelper, username, password)) {  // the login info is correct
      loginPresenter.createProfileScreen(username);
    } else {  // the password and username don't match
      // after button is clicked, hides keyboard
      InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      assert imm != null;
      imm.hideSoftInputFromWindow(findViewById(R.id.constraintLayoutLogin).getWindowToken(), 0);

      // makes error message visible
      findViewById(R.id.errorMessageLogin).setVisibility(View.VISIBLE);
    }
  }


  /**
   * Starts the ProfilePageActivity.
   *
   * @param profileIntent the intent for ProfilePageActivity
   */
  public void goToProfilePage(Intent profileIntent) {
    startActivity(profileIntent);
    finish();
  }
}
