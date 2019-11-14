package com.group0578.hpgame.presenter;

import android.content.Intent;

import com.group0578.hpgame.Level1.Level1Activity;
import com.group0578.hpgame.Level2.MazeActivity;
import com.group0578.hpgame.Level3.Level3MainActivity;
import com.group0578.hpgame.view.CreateUserActivity;
import com.group0578.hpgame.view.LoginActivity;
import com.group0578.hpgame.view.Startup;
import com.group0578.hpgame.view.StartupActivity;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.StartupActivity}
 * and updating the UI as required.
 */
public class StartupPresenter implements Startup.Presenter {

  /** The view associated with this presenter. */
  private Startup.View startupView;

  /**
   * The constructor for the Presenter associated with the StartupActivity view.
   *
   * @param startupView the variable that represents the StartupActivity.
   */
  public StartupPresenter(Startup.View startupView) {
    this.startupView = startupView;
  }

  /** Creates the Intent to switch to the LoginActivity. */
  public void createLoginScreen() {
    // I think im violating dependency rule right here by casting
    Intent loginIntent = new Intent((StartupActivity) this.startupView, LoginActivity.class);
    System.out.println("Method reached");
    startupView.goToLoginScreen(loginIntent);
  }

  /** Creates the Intent to switch to the CreateUserActivity. */
  public void createNewAccountScreen() {
    // I think im violating dependency rule right here by casting
    Intent createUserIntent =
        new Intent((StartupActivity) this.startupView, CreateUserActivity.class);
    System.out.println("Method reached 2");
    startupView.goToCreateUserScreen(createUserIntent);
  }

  public void createNewStage1Screen() {
    Intent createStage1Intent =
        new Intent((StartupActivity) this.startupView, Level1Activity.class);
    System.out.println("Method reached 3");
    startupView.goToStage1Screen(createStage1Intent);
  }

  public void createNewStage3Screen() {
    Intent createStage3Intent =
        new Intent((StartupActivity) this.startupView, Level3MainActivity.class);
    System.out.println("Method reached 3");
    startupView.goToStage3Screen(createStage3Intent);
  }

  // temporary ,method to make testing of level 2 (the maze) easier
  public void createNewStage2Screen() {
    Intent createStage2Intent = new Intent((StartupActivity) this.startupView, MazeActivity.class);
    System.out.println("Method reached 3");
    startupView.goToStage2Screen(createStage2Intent);
  }
}
