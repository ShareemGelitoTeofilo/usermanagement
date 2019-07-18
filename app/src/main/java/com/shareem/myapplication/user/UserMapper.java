package com.shareem.myapplication.user;

import java.util.List;

import io.realm.RealmList;

public class UserMapper {

    private static UserMapper userMapperInstance;

    private UserMapper(){}

    public static UserMapper getInstance(){
        if(userMapperInstance == null){
            userMapperInstance = new UserMapper();
        }
        return userMapperInstance;
    }

    public static UserRO toUserRO(User user){
        UserRO userRO = new UserRO();
        userRO.setId(user.getId());
        userRO.setAddress(user.getAddress());
        userRO.setAge(user.getAge());
        RealmList<UserRO> friends = toUserROs(user.getFriends());
        userRO.setFriends(friends);
        return null;
    }

    public static User toUser(UserRO userRO){
        User user = new User();
        user.setId(userRO.getId());
        user.setAddress(userRO.getAddress());
        user.setAge(userRO.getAge());
        List<User> friends = toUsers(userRO.getFriends());
        user.setFriends(friends);
        return null;
    }

    public static RealmList<UserRO> toUserROs(List<User> users){
        RealmList<UserRO> userROS = new RealmList<>();
        for(User user : users){
            userROS.add(toUserRO(user));
        }
        return userROS;
    }

    public static List<User> toUsers(RealmList<UserRO> userROs){
        List<User> users = new RealmList<>();
        for(UserRO userRO : userROs){
            users.add(toUser(userRO));
        }
        return users;
    }

}
