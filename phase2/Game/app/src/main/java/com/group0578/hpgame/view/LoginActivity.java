package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.group0578.hpgame.Level1.Level1Activity;
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

    if (loginPresenter.checkLogin(sqlHelper, username, password)) {
      loginPresenter.createNewStage1Screen();
//        loginPresenter.createProfileScreen(); // not working yet
    } else {
      System.out.println("Login Failed!");
    }
  }

  public void goToStage1Screen(Intent stage1Intent) {
    System.out.println("Method reached 2");
    startActivity(stage1Intent);
  }

  // Not working yet
//  public void goToProfilePage(Intent profileIntent) {
//    System.out.println("Testing: Reached method LoginActivity.goToProfilePage ");
//    startActivity(profileIntent);
//  }
}
