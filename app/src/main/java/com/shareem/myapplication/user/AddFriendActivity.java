package com.shareem.myapplication.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.shareem.myapplication.AppCallback;
import com.shareem.myapplication.R;

import java.util.List;

public class AddFriendActivity extends AppCompatActivity {

    private UserLogic userLogic;
    private User user;
    private ListView usersListView;
    private View.OnClickListener btnAddFriendOnClickListener;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        userLogic = UserLogic.getInstance();
        user = getIntent().getParcelableExtra("user");
        usersListView = findViewById(R.id.listViewAddFriends);
        btnBack = findViewById(R.id.btnAddFriendBack);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnAddFriendOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userToAddAsFriend = (User) view.getTag();
                userLogic.addFriend(user.getId(), userToAddAsFriend);
                String message = String.format("Added %s as friend", userToAddAsFriend.getName());
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        };

        userLogic.getAllUsersExceptWithId(user.getId(), new AppCallback() {
            @Override
            public void onCallback(Object response, String message) {
                List<User> users = (List<User>) response;
                if(!users.isEmpty()){
                    AddFriendListAdapter addFriendListAdapter = new AddFriendListAdapter(
                            AddFriendActivity.this, users, btnAddFriendOnClickListener
                    );
                    usersListView.setAdapter(addFriendListAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to load users", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
