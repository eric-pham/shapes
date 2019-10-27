package com.group0578.hpgame.startup;

import android.content.Intent;

import com.group0578.hpgame.LoginActivity;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.startup.StartupActivity}
 * and updating the UI as required.
 */
public class StartupPresenter implements Startup.Presenter {

    private Startup.View startupView;

    StartupPresenter(Startup.View startupView) {
        this.startupView = startupView;
    }

    public void createLoginScreen() {
        // I think im violating dependency rule right here by casting
        Intent loginIntent = new Intent((StartupActivity) this.startupView, LoginActivity.class);
        System.out.println("Method reached");
//        (StartupActivity) startupView.startActivity(loginIntent);
        startupView.goToLoginScreen(loginIntent);
    }

}
