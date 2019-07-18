package com.shareem.myapplication.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;

public class UserRO {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("username")
    private String username;
    @SerializedName("address")
    private String address;
    @SerializedName("password")
    private String password;
    @SerializedName("friends")
    private RealmList<UserRO> friends;

    public UserRO() {
    }

    public UserRO(String name, int age, String username, String address, String password, RealmList<UserRO> friends) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.address = address;
        this.password = password;
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<UserRO> getFriends() {
        return friends;
    }

    public void setFriends(RealmList<UserRO> friends) {
        this.friends = friends;
    }
}
