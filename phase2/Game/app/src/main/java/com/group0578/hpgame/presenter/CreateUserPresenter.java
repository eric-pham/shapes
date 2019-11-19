package com.group0578.hpgame.presenter;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.model.SQLiteManager;
import com.group0578.hpgame.view.CreateUser;
import android.content.Context;

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
}
