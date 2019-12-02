package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.presenter.StartupPresenter;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.StartupActivity} and the
 * Presenter {@link StartupPresenter}.
 */
public interface Startup {

  /** Behaviour of the StartupActivity. */
  interface View {
    void goToLoginScreen(Intent loginIntent);

    void goToCreateUserScreen(Intent createAccountIntent);
  }

  /** Behaviour of the StartupPresenter. */
  interface Presenter {
    void createLoginScreen();

    void createNewAccountScreen();
  }
}
