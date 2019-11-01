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

  SQLiteHelper sqlHelper = new SQLiteHelper(this);
  /** The constructor for the activity that displays the Login Screen. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    loginPresenter = new LoginPresenter(this);
  }

  /** When login button is clicked, check if username and password are valid */
  public void onClickLogin(View v) {
    // Get username and password from user inputted EditText
    final EditText loginUser = findViewById(R.id.loginUser);
    String username = loginUser.getText().toString();

    final EditText loginPassword = findViewById(R.id.loginPassword);
    String password = loginPassword.getText().toString();

    //        //Call presenter to verify username and password
    //        if (loginPresenter.checkLogin(this, username, password)) {
    //            //If checks out do something
    //            loginPresenter.createNewStage1Screen();
    //        } else {
    //            //if invalid do something
    //            System.out.println("Invalid");
    //        }

    String returnPass = sqlHelper.findPassword(username);
    if (returnPass.equals(password)) {
      System.out.println("LOGIN!");
      loginPresenter.createNewStage1Screen();
    } else {
      System.out.println("FAILED LOGIN");
    }
  }

  public void goToStage1Screen(Intent stage1Intent) {
    System.out.println("Method reached 2");
    startActivity(stage1Intent);
  }
}
