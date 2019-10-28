package com.group0578.hpgame.view;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * UserAccount class creates a new User object to be stored in AppUserAccountsDatabase.java
 *
 * Entity annotation means this object must be stored in UserAccountsDatabase
 * - Database contains tables for each entity
 */
@Entity(tableName = "UserAccounts")
public class UserAccount {

    /**
     * The login username for this UserAccount.
     * Every UserAccount must have a unique username.
     */
    @PrimaryKey     // means every UserAccount is uniquely identified by username.
    @NonNull        // PrimaryKeys must contain non-null values
    @ColumnInfo(name = "userName")
    private String username;

    /**
     * The login password for this UserAccount.
     */
    @ColumnInfo(name = "userPassword")
    private String password;

    /**
     * Initializes new UserAccount to be added in AppUserAccountsDatabase.
     * @param username the username for this UserAccount.
     * @param password the password for this UserAccount.
     */
    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * @return the username of this UserAccount.
     */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * @param username the new username for this UserAccount.
     */
    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    /**
     * @return the password of this UserAccount.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the new password for this UserAccount.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
