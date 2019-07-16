package com.shareem.myapplication.friend;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class Friend extends RealmObject implements Parcelable {
    private String name;
    private String email;
    private String address;

    public Friend() {
    }

    public Friend(String name, String email, String address){
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Friend(Parcel in){
        this.name = in.readString();
        this.email = in.readString();
        this.address = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(address);
    }

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
}
