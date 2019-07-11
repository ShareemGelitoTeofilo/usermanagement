package com.shareem.friend;

public class FriendFactory {
    public static Friend create(String name, String email, String address){
        return new Friend(name, email,address);
    }
}
