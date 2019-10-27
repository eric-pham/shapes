package com.group0578.hpgame.startup;

import android.content.Intent;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.startup.StartupActivity} and the
 * Presenter {@link StartupPresenter}.
 */
public interface Startup {
    interface View {
        void goToLoginScreen(Intent loginIntent);
    }

    interface Model {

    }

    interface Presenter {
        void createLoginScreen();
    }
}
