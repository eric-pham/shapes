package com.group0578.hpgame.model;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import com.group0578.hpgame.presenter.CreateUserPresenter;

/**
 * This class was derived from Tech Academy's Tutorial and modified to suit the needs of our
 * project
 */
public class SQLiteHelper extends SQLiteOpenHelper {

  private static final int DB_VERSION = 1;
  private static final String DB_NAME = "users.db";

  private static final String TABLE_NAME = "users";
  private static final String COLUMN_ID = "id";
  private static final String COLUMN_USERNAME = "username";
  private static final String COLUMN_PASSWORD = "password";

  SQLiteDatabase db;

  private static final String TABLE_CREATED =
      "create table users (id integer primary key not null , username text not null , password text not null);";

  public SQLiteHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(TABLE_CREATED);
    this.db = db;
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
    db.execSQL(query);
    this.onCreate(db);
  }

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
