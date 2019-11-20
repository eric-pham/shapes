package com.group0578.hpgame.presenter;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.SQLiteManager;
import com.group0578.hpgame.view.CreateUser;
import com.group0578.hpgame.view.CreateUserActivity;
import com.group0578.hpgame.view.ProfilePageActivity;

import android.content.Context;
import android.content.Intent;

/**
 * Responsible for handling actions from the View {@link
 * com.group0578.hpgame.view.CreateUserActivity} and updating the UI as required.
 */
public class CreateUserPresenter implements CreateUser.Presenter {

  /** CreateUser.View interface reference */
  private CreateUser.View createUserView;

  /**
   * Initializes this class
   *
   * @param createUserView the View responsible for CreateUserActivity
   */
  public CreateUserPresenter(CreateUser.View createUserView) {
    // Initialized to instance of CreateUserActivity object
    this.createUserView = createUserView;
  }

  /**
   * Creates a new account
   * @param sqlHelper stores username and password
   * @param username username for account
   * @param password password for account
   */
  public void createAccount(SQLiteHelper sqlHelper, String username, String password) {
      SQLiteManager sql = new SQLiteManager();
      sql.setUsername(username);
      sql.setPassword(password);

      // Setting initial default values
      // This code doesn't work right now
//    sql.setColourScheme("Light");
//    sql.setLevelDifficulty("Easy");
//    sql.setLevelOneTime(0);
//    sql.setLevelTwoTime(0);
//    sql.setLevelThreeTime(0);
//    sql.setCurrLives(10);

      sqlHelper.insertUser(sql);
      System.out.println("Inserted!");


  }

    /**
     * Creates the intent for the ProfilePageActivity and attaches the username to the intent.
     *
     * @param username the username of the person currently logged in
     */
    @Override
    public void createProfileScreen(String username) {
        Intent profileIntent = new Intent((CreateUserActivity) this.createUserView, ProfilePageActivity.class);
        profileIntent.putExtra("username", username);
        System.out.println("createProfileScreen method reached.");
        createUserView.goToProfilePage(profileIntent);
    }

    /**
     * Checks whether the username is available.
     *
     * @param sqlHelper instance of the database helper that defines all methods that can be used on
     *                  the database
     * @param username  the username of the person currently logged in
     * @return a boolean; returns true if the username is already taken; returns false if the username
     * is available.
     */
    public boolean checkDuplicates(SQLiteHelper sqlHelper, String username) {
        return sqlHelper.checkDuplicates(username);
    }
}
