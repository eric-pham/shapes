package com.group0578.hpgame.presenter;

import android.content.Intent;

import com.group0578.hpgame.view.CreateUserActivity;
import com.group0578.hpgame.view.LoginActivity;
import com.group0578.hpgame.view.Startup;
import com.group0578.hpgame.view.StartupActivity;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.StartupActivity}
 * and updating the UI as required.
 */
public class StartupPresenter implements Startup.Presenter {

    private Startup.View startupView;

    /**
     * The constructor for the Presenter associated with the StartupActivity view.
     * @param startupView the variable that represents the StartupActivity.
     */
    public StartupPresenter(Startup.View startupView) {
        this.startupView = startupView;
    }

    /**
     * Creates the Intent to switch to the LoginActivity.
     */
    public void createLoginScreen() {
        // I think im violating dependency rule right here by casting
        Intent loginIntent = new Intent((StartupActivity) this.startupView, LoginActivity.class);
        System.out.println("Method reached");
        startupView.goToLoginScreen(loginIntent);
    }

     /**
     * Creates the Intent to switch to the CreateUserActivity.
     */
    public void createNewAccountScreen() {
        // I think im violating dependency rule right here by casting
        Intent createUserIntent = new Intent((StartupActivity) this.startupView, CreateUserActivity.class);
        System.out.println("Method reached 2");
        startupView.goToCreateUserScreen(createUserIntent);
    }
}
