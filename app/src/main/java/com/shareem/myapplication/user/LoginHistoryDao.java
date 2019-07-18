package com.shareem.myapplication.user;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginHistoryDao {

    public LoginHistory insert(LoginHistory loginHistory){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        loginHistory = realm.copyToRealm(loginHistory);
        realm.commitTransaction();
        realm.close();
        return loginHistory;
    }

    public List<LoginHistory> findAllByUsername(String username) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<LoginHistory> loginHistory = realm.where(LoginHistory.class)
                .equalTo("username", username)
                .findAll();
        realm.close();
        return loginHistory;
    }
}
