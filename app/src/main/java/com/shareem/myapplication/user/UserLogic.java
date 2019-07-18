package com.shareem.myapplication.user;

import java.util.List;

public class UserLogic {

    private static UserLogic userLogicInstance;
    private LoginHistoryDao loginHistoryDao = new LoginHistoryDao();

    private UserLogic() {}

    public static UserLogic getInstance(){
        if(userLogicInstance == null){
            userLogicInstance = new UserLogic();
        }
        return userLogicInstance;
    }

    public LoginHistory createLoginHistoryForUser(User user){
        LoginHistory loginHistory = LoginHistoryFactory.create(user.getUsername());
        return loginHistoryDao.insert(loginHistory);
    }

    public List<LoginHistory> getLoginHistoriesByUsername(String username){
        return loginHistoryDao.findAllByUsername(username);
    }


}
