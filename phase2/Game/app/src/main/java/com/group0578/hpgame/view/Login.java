package com.group0578.hpgame.view;

import android.content.Intent;

import com.group0578.hpgame.presenter.LoginPresenter;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.LoginActivity} and the
 * Presenter {@link LoginPresenter}.
 */
public interface Login {

    /**
     * Behaviour of the LoginActivity.
     */
    interface View {
        void goToStage1Screen(Intent createStage1Intent);

//        void goToProfilePage(Intent profileIntent);

    }

    /**
     * Not sure if needed yet.
     */
    interface Model {

    }

    /**
     * Behaviour of the LoginPresenter.
     */
    interface Presenter {

    }
}
