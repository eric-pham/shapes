package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.Scoreboard;
import com.group0578.hpgame.view.ScoreboardActivity;

/**
 * Responsible for handling actions from the View {@link ScoreboardActivity}
 * and updating the UI as required.
 */
public class ScoreboardPresenter implements Scoreboard.Presenter {

    /**
     * The view associated with this presenter.
     */
    private Scoreboard.View statsView;

    public ScoreboardPresenter(Scoreboard.View userStatsView) {
        this.statsView = userStatsView;
    }
}
