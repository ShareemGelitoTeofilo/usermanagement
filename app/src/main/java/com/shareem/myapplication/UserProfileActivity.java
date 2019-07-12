package com.shareem.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.shareem.user.User;

public class UserProfileActivity extends AppCompatActivity {

    private User user;
    private TextView txtUserName;
    private TextView txtUserAge;
    private TextView txtUserAddress;
    private TextView txtUserEmail;
    private ListView listViewFriends;
    private Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        btnLogout = findViewById(R.id.btnLogout);
        txtUserName = findViewById(R.id.txtName);
        txtUserAge = findViewById(R.id.txtAge);
        txtUserAddress = findViewById(R.id.txtAddress);
        txtUserEmail = findViewById(R.id.txtEmail);
        user = getIntent().getParcelableExtra("user");

        populateUserProfile(user);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void populateUserProfile(User user){
        txtUserName.setText(user.getName());
        String age = String.valueOf(user.getAge());
        txtUserAge.setText(age);
        txtUserAddress.setText(user.getAddress());
        txtUserEmail.setText(user.getEmail());
        FriendListAdapter friendListAdapter = new FriendListAdapter(this, user.getFriends());
        listViewFriends = findViewById(R.id.friendListListView);
        listViewFriends.setAdapter(friendListAdapter);
    }
}
