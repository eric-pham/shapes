package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.ProfilePage;

public class ProfilePagePresenter {

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
}
