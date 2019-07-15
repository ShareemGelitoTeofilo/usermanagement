package com.shareem.myapplication.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("users/login")
    Call<User> loginUser(@Query("username") String username, @Query("password") String password);

    @GET("users/findAll")
    Call<List<User>> findAllUsers();
}