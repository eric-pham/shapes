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

    /**
     * The presenter associated with this View that handles the user's interactions with the UI.
     */
    private StartupPresenter startupPresenter;

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

    public void onClickStage1(View view){
        startupPresenter.createNewStage1Screen();
    }

    public void goToStage1Screen(Intent stage1Intent) {
        System.out.println("Method reached 2");
        startActivity(stage1Intent);
    }

    public void onClickStage3(View view){
        startupPresenter.createNewStage3Screen();
    }

    public void goToStage3Screen(Intent stage3Intent) {
        System.out.println("Method reached 2");
        startActivity(stage3Intent);
    }

    // temporary ,method to make testing of level 2 (the maze) easier
    public void goToStage2Screen(Intent stage2Intent) {
        System.out.println("Method reached 2");
        startActivity(stage2Intent);
    }

    // temporary ,method to make testing of level 2 (the maze) easier
    public void onClickStage2(View view) {
        startupPresenter.createNewStage2Screen();
    }
}
