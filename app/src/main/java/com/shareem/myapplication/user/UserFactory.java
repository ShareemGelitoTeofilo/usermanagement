package com.shareem.myapplication.user;

import java.util.List;

public class UserFactory {
    public static User create(String name, int age, String username, String address, String password, List<User> friends){
        return new User(name, age,  username, address, password, friends);
    }
}
