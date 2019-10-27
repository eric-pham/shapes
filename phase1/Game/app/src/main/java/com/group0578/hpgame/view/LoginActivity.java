package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.LoginPresenter;

/**
 * Displays the login screen.
 */
public class LoginActivity extends AppCompatActivity implements Login.View {

    /**
     * The presenter associated with this View that handles the user's interactions with the UI.
     */
    private LoginPresenter loginPresenter;

    /**
     * The constructor for the activity that displays the Login Screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);


    }

    /**
     * When login button is clicked, check if username and password are valid
     */
    public void onClickLogin(View v) {
        //Get username and password from user inputted EditText
        final EditText loginUser = findViewById(R.id.loginUser);
        String username = loginUser.getText().toString();

        final EditText loginPassword = findViewById(R.id.loginPassword);
        String password = loginPassword.getText().toString();

        //Call presenter to verify username and password
        if (loginPresenter.checkLogin(username, password)) {
            //If checks out do something
            System.out.println("Valid");
        } else{
            //if invalid do something
            System.out.println("Invalid");
        }
    }
}
