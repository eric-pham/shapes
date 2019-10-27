package com.group0578.hpgame.presenter;

import com.group0578.hpgame.view.CreateUser;

/**
 * Responsible for handling actions associated with the View for CreateUserActivity
 * and updating the screen's visual component.
 */
public class CreateUserPresenter implements CreateUser.Presenter {

    /**
     * CreateUser.View interface reference
     */
    private CreateUser.View createUserView;

    /**
     * Initializes this class
     * @param createUserView the View responsible for CreateUserActivity
     */
    public CreateUserPresenter(CreateUser.View createUserView) {
        // Initialized to instance of CreateUserActivity object
        this.createUserView = createUserView;
    }

    /**
     * This method creates a new intent (the next screen after creating a new account)
     * UNFINISHED --- Decide what to do later.
     */
    public void createAccount() {

    }


}
