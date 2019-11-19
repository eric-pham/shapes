package com.group0578.hpgame.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;

import com.group0578.hpgame.Level1.Level1Activity;
import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.view.Login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.FileReader;

import com.google.gson.Gson;
import com.group0578.hpgame.view.LoginActivity;
import com.group0578.hpgame.view.ProfilePageActivity;
import com.group0578.hpgame.view.StartupActivity;

import java.io.File;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.LoginActivity}
 * and updating the UI as required.
 */
public class LoginPresenter implements Login.Presenter {

  /** The view associated with this presenter. */
  private Login.View loginView;

  /**
   * The constructor for the Presenter associated with the LoginActivity view.
   *
   * @param loginView the variable that represents the LoginActivity.
   */
  public LoginPresenter(Login.View loginView) {
    this.loginView = loginView;
  }

  /**
   * Verifies the login
   *
   * @param sqlHelper SQLiteHelper object
   * @param username username to check
   * @param password password to check
   * @return true if the password associated with the username is correct, false otherwise
   */
  public boolean checkLogin(SQLiteHelper sqlHelper, String username, String password) {
    // Search database for password associated with username
    String returnPass = sqlHelper.findPassword(username);

    // Checks whether password is correct
    return returnPass.equals(password);
  }

  /** Load stage 1 */
  public void createNewStage1Screen() { // not using anymore -> go to profile screen
    Intent createStage1Intent = new Intent((LoginActivity) this.loginView, Level1Activity.class);
    System.out.println("Method reached 3");
    loginView.goToStage1Screen(createStage1Intent);
  }

  // Not working yet
//  public void createProfileScreen() {
//    Intent profileIntent = new Intent((LoginActivity) this.loginView, ProfilePageActivity.class);
//    System.out.println("createProfileScreen method reached.");
//    loginView.goToProfilePage(profileIntent);
//  }
}
