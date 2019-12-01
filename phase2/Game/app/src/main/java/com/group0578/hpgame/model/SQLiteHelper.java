package com.group0578.hpgame.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class was derived from Tech Academy's "Login and Signup - SQLite Database" 3 part Tutorial
 * and modified to suit the needs of our project
 * <p>
 * Sources:
 * Part 1: https://www.youtube.com/watch?v=NT1qxmqH1eM
 * Part 2: https://www.youtube.com/watch?v=KxlLsk5j3rY
 * Part 3: https://www.youtube.com/watch?v=A6Jq7NVBVxU
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    /**
     * Metadata for the table representing the database
     */
    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "users.db";
    private static final String TABLE_NAME = "users";

    /**
     * Columns of the users database with name of column
     */
    private static final String COLUMN_ID = "id";

    /**
     * Column for user account username
     */
    private static final String COLUMN_USERNAME = "username";

    /**
     * Column for user account password
     */
    private static final String COLUMN_PASSWORD = "password";

    /**
     * Column for a user account's preferred level difficulty
     */
    private static final String COLUMN_LEVEL_DIFFICULTY = "levelDifficulty";

    /**
     * Column for a user account's preferred colour scheme
     */
    private static final String COLUMN_COLOUR_SCHEME = "colourScheme";

    /**
     * Column for a user account's level one completion time in seconds
     */
    private static final String COLUMN_LEVEL_ONE_TIME = "levelOneTime";

    /**
     * Column for a user account's level two completion time in seconds
     */
    private static final String COLUMN_LEVEL_TWO_TIME = "levelTwoTime";

    /**
     * Column for a user account's level three completion time in seconds
     */
    private static final String COLUMN_LEVEL_THREE_TIME = "levelThreeTime";

    /**
     * Column for a user account's number of lives leftover from the previous game
     */
    private static final String COLUMN_CURRENT_LIVES = "currLives";

    /**
     * Column for a user account's most recently completed level
     */
    private static final String COLUMN_PROGRESS = "progress";

    /**
     * Column representing a boolean that's true if the user did not just create an account
     */
    private static final String COLUMN_RETURNING_USER = "returningUser";

    /**
     * Column for a user account's custom character appearance.
     */
    private static final String COLUMN_CHARACTER = "character";

    /**
     * Column for a user's high score after completing the game.
     */
    private static final String COLUMN_USER_SCORE = "score";

    /**
     * SQLiteDatabase object
     */
    private SQLiteDatabase db;

    /**
     * String with table with appropriate columns
     */
    private static final String TABLE_CREATED =
            "create table users (id integer primary key not null, "
                    + "username text not null, "
                    + "password text not null,"
                    + "levelDifficulty text not null, "
                    + "colourScheme text not null, "
                    + "character text not null,"
                    + "levelOneTime real not null, "
                    + "levelTwoTime real not null, "
                    + "levelThreeTime real not null, "
                    + "currLives integer not null,"
                    + "progress text not null, "
                    + "score integer not null)";

    /**
     * Constructor for SQLiteHelper
     *
     * @param context the current Context used to initialize this object.
     */
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Called when the database is initially created
     *
     * @param db database passed in
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATED);
        this.db = db;
    }

    /**
     * Called when the database requires updating
     *
     * @param sqLiteDatabase database
     * @param i              old version
     * @param j              new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    /**
     * Insert a new user into the database based on SQLiteManager information
     *
     * @param sqlManager contains username and password to be inserted
     */
    public void insertUser(SQLiteManager sqlManager) {
        System.out.println("Inserted");
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        String query = "select * from users";
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cValues.put(COLUMN_ID, count);
        cValues.put(COLUMN_USERNAME, sqlManager.getUsername());
        cValues.put(COLUMN_PASSWORD, sqlManager.getPassword());
        cValues.put(COLUMN_LEVEL_DIFFICULTY, sqlManager.getLevelDifficulty());
        cValues.put(COLUMN_COLOUR_SCHEME, sqlManager.getColourScheme());
        cValues.put(COLUMN_LEVEL_ONE_TIME, sqlManager.getLevelOneTime());
        cValues.put(COLUMN_LEVEL_TWO_TIME, sqlManager.getLevelTwoTime());
        cValues.put(COLUMN_LEVEL_THREE_TIME, sqlManager.getLevelThreeTime());
        cValues.put(COLUMN_CURRENT_LIVES, sqlManager.getCurrLives());
        cValues.put(COLUMN_PROGRESS, sqlManager.getProgress());
        cValues.put(COLUMN_CHARACTER, sqlManager.getCharacter());
        cValues.put(COLUMN_USER_SCORE, sqlManager.getScore());

        db.insert(TABLE_NAME, null, cValues);
        cursor.close();
        System.out.println("Inserted");
    }

    /**
     * Returns the password with a given username, "Cannot find pass" otherwise.
     *
     * @param username username to search for in the table
     * @return password associated with the username or "Cannot find pass"
     */
    public synchronized String findPassword(String username) {
        System.out.println("FIndpassword method 1");
        db = this.getReadableDatabase();

        System.out.println("FIndpassword method 2");
        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String user, pass;

        System.out.println("FIndpassword method 3");

        pass = "Cannot find pass";
        if (cursor.moveToFirst()) {
            do {
                user = cursor.getString(0);

                if (user.equals(username)) {
                    pass = cursor.getString(1);
                    db.close();
                    break;
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return pass;
    }

    /**
     * Checks whether the username is already in the database.
     *
     * @param username1 the user's desired username
     * @return true if the username is already in the database, false otherwise
     */
    public synchronized boolean checkDuplicates(String username1) {
        System.out.println("duplicates checked.");
        db = this.getReadableDatabase();

        String username2;

        String query = "select username from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                username2 = cursor.getString(0);

                if (username2.equals(username1)) {
                    db.close();
                    return true; // there are duplicate user names
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false; // there are no duplicate user names
    }

    /**
     * Finds the primary key id column in the same row as the username column username1
     *
     * @param username1 the username of the user currently logged in
     * @return ID column value for the user with username username1
     */
    private synchronized int findID(String username1) {
        System.out.println("SQLiteHelper.findID method reached");
        db = this.getReadableDatabase();

        String query = "select id, username from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String username2;
        int ID = -1;

        if (cursor.moveToFirst()) {
            do {
                username2 = cursor.getString(1);

                if (username2.equals(username1)) {
                    ID = cursor.getInt(0);
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ID;
    }

    /**
     * Finds the colour scheme associated with the logged in user. Checks whether the username is
     * already in the database.
     *
     * @param username1 the user's username
     * @return a String: 'Light' or 'Dark'.
     */
    public synchronized String findColourScheme(String username1) {

        System.out.println("colour scheme found.");
        db = this.getReadableDatabase();
        String username2;

        String query = "select username, colourScheme from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String colourScheme = "Light";

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);

            if (username2.equals(username1)) {
                colourScheme = cursor.getString(1); // gets the colour scheme: 'Light' or 'Dark'
                break; // the colour scheme has been found
            }
        } while (cursor.moveToNext());
        cursor.close();
        return colourScheme;
    }

    /**
     * Finds the game difficulty associated with the logged in user.
     *
     * @param username1 the user's username
     * @return a String: 'Easy' or 'Hard'.
     */
    public synchronized String findDifficulty(String username1) {
        System.out.println("difficulty found");
        db = this.getReadableDatabase();

        String username2;

        String query = "select username, levelDifficulty from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String difficulty = "Easy";

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);

            if (username2.equals(username1)) {
                difficulty = cursor.getString(1); // gets the difficulty: 'Easy' or 'Hard'
                break; // difficulty found
            }
        } while (cursor.moveToNext());
        cursor.close();
        return difficulty;
    }

    /**
     * Finds the game character associated with the logged in user.
     *
     * @param username1 the user's username
     * @return a String: 'Circle' or 'Square'.
     */
    public synchronized String findCharacter(String username1) {
        System.out.println("character found");
        db = this.getReadableDatabase();

        String username2;

        String query = "select username, character from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String character = "Circle";

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);

            if (username2.equals(username1)) {
                character = cursor.getString(1); // gets the character: 'Circle' or 'Square'
                break; // character found
            }
        } while (cursor.moveToNext());
        cursor.close();
        return character;
    }

    /**
     * Finds the last level completed by the logged in user.
     *
     * @param username1 the user's username
     * @return a String: 'none', 'one', 'two' or 'three'.
     */
    public synchronized String findProgress(String username1) {
        System.out.println("Progress found");
        db = this.getReadableDatabase();

        String username2;

        String query = "select username, progress from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String progress = "one";    // progress by default is the first level

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);

            if (username2.equals(username1)) {
                progress = cursor.getString(1); // gets the progress
                break; // progress found
            }
        } while (cursor.moveToNext());
        cursor.close();
        return progress;
    }

    /**
     * Finds the number of lives left for the user currently logged in and playing the game.
     *
     * @param username1 the username of the user in this database to find
     * @return integer for number of lives remaining
     */
    public synchronized int findLives(String username1) {
        System.out.println("Method SQLiteHelper.findLives() reached");
        db = this.getReadableDatabase();

        String query = "select username, currLives from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String username2;
        int currLives = -1;

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);
            if (username2.equals(username1)) {
                currLives = cursor.getInt(1); // gets the current number of lives
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();
        return currLives;
    }

    /**
     * Finds the current high score (most points earned in a game) for the user logged in
     *
     * @param username1 the username of the user logged in.
     * @return integer score for this user.
     */
    public synchronized int findScore(String username1) {
        System.out.println("Method SQLiteHelper.findScore() reached");
        db = this.getReadableDatabase();

        String query = "select username, score from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String username2;
        int score = 0;

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);
            if (username2.equals(username1)) {
                score = cursor.getInt(1); // gets the current score for the user.
                break;
            }
        } while (cursor.moveToNext());
        cursor.close();
        return score;
    }

    /**
     * Finds the levelOneTime, levelTwoTime, and levelThreeTime to calculate the user's score
     * after they have won the game.
     *
     * @param username1 the username of the user who just finished the game
     * @return double array of length 3 containing user's times to complete each level in seconds.
     */
    private double[] findTimes(String username1) {
        System.out.println("Progress found");
        db = this.getReadableDatabase();

        String username2;

        String query = "select username, levelOneTime, levelTwoTime, levelThreeTime from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        double[] times = new double[3];

        cursor.moveToFirst();
        do {
            username2 = cursor.getString(0);

            if (username2.equals(username1)) {
                times[0] = cursor.getInt(1);
                times[1] = cursor.getInt(2);
                times[2] = cursor.getInt(3);
                break; // times have been found -- don't need to continue looping over database
            }
        } while (cursor.moveToNext());
        return times;
    }

    /**
     * Updates the database by changing the String value representing the logged in user's preferred
     * colour scheme.
     *
     * @param username     the logged in user.
     * @param colourScheme the string representing the user's preferred colour scheme.
     */
    public synchronized void setColourScheme(String username, String colourScheme) {
        System.out.println("Method SQLiteHelper.setColourScheme() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_COLOUR_SCHEME, colourScheme);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        System.out.println("New colour scheme: " + colourScheme);
    }

    /**
     * Updates the database by changing the String value representing the logged in user's preferred
     * game difficulty.
     *
     * @param username        the logged in user.
     * @param levelDifficulty the string representing the user's preferred game difficulty.
     */
    public synchronized void setDifficulty(String username, String levelDifficulty) {
        System.out.println("Method SQLiteHelper.setDifficulty() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_DIFFICULTY, levelDifficulty);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        System.out.println("New difficulty: " + levelDifficulty);
    }

    /**
     * Updates the database by changing the String value representing the logged in user's preferred
     * character.
     *
     * @param username  the logged in user.
     * @param character the string representing the user's preferred character.
     */
    public synchronized void setCharacter(String username, String character) {
        System.out.println("Method SQLiteHelper.setCharacter() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_CHARACTER, character);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        System.out.println("New character: " + character);
    }

    /**
     * Updates the database by changing the String value representing the logged in user's most
     * recently completed level in the game.
     *
     * @param username the logged in user.
     * @param level    the string representing the user's most recently completed level in the game.
     *                 should be either "one", "two" or "three"
     */
    public synchronized void setProgress(String username, String level) {
        System.out.println("Method SQLiteHelper.setColourScheme() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_PROGRESS, level);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        System.out.println("Completed level: " + level);
    }

    /**
     * Updates the database by recording the user's most recent time to complete the Flying Level
     * (level 1).
     *
     * @param username  the logged in user.
     * @param totalTime the time taken to complete level 2
     */
    public synchronized void setLevelOneTime(String username, double totalTime) {
        System.out.println("Method SQLiteHelper.setLevelOneTime reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_ONE_TIME, totalTime);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
    }

    /**
     * Updates the database by recording the user's most recent time to complete the maze (level 2).
     *
     * @param username  the logged in user.
     * @param totalTime the time taken to complete level 2
     */
    public synchronized void setLevelTwoTime(String username, double totalTime) {
        System.out.println("Method SQLiteHelper.setLevelTwoTime reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_TWO_TIME, totalTime);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
    }

    /**
     * Updates the database by recording the user's most recent time to complete level 3.
     *
     * @param username  the logged in user.
     * @param totalTime the time taken to complete level 2
     */
    public synchronized void setLevelThreeTime(String username, double totalTime) {
        System.out.println("Method SQLiteHelper.setLevelThreeTime reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_THREE_TIME, totalTime);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
    }

    /**
     * Updates the database by recording the number of lives remaining for the user who's
     * currently logged in and playing the game.
     *
     * @param username the username of the user playing the game
     * @param lives    the new number of lives left for the user playing.
     */
    public synchronized void setLives(String username, int lives) {
        System.out.println("Method SQLiteHelper.setLives() reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_CURRENT_LIVES, lives);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
    }

    /**
     * Updates the database by recording the logged in user's new high score if it beats (is
     * larger than) their old high score.
     *
     * @param username the username of the user currently logged in.
     * @param score    the new score for the user logged in.
     */
    public synchronized void setScore(String username, int score) {
        System.out.println("SQLiteHelper.setUserScore() method reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_USER_SCORE, score);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
    }

    /**
     * Resets the game progress, user lives, and the times for each level.
     *
     * @param username the logged in user.
     */
    public void resetDefaults(String username) {
        System.out.println("Method SQLiteHelper.resetDefaults() reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_ONE_TIME, 0.0);
        cValues.put(COLUMN_LEVEL_TWO_TIME, 0.0);
        cValues.put(COLUMN_LEVEL_THREE_TIME, 0.0);
        cValues.put(COLUMN_CURRENT_LIVES, 10);
        cValues.put(COLUMN_PROGRESS, "none");
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
    }

    /**
     * Generates a TreeMap object sorted from least to greatest user scores where keys are
     * integers representing users' scores and values are strings for the users' username.
     *
     * @return TreeMap<Integer, String> object of (user scores, username)
     */
    public TreeMap<Integer, String> findAllScores() {
        Map<Integer, String> userScores = new HashMap<>();
        db = this.getReadableDatabase();

        String query = "select username, score from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();
        do {
            userScores.put(cursor.getInt(1), cursor.getString(0));
        } while (cursor.moveToNext());
        cursor.close();

        return new TreeMap<>(userScores);
    }

    /**
     * Calculates the new high score for the user logged in who just won the game.
     * If the user beats their previous best score, then the database is updated with the new score.
     */
    public void saveNewScore(String username) {
        double[] allTimes = findTimes(username);
        double totalTime = allTimes[0] + allTimes[1] + allTimes[2];
        String difficulty = findDifficulty(username);
        int livesLeft = findLives(username);
        // User
        int totalScore = 10000 - (int) Math.floor((totalTime / 10) * 100);

        // Reducing the number of points based on how many lives user has left at end of game.
        if (difficulty.equalsIgnoreCase("Easy")) { // total possible lives is 10
            totalScore *= (livesLeft / 10);
        } else {    // total possible lives is 5
            totalScore *= (livesLeft / 5);
        }

        if (totalScore > findScore(username)) { // new score beats previous best
            setScore(username, totalScore);
        }
    }

    /**
     * Returns a boolean specifying whether the user with username has a high enough score to be
     * on the scoreboard.
     *
     * @param username the username of the user logged in.
     * @return true if the user is on the scoreboard, else false
     */
    public boolean userOnScoreboard(String username) {
        TreeMap<Integer, String> allScores = findAllScores();
        if (allScores.size() <= 20) {
            return true;
        } else {
            ArrayList<String> usernames = (ArrayList<String>) allScores.values();
            Collections.reverse(usernames);
            for (int i = 0; i < 20; i++) {
                if (usernames.get(i).equals(username)) {
                    return true;
                }
            }
            return false;
        }
    }
}
