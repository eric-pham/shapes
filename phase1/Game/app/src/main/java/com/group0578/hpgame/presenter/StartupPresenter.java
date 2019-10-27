package com.group0578.hpgame.presenter;

import android.content.Intent;

import com.group0578.hpgame.CreateAccountActivity;
import com.group0578.hpgame.LoginActivity;
import com.group0578.hpgame.view.Startup;
import com.group0578.hpgame.view.StartupActivity;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.StartupActivity}
 * and updating the UI as required.
 */
public class StartupPresenter implements Startup.Presenter {

    private Startup.View startupView;

    public StartupPresenter(Startup.View startupView) {
        this.startupView = startupView;
    }

    public void createLoginScreen() {
        // I think im violating dependency rule right here by casting
        Intent loginIntent = new Intent((StartupActivity) this.startupView, LoginActivity.class);
        System.out.println("Method reached");
//        (StartupActivity) startupView.startActivity(loginIntent);
        startupView.goToLoginScreen(loginIntent);
    }

    public void createNewAccountScreen() {
        // I think im violating dependency rule right here by casting
        Intent createAccountIntent = new Intent((StartupActivity) this.startupView, CreateAccountActivity.class);
        System.out.println("Method reached 2");
//        (StartupActivity) startupView.startActivity(loginIntent);
        startupView.goToCreateAccountScreen(createAccountIntent);
    }
}
