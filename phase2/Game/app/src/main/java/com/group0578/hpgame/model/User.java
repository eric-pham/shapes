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
     * The amount of time this user takes to complete levels 1, 2, and 3 of the game currently
     * being played.
     */
    private Timer levelOneTime, levelTwoTime, levelThreeTime;


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

    /**
     * Initialize the amount of time taken to complete level one.
     * @param levelOneTime the timer object used to keep track of level one's completion time.
     */
    public void setLevelOneTime(Timer levelOneTime) {
        this.levelOneTime = levelOneTime;
    }

    /**
     * Initialize the amount of time taken to complete level two.
     * @param levelTwoTime the timer object used to keep track of level two's completion time.
     */
    public void setLevelTwoTime(Timer levelTwoTime) {
        this.levelTwoTime = levelTwoTime;
    }

    /**
     * Initialize the amount of time taken to complete level three.
     * @param levelThreeTime the timer object used to keep track of level three's completion time.
     */
    public void setLevelThreeTime(Timer levelThreeTime) {
        this.levelThreeTime = levelThreeTime;
    }

    /**
     * Calculating the total amount of time to complete the game being played by this user.
     * @return Timer object representing total time completion of the game.
     */
    public Timer calculateTotalTime() {
        int levelOne = this.levelOneTime.getTotalSeconds();
        int levelTwo = this.levelTwoTime.getTotalSeconds();
        int levelThree = this.levelThreeTime.getTotalSeconds();
        return new Timer(levelOne + levelTwo + levelThree);
    }



}
