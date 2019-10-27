package com.group0578.hpgame.view;

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
        void createAccount();
    }

}
