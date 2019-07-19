package com.shareem.myapplication.user;

import com.shareem.myapplication.AppCallback;
import com.shareem.myapplication.loginhistory.LoginHistory;
import com.shareem.myapplication.loginhistory.LoginHistoryDao;
import com.shareem.myapplication.loginhistory.LoginHistoryFactory;
import com.shareem.myapplication.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogic {

    private static UserLogic userLogicInstance;
    private LoginHistoryDao loginHistoryDao;
    private UserDao userDao;
    private UserService userService;

    private UserLogic() {
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
        loginHistoryDao = new LoginHistoryDao();
        userDao = new UserDao();
    }

    public static UserLogic getInstance(){
        if(userLogicInstance == null){
            userLogicInstance = new UserLogic();
        }
        return userLogicInstance;
    }

    public User findById(int userId){
        return userDao.findById(userId);
    }

    public User addFriend(int userID, User userToAdd){
        User user = userDao.findById(userID);
        user.getFriends().add(userToAdd);
        return userDao.update(user);
    }

    public void signUpUser(User user, final AppCallback callback){
        Call<User> userCall = userService.signUpUser(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                userDao.insert(user);
                callback.onCallback(user, "Successfully retrieved all users");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onCallback(null, "");
            }
        });


    }

    public LoginHistory createLoginHistoryForUser(User user){
        LoginHistory loginHistory = LoginHistoryFactory.create(user.getUsername());
        return loginHistoryDao.insert(loginHistory);
    }

    public List<LoginHistory> getLoginHistoriesByUsername(String username){
        List<LoginHistory> loginHistories = loginHistoryDao.findAllByUsername(username);
        return loginHistories;
    }

    public void getAllUsersExceptWithId(int id, final AppCallback callback){
        Call<List<User>> userCalls = userService.findAllUsersExceptWithId(id);
        userCalls.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                callback.onCallback(users, "Successfully retrieved all users");
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onCallback(null, "");
            }
        });

    }


    public void loginUser(String username, String password, final AppCallback callback) {
        Call<User> userCall = userService.loginUser(username, password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                createLoginHistoryForUser(user);
                callback.onCallback(user, "Logged in");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onCallback(null, "");
            }
        });
    }

    public List<User> getAllNotFriendsOfUser(User user, List<User> responseUsers){
        List<User> friends =  findById(user.getId()).getFriends();
        List<User> notFriends = new ArrayList<>(responseUsers);
        notFriends.removeAll(friends);
        return notFriends;
    }


}
