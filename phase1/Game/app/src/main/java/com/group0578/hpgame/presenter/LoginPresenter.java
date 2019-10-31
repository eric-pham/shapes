package com.group0578.hpgame.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;

import com.group0578.hpgame.Level1.Level1Activity;
import com.group0578.hpgame.view.Login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.FileReader;

import com.google.gson.Gson;
import com.group0578.hpgame.view.LoginActivity;
import com.group0578.hpgame.view.StartupActivity;

import java.io.File;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.LoginActivity}
 * and updating the UI as required.
 */
public class LoginPresenter implements Login.Presenter {

  /**
   * The view associated with this presenter.
   */
  private Login.View loginView;

  /**
   * The constructor for the Presenter associated with the LoginActivity view.
   *
   * @param loginView the variable that represents the LoginActivity.
   */
  public LoginPresenter(Login.View loginView) {
    this.loginView = loginView;
  }

  public boolean checkLogin(Activity act, String username, String password) {
    // Given username and password check if valid key value pair
    // do something
    //System.out.println(username);
    //System.out.println(password);

    // Create new gson
    Gson gson = new Gson();

    try {
      // Create AssetsManager, InputStream and BufferedReader
      AssetManager am = act.getAssets();
      String[] asd = am.list("");
      InputStream is = am.open("userInfo.json");
      BufferedReader bf = new BufferedReader(new InputStreamReader(is));

      // Create HashMap
      HashMap hmap = gson.fromJson(bf, HashMap.class);

      //System.out.println(hmap.get("username"));

      //Check HashMap for match
      return hmap.get(username).equals(password);

    } catch (FileNotFoundException e) {
      System.out.println("Exception!");
    } catch (Exception e) {
      // hmap.get did not work, username is not in userInfo
      System.out.println("Exception e!");
      return false;
    }
    return false;
  }

  public void createNewStage1Screen() {
    Intent createStage1Intent = new Intent((LoginActivity) this.loginView, Level1Activity.class);
    System.out.println("Method reached 3");
    loginView.goToStage1Screen(createStage1Intent);
  }
}
