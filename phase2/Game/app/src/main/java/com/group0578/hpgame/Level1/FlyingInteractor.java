package com.group0578.hpgame.Level1;

import com.group0578.hpgame.model.SQLiteHelper;

public class FlyingInteractor {

    private SQLiteHelper sqLiteHelper;
    private String username;

    FlyingInteractor(SQLiteHelper sqlHelper, String username)
    {
        this.sqLiteHelper = sqlHelper;
        this.username = username;
    }

    // Get lives from the data base and return it as an int
    public int getLives(){
        return 0;
    }

    // Get the theme from the data base and return it as a string
    public String getTheme(){
        return "temp";
    }

    // Get the character the user selected
    public String getCharacter(){
        return "temp";
    }

    // Need to add update database methods for when game is over, will add after transitions are complete

}
