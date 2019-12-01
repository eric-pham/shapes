package com.group0578.hpgame.view;

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

    }

    interface Model {

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
