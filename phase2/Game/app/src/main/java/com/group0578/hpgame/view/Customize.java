package com.group0578.hpgame.view;

import android.view.View;

import com.group0578.hpgame.model.SQLiteHelper;

/**
 * Defines the contract between the View {@link com.group0578.hpgame.view.CustomizeActivity} and
 * the Presenter {@link com.group0578.hpgame.presenter.CustomizePresenter}.
 */
public interface Customize {

    /**
     * Behaviour of the CustomizeActivity
     */
    interface View {
        void onClickLightButton(android.view.View view);
        void onClickDarkButton(android.view.View view);
        void onClickEasyButton(android.view.View view);
        void onClickHardButton(android.view.View view);
        void onClickCharCircleButton(android.view.View view);
        void onClickCharSquareButton(android.view.View view);
        void displayToast(String message);
    }

    /**
     * Behaviour of the CustomizePresenter
     */
    interface Presenter {
        boolean changeColourScheme(SQLiteHelper sqlHelper, String username, String colourScheme);

        boolean changeLevelDifficulty(SQLiteHelper sqlHelper, String username, String levelDifficulty);

        void changeCustomCharacter(SQLiteHelper sqlHelper, String username, String customChar);
    }
}
