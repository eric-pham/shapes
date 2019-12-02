package com.group0578.hpgame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.level1.FlyingActivity;
import com.group0578.hpgame.model.SQLiteHelper;

/**
 * GameOverActivity called when users lose the game by losing all of their lives (game statistic)
 */
public class GameOverActivity extends AppCompatActivity {

    /**
     * The sql database helper that has methods that can operate on the database.
     */
    private SQLiteHelper sqlHelper = new SQLiteHelper(this);

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
        setContentView(R.layout.activity_game_over);

        // extracts the information that was passed from the previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // the username of the user currently logged in
            username = extras.getString("username");
        }

        final TextView messageTextView = findViewById(R.id.sorryMessage);
        String message = "Sorry %s!\nYou've lost the game.\nPlease choose an option:";
        messageTextView.setText(String.format(message, username));

        setComponentColours();
        this.sqlHelper.resetDefaults(this.username);
    }

    /**
     * Changes the background and text colour depending on the colour scheme.
     */
    private void setComponentColours() {
        String colourScheme = sqlHelper.findColourScheme(username);
        if (colourScheme.equalsIgnoreCase("Light")) {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 204, 212, 255));
            ((TextView) findViewById(R.id.sorryMessage)).setTextColor(Color.argb(255, 68, 0, 102));
        } else {
            getWindow().getDecorView().setBackgroundColor(Color.argb(255, 0, 51, 153));
            //      ((TextView) findViewById(R.id.level2_congrats_message_textView))
            //              .setTextColor(Color.argb(255, 255, 179, 204));
            ((TextView) findViewById(R.id.sorryMessage)).setTextColor(Color.argb(255, 239, 222, 205));
        }
    }

    /**
     * Starts a new game by starting the Level 1 FlyingActivity.
     *
     * @param view the view displaying this activity.
     */
    public void onClickPlayAgain(View view) {
        Intent level1 = new Intent(this, FlyingActivity.class);
        level1.putExtra("username", this.username);
        startActivity(level1);
        finish();
    }

    /**
     * Returns the user to the ProfilePageActivity.
     *
     * @param view the view displaying this activity.
     */
    public void onClickProfilePageReturn(View view) {
        Intent profile = new Intent(this, ProfilePageActivity.class);
        profile.putExtra("username", this.username);
        startActivity(profile);
        finish();
    }

}
