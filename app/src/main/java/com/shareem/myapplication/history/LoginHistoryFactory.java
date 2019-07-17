package com.shareem.myapplication.history;

import java.util.UUID;

public class LoginHistoryFactory {
    public static LoginHistory create(String username){
        long loginTime = System.currentTimeMillis();
        String id = UUID.randomUUID().toString();
        LoginHistory loginHistory = new LoginHistory(username, loginTime);
        loginHistory.setId(id);
        return loginHistory;
    }
}
