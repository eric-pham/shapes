package com.group0578.hpgame.presenter;

import android.content.Intent;

import com.group0578.hpgame.Level2.MazeActivity;
import com.group0578.hpgame.Level3.Level3MainActivity;
import com.group0578.hpgame.view.CustomizeActivity;
import com.group0578.hpgame.view.ProfilePage;
import com.group0578.hpgame.view.ProfilePageActivity;

/**
 * Responsible for handling actions from the View {@link
 * com.group0578.hpgame.view.ProfilePageActivity} and updating the UI as required.
 */
public class ProfilePagePresenter implements ProfilePage.Presenter {

    /** The view associated with this presenter. */
    private ProfilePage.View profilePageView;

    /**
     * The constructor for the Presenter associated with the ProfilePageActivity view.
     *
     * @param profilePageView the variable that represents the ProfilePageActivity.
     */
    public ProfilePagePresenter(ProfilePage.View profilePageView) {
        this.profilePageView = profilePageView;
    }

    /** Creates the intent for the Level1Activity */
    public void createLevel1() {
        Intent levelOne = new Intent((ProfilePageActivity) this.profilePageView, MazeActivity.class);  // temporarily changed from level1 to level 2 for testing purposes
        System.out.println("levelOne intent created");
        profilePageView.goToLevel1(levelOne);
    }

    /**
     * Resumes the previous game for the user currently playing.
     *
     * @param progress
     */
    @Override
    public void resumePreviousGame(String progress) {

    }

    /**
     * Shows statistics from game history for the user currently playing. If no previous games played,
     * default display message: "No games played. Statistics available after game completion."
     */
    @Override
    public void displayPlayerStats() {}

    /**
     * Changes the logged in user's customization preferences for colour scheme, game level difficulty
     * etc.
     */
    @Override
    public void changeUserCustomization() {
        Intent createCustomizePage =
                new Intent((ProfilePageActivity) profilePageView, CustomizeActivity.class);
        System.out.println("Method ProfilePagePresenter.changeUserCustomization() reached");
        profilePageView.goToCustomizePage(createCustomizePage);
    }
}
