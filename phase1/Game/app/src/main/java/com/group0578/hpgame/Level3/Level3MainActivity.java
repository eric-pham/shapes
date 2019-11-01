package com.group0578.hpgame.Level3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.View;

import com.group0578.hpgame.R;


public class Level3MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.level3);
    }

    public void createBlast(View view){
        Manager manager = ScreenView.getRoomManager();
        manager.createBlast();
    }

    public void moveWandRight(View view){
        Manager manager = ScreenView.getRoomManager();
        manager.moveWandRight();
    }

    public void moveWandLeft(View view){
        Manager manager = ScreenView.getRoomManager();
        manager.moveWandLeft();
    }

}
