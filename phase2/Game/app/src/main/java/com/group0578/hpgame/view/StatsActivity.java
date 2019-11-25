package com.group0578.hpgame.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.StatsPresenter;

/**
 * Displays the user statistics screen.
 */
public class StatsActivity extends AppCompatActivity implements Stats.View {

    /**
     * The presenter associated with this View that handles the user's interactions with the UI.
     */
    private StatsPresenter statsPresenter;

    /**
     * The constructor for the activity that displays the user statistics screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        statsPresenter = new StatsPresenter(this);

    }
}
