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

    /**
     * Changes the colour scheme customization setting for the user currently logged in to either the
     * light or dark theme.
     *
     * @param sqlHelper    the database accessing and modifying this user's colour scheme
     * @param username     String: username of the user currently logged in
     * @param colourScheme String: the new colour scheme
     * @return true if the colour scheme in the database was updated, else false
     */
    @Override
    public boolean changeColourScheme(SQLiteHelper sqlHelper, String username, String colourScheme) {
        // Database colour scheme does not match new colour scheme for this user
        if (!sqlHelper.findColourScheme(username).equals(colourScheme)) {
            System.out.println("New colour scheme preference detected!");
            sqlHelper.setColourScheme(username, colourScheme);
            return true; // colour scheme updated
        }
        return false; // colour scheme not updated
    }

    /**
     * Changes the level difficulty for the user who is currently logged in.
     *
     * @param sqlHelper  the database accessing and modifying this user's level difficulty
     * @param username   String: username of the user currently logged in
     * @param difficulty String: the new level difficulty
     */
    @Override
    public void changeLevelDifficulty(SQLiteHelper sqlHelper, String username, String difficulty) {
        // Database level difficulty does not match new level difficulty for this user
        if (!sqlHelper.findDifficulty(username).equals(difficulty)) {
            System.out.println("New difficulty preference detected!");
            sqlHelper.setDifficulty(username, difficulty);
        }
    }

    /**
     * Changes the character appearance for the user who is currently logged in.
     *
     * @param sqlHelper  the database accessing and modifying this user's character appearance
     * @param username   String: username of the user currently logged in
     * @param character  String: the new character appearance
     */
    @Override
    public void changeCustomCharacter(SQLiteHelper sqlHelper, String username, String character) {
        // Database character preference does not match new preference for this user
        if (!sqlHelper.findCharacter(username).equals(character)) {
            System.out.println("New character preference detected!" + character);
            sqlHelper.setCharacter(username, character);
        }
    }
}
