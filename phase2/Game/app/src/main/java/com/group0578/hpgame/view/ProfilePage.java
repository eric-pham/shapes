package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.presenter.ProfilePagePresenter;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.ProfilePageActivity} and the
 * Presenter {@link ProfilePagePresenter}.
 */
public interface ProfilePage {

        /**
         * Behaviour of the StartupActivity.
         */
        interface View {

                void goToLevel1(Intent levelOne);
        }

        /**
         * Not sure if needed yet.
         */
        interface Model {

        }

        /**
         * Behaviour of the StartupPresenter.
         */
        interface Presenter {
                void createLevel1();
        }
}
