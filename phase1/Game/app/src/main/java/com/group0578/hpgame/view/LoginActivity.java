package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.LoginPresenter;

/**
 * Displays the login screen.
 */
public class LoginActivity extends AppCompatActivity implements Login.View {

    /**
     * Displays the login screen.
     */
    private LoginPresenter loginPresenter;

    /**
     * Behaviour of the StartupActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
    }
}
