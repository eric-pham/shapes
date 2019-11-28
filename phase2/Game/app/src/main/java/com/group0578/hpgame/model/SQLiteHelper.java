package com.group0578.hpgame.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

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
     * SQLiteDatabase object
     */
    private SQLiteDatabase db;

    /**
     * String with table with appropriate columns
     */
    private static final String TABLE_CREATED =
            "create table users (id integer primary key not null, username text not null, password text not null,"
                    + "levelDifficulty text not null , colourScheme text not null, levelOneTime real not null, "
                    + "levelTwoTime real not null, levelThreeTime real not null, currLives integer not null,"
                    + "progress text not null, returningUser integer not null, character text not null)";

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
        cValues.put(COLUMN_RETURNING_USER, sqlManager.getReturningUser());
        cValues.put(COLUMN_CHARACTER, sqlManager.getCharacter());

        db.insert(TABLE_NAME, null, cValues);
        db.close();
        cursor.close();
        System.out.println("Inserted");
    }

    /**
     * Returns the password with a given username, "Cannot find pass" otherwise.
     *
     * @param username username to search for in the table
     * @return password associated with the username or "Cannot find pass"
     */
    public String findPassword(String username) {
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
        db.close();
        cursor.close();
        return pass;
    }

    /**
     * Checks whether the username is already in the database.
     *
     * @param username1 the user's desired username
     * @return true if the username is already in the database, false otherwise
     */
    public boolean checkDuplicates(String username1) {
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
        db.close();
        cursor.close();
        return false; // there are no duplicate user names
    }

    /**
     * Finds the primary key id column in the same row as the username column username1
     *
     * @param username1 the username of the user currently logged in
     * @return ID column value for the user with username username1
     */
    private int findID(String username1) {
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
//    db.close();
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
    public String findColourScheme(String username1) {

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
        db.close();
        cursor.close();
        return colourScheme;
    }

    /**
     * Finds the game difficulty associated with the logged in user.
     *
     * @param username1 the user's username
     * @return a String: 'Easy' or 'Hard'.
     */
    public String findDifficulty(String username1) {
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
        db.close();
        cursor.close();
        return difficulty;
    }

    /**
     * Finds the game character associated with the logged in user.
     *
     * @param username1 the user's username
     * @return a String: 'Circle' or 'Square'.
     */
    public String findCharacter(String username1) {
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
        db.close();
        cursor.close();
        return character;
    }

    /**
     * Finds the last level completed by the logged in user.
     *
     * @param username1 the user's username
     * @return a String: 'one', 'two' or 'three'.
     */
    public String findProgress(String username1) {
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
        db.close();
        cursor.close();
        return progress;
    }

    /**
     * Finds the number of lives left for the user currently logged in and playing the game.
     *
     * @param username1 the username of the user in this database to find
     * @return integer for number of lives remaining
     */
    public int findLives(String username1) {
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
        db.close();
        cursor.close();
        return currLives;
    }

    // temporary just for testing purposes
//    public float findTime(String username1) {
//        System.out.println("Progress found");
//        db = this.getReadableDatabase();
//
//        String username2;
//
//        String query = "select username, levelTwoTime from " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query, null);
//
//        float time = 0;
//
//        cursor.moveToFirst();
//        do {
//            username2 = cursor.getString(0);
//
//            if (username2.equals(username1)) {
//                time = cursor.getInt(1); // gets the progress
//                db.close();
//                break; // progress found
//            }
//        } while (cursor.moveToNext());
//        return time;
//    }

    /**
     * Updates the database by changing the String value representing the logged in user's preferred
     * colour scheme.
     *
     * @param username     the logged in user.
     * @param colourScheme the string representing teh user's preferred colour scheme.
     */
    public void setColourScheme(String username, String colourScheme) {
        System.out.println("Method SQLiteHelper.setColourScheme() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_COLOUR_SCHEME, colourScheme);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
        System.out.println("New colour scheme: " + colourScheme);
    }

    /**
     * Updates the database by changing the String value representing the logged in user's preferred
     * game difficulty.
     *
     * @param username        the logged in user.
     * @param levelDifficulty the string representing the user's preferred game difficulty.
     */
    public void setDifficulty(String username, String levelDifficulty) {
        System.out.println("Method SQLiteHelper.setDifficulty() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_DIFFICULTY, levelDifficulty);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
        System.out.println("New difficulty: " + levelDifficulty);
    }

    /**
     * Updates the database by changing the String value representing the logged in user's preferred
     * character.
     *
     * @param username  the logged in user.
     * @param character the string representing the user's preferred character.
     */
    public void setCharacter(String username, String character) {
        System.out.println("Method SQLiteHelper.setCharacter() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_CHARACTER, character);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
        System.out.println("New character: " + character);
    }

    /**
     * Updates the database by changing the String value representing the logged in user's most
     * recently completed level in the game.
     *
     * @param username the logged in user.
     * @param level    the string representing the user's most recently completed level in the game.
     */
    public void setProgress(String username, String level) {
        System.out.println("Method SQLiteHelper.setColourScheme() reached");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_PROGRESS, level);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
        System.out.println("Completed level: " + level);
    }

    /**
     * Updates the database by recording the user's most recent time to complete the maze (level 2).
     *
     * @param username  the logged in user.
     * @param totalTime the time taken to complete level 2
     */
    public void setLevelTwoTime(String username, double totalTime) {
        System.out.println("Method SQLiteHelper.setLevelTwoTime reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_TWO_TIME, totalTime);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
    }

    /**
     * Updates the database by recording the user's most recent time to complete level 3.
     *
     * @param username  the logged in user.
     * @param totalTime the time taken to complete level 2
     */
    public void setLevelThreeTime(String username, double totalTime) {
        System.out.println("Method SQLiteHelper.setLevelThreeTime reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_LEVEL_THREE_TIME, totalTime);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
    }

    /**
     * Updates the database by recording the number of lives remaining for the user who's
     * currently logged in and playing the game.
     *
     * @param username the username of the user playing the game
     * @param lives    the new number of lives left for the user playing.
     */
    public void setLives(String username, int lives) {
        System.out.println("Method SQLiteHelper.setLives() reached.");
        int ID = this.findID(username);
        db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();

        cValues.put(COLUMN_CURRENT_LIVES, lives);
        db.update(TABLE_NAME, cValues, "id=" + ID, null);
        db.close();
    }
}
