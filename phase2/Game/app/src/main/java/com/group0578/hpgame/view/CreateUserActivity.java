package com.group0578.hpgame.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.group0578.hpgame.model.SQLiteHelper;

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

    SQLiteHelper sqlHelper = new SQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        // Presenter stores reference of this CreateUserActivity (send 'this')
        createUserPresenter = new CreateUserPresenter(this);
    }

    /**
     *  When create user button is clicked add information to data
     */
    public void onClickConfirm(View v) {
        //Get username and password from user inputted EditText
        final EditText userNameCreate = findViewById(R.id.userNameCreate);
        String username = userNameCreate.getText().toString();

        final EditText userPasswordCreate = findViewById(R.id.userPasswordCreate);
        String password = userPasswordCreate.getText().toString();

        //Debug purposes you can remove this later
        System.out.println(username);
        System.out.println(password);

        //Call CreateUserPresenter method here you can pass in the local variables
        //username and password
        if (createUserPresenter.checkDuplicates(sqlHelper, username)) { // there is a duplicate
            // after button is clicked, hides keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(findViewById(R.id.constraintLayoutCreateUser).getWindowToken(), 0);

            // display error message
            findViewById(R.id.errorMessageCreateUser).setVisibility(View.VISIBLE);
        } else {  // there is no duplicate
            createUserPresenter.createAccount(sqlHelper, username, password);  // create account
            createUserPresenter.createProfileScreen(username);
        }

    }

    /**
     * Starts the ProfilePageActivity.
     *
     * @param profileIntent the intent for the ProfilePageActivity
     */
    @Override
    public void goToProfilePage(Intent profileIntent) {
        System.out.println("Testing: Reached method CreateUserActivity.goToProfilePage ");
        startActivity(profileIntent);
        finish();
    }
}
