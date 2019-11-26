package com.group0578.hpgame.model;

/**
 * User object represents a player in the game.
 * Stores information about this user's customization preferences, and current game statistics.
 */
public class User {

    /**
     * The username for this user's account.
     */
    private String username;

    /**
     * The password for this user's account for logging in.
     */
    private String password;

    /**
     * This user's customization preference for the colour scheme appearance of the game.
     * Must be "Light" or "Dark"; default setting is "Light".
     */
    private String customColourScheme;

    /**
     * This user's customization preference for how difficult each level of the game is.
     * Must be "Easy" or "Hard"; default setting is "Easy".
     */
    private String levelDifficulty;

    /**
     * The number of lives remaining for this user's player in the game currently being played.
     * Game difficulty: "Easy" -> 10 lives to start, "Hard" -> 5 lives to start.
     */
    private int numLivesLeft;




    /**
     * Constructing a new User instance.
     * @param username the username for logging in to this user's account
     * @param password the password for logging in to this user's account.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;

        // Initializing default customization settings
        this.customColourScheme = "Light";
        this.levelDifficulty = "Easy";
        this.numLivesLeft = 10;
    }

    /**
     * Getter for this user's username
     * @return the username for this user.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter for this user's password.
     * @return the password for this user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Updating the user's preferences for the colour scheme customization.
     * @param newColourScheme the new colour scheme "Light" or "Dark" as selected by this user.
     */
    public void setCustomColourScheme(String newColourScheme) {
        // Only update colour scheme if different from previous setting.
        if (!this.customColourScheme.equals(newColourScheme)) {
            this.customColourScheme = newColourScheme;
        }
    }

    /**
     * Updating the user's preferences for the level of difficulty customization.
     * @param newLevelDifficulty the level of difficulty "Easy" or "Hard" as selected by this user.
     */
    public void setLevelDifficulty(String newLevelDifficulty) {
        // Only update if level difficulty is different from previous setting.
        if (!this.levelDifficulty.equals(newLevelDifficulty)) {
            this.levelDifficulty = newLevelDifficulty;
        }
    }

    /**
     * Decreasing number of lives left for this user's player by removing 1 life.
     */
    public void removeLife() {
        this.numLivesLeft -= 1;
    }


}
