package com.shareem.myapplication.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.shareem.myapplication.R;
import com.shareem.myapplication.history.LoginHistory;

import java.sql.Timestamp;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserProfileActivity extends AppCompatActivity {

    private User user;
    private TextView txtUserName;
    private TextView txtUserAge;
    private TextView txtUserAddress;
    private TextView txtUserEmail;
    private ListView listViewFriends;
    private Button btnLogout;
    private Button btnShowLoginHistory;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        btnLogout = findViewById(R.id.btnLogout);
        btnShowLoginHistory = findViewById(R.id.btnShowLoginHistory);
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

    @Override
    protected void onResume() {
        super.onResume();

        btnShowLoginHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginHistory(user);
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

    private void showLoginHistory(User user){
        RealmResults<LoginHistory> loginHistories = realm.where(LoginHistory.class)
                                            .equalTo("username", user.getEmail())
                                            .findAll();
        String result;
        if(loginHistories != null || !loginHistories.isEmpty()){
            result = "Username: " + user.getEmail() + "\n" +
                     "Login dates: \n";
            for (LoginHistory loginHistory: loginHistories){
                Timestamp time = new Timestamp(loginHistory.getLoginTime());
                result += "Time: " + time.toString() + "\n";
            }
        } else {
            result = "Failed to load login histories.";
        }
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }
}
