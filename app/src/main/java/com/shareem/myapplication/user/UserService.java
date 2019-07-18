package com.shareem.myapplication.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("users/login")
    Call<User> loginUser(@Query("username") String username, @Query("password") String password);

    @POST("users/signUp")
    Call<User> signUpUser(@Body User user);

    @GET("users/findAllExceptWithId/{id}")
    Call<List<User>> findAllUsersExceptWithId(@Path("id") int id);

    @GET("users/findAll")
    Call<List<User>> findAllUsers();
}