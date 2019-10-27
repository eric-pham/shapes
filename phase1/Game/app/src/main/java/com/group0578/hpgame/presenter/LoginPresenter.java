package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.Login;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.LoginActivity}
 * and updating the UI as required.
 */
public class LoginPresenter implements Login.Presenter {

    /**
     * The view associated with this presenter.
     */
    private Login.View loginView;

    /**
     * The constructor for the Presenter associated with the LoginActivity view.
     * @param loginView the variable that represents the LoginActivity.
     */
    public LoginPresenter(Login.View loginView) {
        this.loginView = loginView;
    }

    public boolean checkLogin(String username, String password) {
        //Given username and password check if valid key value pair
        //do something
        System.out.println(username);
        System.out.println(password);
        return true;
    }
}
