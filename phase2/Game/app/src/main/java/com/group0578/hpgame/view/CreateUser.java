package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.model.SQLiteHelper;

/**
 * Methods responsible for communication between CreateUserPresenter and CreateUserActivity
 */
public interface CreateUser {

    /**
     * Methods implemented by CreateUserActivity
     */
    interface View {
        void goToProfilePage(Intent profileIntent);
    }

    // Haven't decided if we need this yet
    interface Model {

    }

    /**
     * Methods implemented by CreateUserPresenter
     */
    interface Presenter {
        void createAccount(SQLiteHelper sqlHelper, String username, String password);

        void createProfileScreen(String username);

        boolean checkDuplicates(SQLiteHelper sqlHelper, String username);
    }

}
