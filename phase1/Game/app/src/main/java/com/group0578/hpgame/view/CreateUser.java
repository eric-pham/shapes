package com.group0578.hpgame.view;

import com.group0578.hpgame.model.SQLiteHelper;

/**
 * Methods responsible for communication between CreateUserPresenter and CreateUserActivity
 */
public interface CreateUser {

    /**
     * Methods implemented by CreateUserActivity
     */
    interface View {
    }

    // Haven't decided if we need this yet
    interface Model {

    }

    /**
     * Methods implemented by CreateUserPresenter
     */
    interface Presenter {
        void createAccount(SQLiteHelper sqlHelper, String username, String password);
    }

}
