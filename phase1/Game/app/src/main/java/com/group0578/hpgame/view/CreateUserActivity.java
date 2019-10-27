package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.group0578.hpgame.presenter.CreateUserPresenter;
import com.group0578.hpgame.R;

/**
 * Displays the create account screen.
 */
public class CreateUserActivity extends AppCompatActivity implements CreateUser.View {

    /**
     * The Presenter managing the actions behind this CreateUserActivity View
     */
    private CreateUserPresenter createUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        // Presenter stores reference of this CreateUserActivity (send 'this')
        createUserPresenter = new CreateUserPresenter(this);
    }

    /**
     * Called when the 'confirm' button is clicked
     * @param view the View of the 'Create Account' screen (activity_create_userl)
     */
    public void onClickConfirmButton(View view) {
        createUserPresenter.createAccount();
    }


    /**
     * ALL METHODS BELOW:
     * Must be moved to CreateUserPresenter class -- methods responsible for screen actions
     */


    public void confirmAccount() {
        String username = ((TextView) findViewById(R.id.userName)).getText().toString();
        String password = ((TextView) findViewById(R.id.userPassword)).getText().toString();

        /*if (username.equals("") || password.equals("")) {

        } else if (userExists(username, password)) {

        } else {

        }*/

        addAccount(username, password);
    }

//    private boolean userExists(String userName, String userPassword) {
//
//    }

    private void addAccount(String userName, String userPassword) {

    }

}
