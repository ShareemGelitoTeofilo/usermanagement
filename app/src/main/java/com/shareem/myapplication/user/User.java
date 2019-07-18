package com.shareem.myapplication.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.realm.RealmList;

public class User implements Parcelable {

    private int id;
    private String name;
    private int age;
    private String username;
    private String address;
    private String password;
    private List<User> friends;

    public User() {
    }

    public User(String name, int age, String username, String address, String password, List<User> friends) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.address = address;
        this.password = password;
        this.friends = friends;
    }

    public User(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.age = in.readInt();
        this.username = in.readString();
        this.address = in.readString();
        this.password = in.readString();
        this.friends = new RealmList<>();
        in.readList(this.friends, User.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(username);
        parcel.writeString(address);
        parcel.writeString(password);
        parcel.writeList(friends);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
