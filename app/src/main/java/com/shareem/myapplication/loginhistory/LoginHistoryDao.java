package com.shareem.myapplication.loginhistory;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginHistoryDao {

    public LoginHistory insert(LoginHistory loginHistory){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        LoginHistoryRO loginHistoryRO = LoginHistoryMapper.toLoginHistoryRO(loginHistory);
        realm.copyToRealm(loginHistoryRO);
        realm.commitTransaction();
        loginHistory = LoginHistoryMapper.toLoginHistory(loginHistoryRO);
        realm.close();
        return loginHistory;
    }

    public List<LoginHistory> findAllByUsername(String username) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<LoginHistoryRO> loginHistoryROs = realm.where(LoginHistoryRO.class)
                .equalTo("username", username)
                .findAll();
        List<LoginHistory> loginHistories = LoginHistoryMapper.toLoginHistories(loginHistoryROs);
        realm.close();
        return loginHistories;
    }
}
