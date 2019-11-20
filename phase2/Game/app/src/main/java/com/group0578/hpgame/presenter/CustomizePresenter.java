package com.group0578.hpgame.presenter;

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

}
