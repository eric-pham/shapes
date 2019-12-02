package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.ProfilePagePresenter;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.ProfilePageActivity} and
 * the Presenter {@link ProfilePagePresenter}.
 */
public interface ProfilePage {

  /** Behaviour of the ProfilePageActivity. */
  interface View {
    void goToLevel1(Intent levelOne);

    void goToCustomizePage(Intent createCustomizePage);

      void resumePreviousLevel(Intent previousLevel);

      void goToPlayerStatsPage(Intent playerStatsPage);

    void displayToast(String message);
  }

  /** Behaviour of the ProfilePagePresenter. */
  interface Presenter {
    void createLevel1();

    void resumePreviousGame(String progress);

    void displayScoreBoard();

    void changeUserCustomization();

    void resetDefaults(SQLiteHelper sqlHelper, String username);
  }
}
