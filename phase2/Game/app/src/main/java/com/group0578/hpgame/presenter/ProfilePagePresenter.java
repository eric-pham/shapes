package com.group0578.hpgame.presenter;

import android.content.Intent;

import com.group0578.hpgame.Level1.Level1Activity;
import com.group0578.hpgame.view.ProfilePage;
import com.group0578.hpgame.view.ProfilePageActivity;

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

    /**
     * Creates the intent for the Level1Activity
     */
    public void createLevel1() {
        Intent levelOne = new Intent((ProfilePageActivity) this.profilePageView, Level1Activity.class);
        System.out.println("levelOne intent created");
        profilePageView.goToLevel1(levelOne);
    }
}
