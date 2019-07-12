package com.shareem.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.shareem.friend.Friend;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {

    private String name;
    private int age;
    private String email;
    private String address;
    private String password;
    private List<Friend> friends;

    public User(String name, int age, String email, String address, String password, List<Friend> friends) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.password = password;
        this.friends = friends;
    }

    public User(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.email = in.readString();
        this.address = in.readString();
        this.password = in.readString();
        this.friends = new ArrayList<>();
        in.readList(this.friends, Friend.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(email);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
