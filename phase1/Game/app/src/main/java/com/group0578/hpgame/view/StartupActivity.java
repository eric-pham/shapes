package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.StartupPresenter;

/**
 * Displays the start up screen.
 */
public class StartupActivity extends AppCompatActivity implements Startup.View {

    private StartupPresenter startupPresenter;

    /**
     * The constructor for this View.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        startupPresenter = new StartupPresenter(this);
    }

    /**
     * Executed when the login button on the start up screen is clicked.
     */
    public void onClickLoginButton(View view) {
        startupPresenter.createLoginScreen();
    }

    /**
     * Starts the activity that displays the login screen.
     * @param loginIntent an instance of the LoginActivity view.
     */
    public void goToLoginScreen(Intent loginIntent) {
        System.out.println("Method reached");
        startActivity(loginIntent);
    }

    /**
     * Executed when the create account button on the start up screen is clicked.
     */
    public void onClickCreateUserButton(View view){
        startupPresenter.createNewAccountScreen();
    }

    /**
     * Starts the activity that displays the create user screen.
     * @param createUserIntent an instance of the CreateUserActivity view.
     */
    public void goToCreateUserScreen(Intent createUserIntent) {
        System.out.println("Method reached 2");
        startActivity(createUserIntent);
    }



}
