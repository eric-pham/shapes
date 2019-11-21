package com.group0578.hpgame.presenter;

import com.group0578.hpgame.model.SQLiteHelper;
import com.group0578.hpgame.view.Customize;

/**
 * Responsible for handling actions from the View {@link
 * com.group0578.hpgame.view.CustomizeActivity} and updating the UI as required.
 */
public class CustomizePresenter implements Customize.Presenter {

    /**
     * Customize.View interface reference to refer to the CustomizeActivity file
     */
    public Customize.View customizeView;

    /**
     * Constructs an instance of a Presenter responsible for updating the customization page.
     *
     * @param customizeView the View responsible for CustomizeActivity.
     */
    public CustomizePresenter(Customize.View customizeView) {
        // Initialized to instance of CustomizeActivity object
        this.customizeView = customizeView;
    }

    @Override
    public void changeColourScheme(SQLiteHelper sqlHelper, String username, String colourScheme) {
        if (!sqlHelper.findColourScheme(username).equals(colourScheme)) {
            System.out.println("New colour scheme preference detected!");
            sqlHelper.setColourScheme(username, colourScheme);
        }
    }

    @Override
    public void changeLevelDifficulty(SQLiteHelper sqlHelper, String username, String difficulty) {
        if (!sqlHelper.findDifficulty(username).equals(difficulty)) {
            System.out.println("New difficulty preference detected!");
            sqlHelper.setDifficulty(username, difficulty);
        }
    }

    @Override
    public void changeCustomCharacter(SQLiteHelper sqlHelper, String username, String customChar) {

    }
}
