package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group0578.hpgame.CreateAccountActivity;
import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.StartupPresenter;

/**
 * Displays the start up screen.
 */
public class StartupActivity extends AppCompatActivity implements Startup.View {

    private StartupPresenter startupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        startupPresenter = new StartupPresenter(this);
    }

    public void onClickLoginButton(View view) {
        startupPresenter.createLoginScreen();
    }

    public void goToLoginScreen(Intent loginIntent) {
        System.out.println("Method reached");
        startActivity(loginIntent);
    }

    public void onClickCreateAccountButton(View view){
        startupPresenter.createNewAccountScreen();
    }

    public void goToCreateAccountScreen(Intent createAccountIntent) {
        System.out.println("Method reached 2");
        startActivity(createAccountIntent);
    }



}
