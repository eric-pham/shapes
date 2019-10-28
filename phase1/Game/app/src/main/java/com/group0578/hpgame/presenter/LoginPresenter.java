package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.Login;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.io.FileReader;
import com.google.gson.Gson;

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


        Gson gson = new Gson();
        try{
            HashMap hmap = gson.fromJson(new FileReader("userInfo.json"), HashMap.class);
            System.out.println(hmap);
            System.out.println(hmap.get("username"));
        } catch(FileNotFoundException e){
            System.out.println("Exception!");
        }
        return true;
    }
}
