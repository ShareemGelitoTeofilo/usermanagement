package com.shareem.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.shareem.friend.Friend;
import com.shareem.friend.FriendFactory;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final Button btnLogout = findViewById(R.id.btnLogout);
        final FriendListAdapter friendListAdapter = new FriendListAdapter(this, createFriends());
        final ListView listView = findViewById(R.id.friendListListView);

        listView.setAdapter(friendListAdapter);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private List<Friend> createFriends(){
        List<Friend> friends = new ArrayList<>();
        friends.add(FriendFactory.create("Joker", "jokerhahaha@yahoo.com", "Jaro, Iloilo City"));
        friends.add(FriendFactory.create("Batman", "batman@yahoo.com", "Jaro, Iloilo City"));
        friends.add(FriendFactory.create("Superman", "superman@yahoo.com", "Jaro, Iloilo City"));
        friends.add(FriendFactory.create("Barney", "barney@yahoo.com", "Jaro, Iloilo City"));
        friends.add(FriendFactory.create("Spiderman", "spiderman@yahoo.com", "Jaro, Iloilo City"));
        friends.add(FriendFactory.create("Lilo", "lilo@yahoo.com", "Jaro, Iloilo City"));
        friends.add(FriendFactory.create("Stitch", "stich@yahoo.com", "Jaro, Iloilo City"));
        return friends;
    }
}
