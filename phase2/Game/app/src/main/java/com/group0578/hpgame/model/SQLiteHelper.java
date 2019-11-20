package com.group0578.hpgame.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import com.group0578.hpgame.presenter.CreateUserPresenter;

/**
 * This class was derived from Tech Academy's Tutorial and modified to suit the needs of our project
 */
public class SQLiteHelper extends SQLiteOpenHelper {
  /** Metadata for the Database and table */

  private static final int DB_VERSION = 1;

  private static final String DB_NAME = "users.db";
  private static final String TABLE_NAME = "users";

  /** Columns of the table */
  private static final String COLUMN_ID = "id";

  private static final String COLUMN_USERNAME = "username";
  private static final String COLUMN_PASSWORD = "password";

    private static final String COLUMN_LEVEL_DIFFICULTY = "levelDifficulty";
    private static final String COLUMN_COLOUR_SCHEME = "colourScheme";
    private static final String COLUMN_LEVEL_ONE_TIME = "levelOneTime";
    private static final String COLUMN_LEVEL_TWO_TIME = "levelTwoTime";
    private static final String COLUMN_LEVEL_THREE_TIME = "levelThreeTime";
    private static final String COLUMN_CURRENT_LIVES = "currLives";
    private static final String COLUMN_PROGRESS = "progress";
    private static final String COLUMN_RETURNING_USER = "returningUser";

  /** SQLiteDatabase object */
  SQLiteDatabase db;

  /** String with table with appropriate columns */
  private static final String TABLE_CREATED =
          "create table users (id integer primary key not null, username text not null, password text not null," +
                  "levelDifficulty text not null , colourScheme text not null, levelOneTime integer not null, " +
                  "levelTwoTime integer not null, levelThreeTime integer not null, currLives integer not null," +
                  "progress text not null, returningUser integer not null)";

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
   * @param i old version
   * @param j new version
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

    db.insert(TABLE_NAME, null, cValues);
    db.close();
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
                    return true;  // there are duplicate user names
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return false; // there are no duplicate user names
    }
}
