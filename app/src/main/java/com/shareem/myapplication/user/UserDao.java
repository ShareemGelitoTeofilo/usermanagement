package com.shareem.myapplication.user;

import io.realm.Realm;

public class UserDao {

    public User insert(User user){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserRO userRO = UserMapper.toUserRO(user);
        userRO = realm.copyToRealm(userRO);
        user = UserMapper.toUser(userRO);
        realm.commitTransaction();
        realm.close();
        return user;
    }

    public User update(User user){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserRO userRO = UserMapper.toUserRO(user);
        userRO = realm.copyToRealmOrUpdate(userRO);
        user = UserMapper.toUser(userRO);
        realm.commitTransaction();
        realm.close();
        return user;
    }

    public User findById(int id){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserRO userRO = realm.where(UserRO.class)
                .equalTo("id", id)
                .findFirst();
        User user = UserMapper.toUser(userRO);
        realm.close();
        return user;
    }
}
