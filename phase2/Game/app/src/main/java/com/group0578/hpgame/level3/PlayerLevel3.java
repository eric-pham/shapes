package com.group0578.hpgame.level3;

import com.group0578.hpgame.model.SQLiteHelper;

public class PlayerLevel3 {
    private int lives;
    private String character;
    private String difficulty;

    PlayerLevel3(SQLiteHelper SqlHelper, String username) {
        this.lives = SqlHelper.findLives(username);
        this.character = SqlHelper.findCharacter(username);
        this.difficulty = SqlHelper.findDifficulty(username);
    }

    public int getLives() {
        return this.lives;
    }

    public String getCharacter() {
        return this.character;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    void reduceLives() {
        this.lives = this.lives - 1;
    }

}
