package com.shareem.myapplication.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.shareem.myapplication.friend.Friend;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
    @SerializedName("username")
    private String email;
    @SerializedName("address")
    private String address;
    @SerializedName("password")
    private String password;
    @SerializedName("friends")
    private RealmList<Friend> friends;

    public User() {
    }

    public User(String name, int age, String email, String address, String password, RealmList<Friend> friends) {
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
        this.friends = new RealmList<>();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public void setFriends(RealmList<Friend> friends) {
        this.friends = friends;
    }
}
