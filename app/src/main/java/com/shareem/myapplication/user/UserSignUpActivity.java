package com.shareem.myapplication.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shareem.myapplication.R;
import com.shareem.myapplication.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignUpActivity extends AppCompatActivity {

    private TextView txtName;
    private TextView txtAge;
    private TextView txtAddress;
    private TextView txtUsername;
    private TextView txtPassword;
    private Button btnSignUp;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        txtName = findViewById(R.id.txtSignUpName);
        txtAge = findViewById(R.id.txtSignUpAge);
        txtAddress = findViewById(R.id.txtSignUpAddress);
        txtUsername = findViewById(R.id.txtSignUpUsername);
        txtPassword = findViewById(R.id.txtSignUpPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
            }
        });
    }

    private void signUpUser(){

        String name = txtName.getText().toString();
        int age = Integer.parseInt(txtAge.getText().toString());
        String address = txtAddress.getText().toString();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        User user = UserFactory.create(
                name, age, username, address, password, null
        );

        Call<User> savedUser = userService.signUpUser(user);

        savedUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
