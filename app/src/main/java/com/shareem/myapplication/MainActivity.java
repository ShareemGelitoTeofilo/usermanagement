package com.shareem.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shareem.myapplication.user.User;
import com.shareem.myapplication.user.UserLogic;
import com.shareem.myapplication.network.RetrofitInstance;
import com.shareem.myapplication.user.UserProfileActivity;
import com.shareem.myapplication.user.UserService;
import com.shareem.myapplication.user.UserSignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private TextView txtCreateAccount;
    private UserService userService;
    private UserLogic userLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtCreateAccount = findViewById(R.id.txtLoginCreateAccount);
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
        userLogic = UserLogic.getInstance();
    }

    @Override
    protected void onResume() {

        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserSignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = txtUsername.getText().toString();
                String passwordValue = txtPassword.getText().toString();
                userLogic.loginUser(usernameValue, passwordValue, new AppCallback() {
                            @Override
                            public void onCallback(Object response, String message) {
                                User user = (User) response;
                                String popMessage;
                                if(!message.isEmpty()){
                                    popMessage = "Welcome " + user.getName();
                                    popMessage(popMessage);
                                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                                    intent.putExtra("user", user);
                                    startActivity(intent);
                                } else {
                                    popMessage = "Sorry failed to login";
                                    popMessage(popMessage);
                                }
                            }});

                txtUsername.setText("");
                txtPassword.setText("");
            }
        });

        super.onResume();
    }

    private void loginUser(String username, String password, final AppCallback callback) {
        Call<User> userCall = userService.loginUser(username, password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               if(response.isSuccessful()){
                   User user = response.body();
                   if (user != null) {
                       String message = "Welcome " + user.getName();
                       popMessage(message);
                       userLogic.createLoginHistoryForUser(user);
                       Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                       intent.putExtra("user", user);
                       startActivity(intent);
                       callback.onCallback(user, "safsf");
                   } else {}
               }else {
                   response.errorBody();
               }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String message = t.getMessage();
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void popMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
