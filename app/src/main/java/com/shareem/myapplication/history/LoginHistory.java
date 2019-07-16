package com.shareem.myapplication.history;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoginHistory extends RealmObject {

    @PrimaryKey
    private int id;
    private String username;
    private int loginTime;
    private int logoutTime;

    public LoginHistory() {
    }

    public LoginHistory(int id, String username, int loginTime, int logoutTime) {
        this.id = id;
        this.username = username;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(int loginTime) {
        this.loginTime = loginTime;
    }

    public int getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(int logoutTime) {
        this.logoutTime = logoutTime;
    }
}

