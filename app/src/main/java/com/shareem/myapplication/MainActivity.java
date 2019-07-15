package com.shareem.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shareem.myapplication.network.RetrofitInstance;
import com.shareem.myapplication.user.User;
import com.shareem.myapplication.user.UserProfileActivity;
import com.shareem.myapplication.user.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Call<User> userCall;
    private TextView txtUsername;
    private TextView txtPassword;
    private Button btnLogin;
    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
    }

    @Override
    protected void onResume() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = txtUsername.getText().toString();
                String passwordValue = txtPassword.getText().toString();

                loginUser(usernameValue, passwordValue);
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });

        super.onResume();
    }

    private void loginUser(String username, String password) {
        Call<User> userCall = userService.loginUser(username, password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                String message = String.format("Hello %s", user.getName());
                greetUser(message);
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                String message = t.getMessage();
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void greetUser(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
