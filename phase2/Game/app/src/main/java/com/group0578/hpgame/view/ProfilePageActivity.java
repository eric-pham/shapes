package com.group0578.hpgame.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.group0578.hpgame.R;
import com.group0578.hpgame.presenter.ProfilePagePresenter;

public class ProfilePageActivity extends AppCompatActivity implements ProfilePage.View {

  ProfilePagePresenter profilePagePresenter;
  /** The presenter associated with this View that handles the user's interactions with the UI. */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile_page);
    profilePagePresenter = new ProfilePagePresenter(this);
  }


}
