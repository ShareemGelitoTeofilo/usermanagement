package com.shareem.myapplication.history;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoginHistory extends RealmObject {

    @PrimaryKey
    private String id;
    private String username;
    private long loginTime;
    private long logoutTime;

    public LoginHistory() {
    }

    public LoginHistory(String username, long loginTime) {
        this.username = username;
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(int logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

