package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.Stats;
import com.group0578.hpgame.view.StatsActivity;

/**
 * Responsible for handling actions from the View {@link StatsActivity}
 * and updating the UI as required.
 */
public class StatsPresenter implements Stats.Presenter {

    /**
     * The view associated with this presenter.
     */
    private Stats.View statsView;

    public StatsPresenter(Stats.View userStatsView) {
        this.statsView = userStatsView;
    }
}
