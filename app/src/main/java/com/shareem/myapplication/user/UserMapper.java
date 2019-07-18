package com.shareem.myapplication.user;

import java.util.ArrayList;
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
        userRO.setName(user.getName());
        userRO.setAddress(user.getAddress());
        userRO.setUsername(user.getUsername());
        userRO.setAge(user.getAge());
        RealmList<UserRO> friends = toUserROs(user.getFriends());
        userRO.setFriends(friends);
        return userRO;
    }

    public static User toUser(UserRO userRO){
        User user = new User();
        user.setId(userRO.getId());
        user.setName(userRO.getName());
        user.setAddress(userRO.getAddress());
        user.setUsername(userRO.getUsername());
        user.setAge(userRO.getAge());
        List<User> friends = toUsers(userRO.getFriends());
        user.setFriends(friends);
        return user;
    }

    public static RealmList<UserRO> toUserROs(List<User> users){
        RealmList<UserRO> userROS = new RealmList<>();
        if(users != null) {
            for (User user : users) {
                userROS.add(toUserRO(user));
            }
        }
        return userROS;
    }

    public static List<User> toUsers(RealmList<UserRO> userROs){
        List<User> users = new ArrayList<>();
        if(userROs != null) {
            for (UserRO userRO : userROs) {
                users.add(toUser(userRO));
            }
        }
        return users;
    }

}
