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

  /** SQLiteDatabase object */
  SQLiteDatabase db;

  /** String with table with appropriate columns */
  private static final String TABLE_CREATED =
      "create table users (id integer primary key not null , username text not null , password text not null);";

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
   * @param i1 new version
   */
  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
    db.execSQL(query);
    this.onCreate(db);
  }

  /**
   * Insert a new user into the database based on SQLiteManager information
   *
   * @param sql contains username and password to be inserted
   */
  public void insertUser(SQLiteManager sql) {
    System.out.println("Inserted");
    db = this.getWritableDatabase();
    ContentValues cValues = new ContentValues();

    String query = "select * from users";
    Cursor cursor = db.rawQuery(query, null);

    int count = cursor.getCount();

    cValues.put(COLUMN_ID, count);
    cValues.put(COLUMN_USERNAME, sql.getUsername());
    cValues.put(COLUMN_PASSWORD, sql.getPassword());

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
          break;
        }
      } while (cursor.moveToNext());
    }
    db.close();
    return pass;
  }
}
