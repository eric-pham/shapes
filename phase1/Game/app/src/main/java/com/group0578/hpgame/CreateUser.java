package com.group0578.hpgame;

import android.content.Intent;

/**
 * Methods responsible for communication between CreateUserPresenter and CreateUserActivity
 */
public interface CreateUser {

    /**
     * Methods implemented by CreateUserActivity
     */
    interface View {
        void goToCreateUserScreen(Intent createUserIntent);
    }

    // Haven't decided if we need this yet
    interface Model {

    }

    /**
     * Methods implemented by CreateUserPresenter
     */
    interface Presenter {
        void createAccount();
    }

}
