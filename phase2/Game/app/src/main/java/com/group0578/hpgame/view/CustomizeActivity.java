package com.group0578.hpgame.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.CustomizePresenter;

/**
 * Responsible for handling user actions like button clicks on the Customization page
 */
public class CustomizeActivity extends AppCompatActivity implements Customize.View {

    /**
     * The presenter associated with this View that handles the user's interactions with the UI.
     */
    CustomizePresenter customizePresenter;

    /**
     * The username belonging to the user currently logged in and viewing the profile page.
     */
    private String username;

    /**
     * Called immediately when the activity is started.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        // Presenter stores reference of this CustomizeActivity as its View (send 'this')
        customizePresenter = new CustomizePresenter(this);

        // extracts the information that was passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            this.username = extras.getString("username");
        }
    }
}
