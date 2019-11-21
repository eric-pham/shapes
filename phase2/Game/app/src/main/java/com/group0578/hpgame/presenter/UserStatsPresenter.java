package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.UserStats;

/**
 * Responsible for handling actions from the View {@link com.group0578.hpgame.view.UserStatsActivity}
 * and updating the UI as required.
 */
public class UserStatsPresenter implements UserStats.Presenter {

    /**
     * The view associated with this presenter.
     */
    private UserStats.View userStatsView;

    public UserStatsPresenter(UserStats.View userStatsView) {
        this.userStatsView = userStatsView;
    }
}
