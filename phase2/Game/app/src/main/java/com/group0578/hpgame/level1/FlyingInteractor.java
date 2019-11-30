package com.group0578.hpgame.level1;

import com.group0578.hpgame.model.SQLiteHelper;

public class FlyingInteractor {
    /**
     * String representing the theme, either 'Light' or 'Dark'
     */
    private String theme;


    /**
     * int representing the number of lives
     */
    private int lives;

    /**
     * String representing the theme, either 'Circle' or 'Square'
     */
    private String character;

    /**
     * String representing the difficulty
     */
    private String difficulty;

    /**
     * Create a new FlyingInteractor with the given SQLiteHelper and username. This class stores
     * and returns the user's information from the database required to run the level.
     *
     * @param sqlHelper Helper to get data from database.
     * @param username  username of the user we're storing info from.
     */
    FlyingInteractor(SQLiteHelper sqlHelper, String username) {
        this.theme = sqlHelper.findColourScheme(username);
        this.lives = sqlHelper.findLives(username);
        this.character = sqlHelper.findCharacter(username);
        this.difficulty =sqlHelper.findDifficulty(username);
    }


    /**
     * Get lives from the data base and return it as an int
     *
     * @return number of lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Get the theme from the data base and return it as a string
     *
     * @return a String: 'Light' or 'Dark'.
     */
    public String getTheme() {
        return this.theme;
    }

    /**
     * Get the character the user selected
     *
     * @return a String: 'Circle' or 'Square'.
     */
    public String getCharacter() {
        return this.character;
    }

    public String getDifficulty(){
        return this.difficulty;
    }

    // Need to add update database methods for when game is over, will add after transitions are complete

}
