package com.shareem.myapplication.loginhistory;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class LoginHistoryMapper {
    private static LoginHistoryMapper loginHistoryMapperInstance;

    private LoginHistoryMapper(){}

    public static LoginHistoryMapper getInstance(){
        if(loginHistoryMapperInstance == null){
            loginHistoryMapperInstance = new LoginHistoryMapper();
        }
        return loginHistoryMapperInstance;
    }

    public static LoginHistory toLoginHistory(LoginHistoryRO loginHistoryRO){
        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setId(loginHistoryRO.getId());
        loginHistory.setUsername(loginHistoryRO.getUsername());
        loginHistory.setLoginTime(loginHistoryRO.getLoginTime());
        loginHistory.setLogoutTime(loginHistoryRO.getLogoutTime());
        return loginHistory;
    }

    public static LoginHistoryRO toLoginHistoryRO(LoginHistory loginHistory){
        LoginHistoryRO loginHistoryRO = new LoginHistoryRO();
        loginHistoryRO.setId(loginHistory.getId());
        loginHistoryRO.setUsername(loginHistory.getUsername());
        loginHistoryRO.setLoginTime(loginHistory.getLoginTime());
        loginHistoryRO.setLogoutTime(loginHistory.getLogoutTime());
        return loginHistoryRO;
    }


    public static List<LoginHistory> toLoginHistories(RealmResults<LoginHistoryRO> loginHistoryROs){
        List<LoginHistory> loginHistories = new ArrayList<>();
        for(LoginHistoryRO loginHistoryRO: loginHistoryROs){
            loginHistories.add(toLoginHistory(loginHistoryRO));
        }
        return loginHistories;
    }



}
