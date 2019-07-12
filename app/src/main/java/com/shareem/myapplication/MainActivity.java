package com.shareem.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shareem.friend.Friend;
import com.shareem.friend.FriendFactory;
import com.shareem.user.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private User user;
    private TextView username;
    private TextView password;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                user = createUser(usernameValue, passwordValue);

                String message = String.format("Hello %s", user.getName());
                greetUser(message);

                username.setText("");
                password.setText("");

                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private User createUser(String username, String password){
        return new User("Shareem Gelito Teofilo", 21,  username,
                "Banga, Aklan", password, createFriends());
    }

    private List<Friend> createFriends() {
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

    private void greetUser(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
