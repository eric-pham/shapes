package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.presenter.LoginPresenter;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.LoginActivity} and the
 * Presenter {@link LoginPresenter}.
 */
public interface Login {

    /**
     * Behaviour of the LoginActivity.
     */
    interface View {
        void goToProfilePage(Intent profileIntent);
    }

    /**
     * Behaviour of the LoginPresenter.
     */
    interface Presenter {
        boolean checkLogin(SQLiteHelper sqlHelper, String username, String password);
        void createProfileScreen(String username);
    }
}
