package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.Login;

public class LoginPresenter implements Login.Presenter {

    private Login.View loginView;

    /**
     * The constructor for the Presenter associated with the LoginActivity view.
     * @param loginView the variable that represents the StartupActivity.
     */
    public LoginPresenter(Login.View loginView) {
        this.loginView = loginView;
    }

}
