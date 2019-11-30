package com.group0578.hpgame.presenter;

import android.content.Intent;

import com.group0578.hpgame.level1.FlyingActivity;
import com.group0578.hpgame.level2.MazeActivity;
import com.group0578.hpgame.level3.Level3MainActivity;
import com.group0578.hpgame.view.CustomizeActivity;
import com.group0578.hpgame.view.ProfilePage;
import com.group0578.hpgame.view.ProfilePageActivity;
import com.group0578.hpgame.view.StatsActivity;

/**
 * Responsible for handling actions from the View {@link
 * com.group0578.hpgame.view.ProfilePageActivity} and updating the UI as required.
 */
public class ProfilePagePresenter implements ProfilePage.Presenter {

    /**
     * The view associated with this presenter.
     */
    private ProfilePage.View profilePageView;

    /**
     * The constructor for the Presenter associated with the ProfilePageActivity view.
     *
     * @param profilePageView the variable that represents the ProfilePageActivity.
     */
    public ProfilePagePresenter(ProfilePage.View profilePageView) {
        this.profilePageView = profilePageView;
    }

    /**
     * Creates the intent for the FlyingActivity
     */
    public void createLevel1() {
        Intent levelOne = new Intent((ProfilePageActivity) this.profilePageView, FlyingActivity.class);
        System.out.println("levelOne intent created");
        profilePageView.goToLevel1(levelOne);
    }

    /**
     * Resumes the previous game for the user currently playing.
     * @param progress the progress level from the previous game for the user currently playing.
     */
    @Override
    public void resumePreviousGame(String progress) {
        Intent resumeLevel;
        if (progress.equalsIgnoreCase("one")) {
            resumeLevel = new Intent((ProfilePageActivity) this.profilePageView, MazeActivity.class);
            this.profilePageView.resumePreviousLevel(resumeLevel);
        } else if (progress.equalsIgnoreCase("two")) {
            resumeLevel = new Intent((ProfilePageActivity) this.profilePageView, Level3MainActivity.class);
            this.profilePageView.resumePreviousLevel(resumeLevel);
        } else { // progress equals "none" (no games completed yet)
            // Display message to show no previous games have been started.
        }
    }

    /**
     * Shows statistics from game history for the user currently playing. If no previous games played,
     * default display message: "No games played. Statistics available after game completion."
     */
    @Override
    public void displayPlayerStats() {
        Intent createStatsPage = new Intent((ProfilePageActivity) this.profilePageView, StatsActivity.class);
        this.profilePageView.goToPlayerStatsPage(createStatsPage);
    }

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
