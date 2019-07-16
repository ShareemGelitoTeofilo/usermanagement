package com.shareem.myapplication.user;

import com.shareem.myapplication.friend.Friend;

import java.util.List;

import io.realm.RealmList;

public class UserFactory {
    public static User create(String name, int age, String username, String address, String password, RealmList<Friend> friends){
        return new User(name, age,  username, address, password, friends);
    }
}
