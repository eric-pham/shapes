package com.group0578.hpgame.level1;

import com.group0578.hpgame.model.SQLiteHelper;

/**
 * Facilitates access and modification to database data
 */
class FlyingInteractor {
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
     * String representing the username of the user
     */
    private String username;

    /**
     * SQLiteHelper object to update and find items form the Database
     */
    private SQLiteHelper sqLiteHelper;

    /**
     * Create a new FlyingInteractor with the given SQLiteHelper and username. This class stores
     * and returns the user's information from the database required to run the level.
     *
     * @param sqlHelper Helper to get data from database.
     * @param username  username of the user we're storing info from.
     */
    FlyingInteractor(SQLiteHelper sqlHelper, String username) {
        this.username = username;
        this.sqLiteHelper = sqlHelper;
        this.theme = sqlHelper.findColourScheme(username);
        this.lives = sqlHelper.findLives(username);
        this.character = sqlHelper.findCharacter(username);
        this.difficulty = sqlHelper.findDifficulty(username);
    }


    /**
     * Get lives from the data base and return it as an int
     *
     * @return number of lives
     */
    int getLives() {
        return this.lives;
    }

    /**
     * Get the theme from the data base and return it as a string
     *
     * @return a String: 'Light' or 'Dark'.
     */
    String getTheme() {
        return this.theme;
    }

    /**
     * Get the character the user selected
     *
     * @return a String: 'Circle' or 'Square'.
     */
    String getCharacter() {
        return this.character;
    }


    /**
     * Returns the difficulty of the game.
     *
     * @return a String: 'Easy' or 'Hard'
     */
    String getDifficulty() {
        return this.difficulty;
    }

    /**
     * Updates the database by recording stats from this level
     *
     * @param lives the new number of lives left for the user playing
     * @param time  total time it took to complete the level
     */
    void updateDatabase(int lives, double time, String progress) {
        sqLiteHelper.setLevelOneTime(this.username, time);
        sqLiteHelper.setLives(this.username, lives);
        sqLiteHelper.setProgress(this.username, progress);
    }


}
