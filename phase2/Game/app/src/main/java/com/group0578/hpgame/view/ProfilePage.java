package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.presenter.ProfilePagePresenter;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.ProfilePageActivity} and
 * the Presenter {@link ProfilePagePresenter}.
 */
public interface ProfilePage {

  /** Behaviour of the StartupActivity. */
  interface View {
    void goToLevel1(Intent levelOne);

    void goToCustomizePage(Intent createCustomizePage);

      void resumePreviousLevel(Intent previousLevel);

      void goToPlayerStatsPage(Intent playerStatsPage);

    void displayToast(String message);
  }

  /** Not sure if needed yet. */
  interface Model {}

  /** Behaviour of the StartupPresenter. */
  interface Presenter {
    void createLevel1();

    void resumePreviousGame(String progress);

    void displayPlayerStats();

    void changeUserCustomization();
  }
}
