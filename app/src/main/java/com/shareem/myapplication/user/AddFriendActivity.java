package com.shareem.myapplication.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private AddFriendListAdapter addFriendListAdapter;

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
                addFriendListAdapter.getUsers().remove(userToAddAsFriend);
                addFriendListAdapter.notifyDataSetChanged();
                String message = String.format("Added %s as friend", userToAddAsFriend.getName());
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        };

        userLogic.getAllUsersExceptWithId(user.getId(), new AppCallback() {
            @Override
            public void onCallback(Object response, String message) {
                List<User> responseUsers = (List<User>) response;
                responseUsers = userLogic.getAllNotFriendsOfUser(user, responseUsers);
                if(!responseUsers.isEmpty()){
                    addFriendListAdapter = new AddFriendListAdapter(
                            AddFriendActivity.this, responseUsers, btnAddFriendOnClickListener
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
