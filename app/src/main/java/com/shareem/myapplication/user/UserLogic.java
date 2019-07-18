package com.shareem.myapplication.user;

import com.shareem.myapplication.AppCallback;
import com.shareem.myapplication.loginhistory.LoginHistory;
import com.shareem.myapplication.loginhistory.LoginHistoryDao;
import com.shareem.myapplication.loginhistory.LoginHistoryFactory;
import com.shareem.myapplication.loginhistory.LoginHistoryMapper;
import com.shareem.myapplication.loginhistory.LoginHistoryRO;
import com.shareem.myapplication.network.RetrofitInstance;

import java.util.List;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLogic {

    private static UserLogic userLogicInstance;
    private LoginHistoryDao loginHistoryDao;
    private UserService userService;

    private UserLogic() {
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
        loginHistoryDao = new LoginHistoryDao();
    }

    public static UserLogic getInstance(){
        if(userLogicInstance == null){
            userLogicInstance = new UserLogic();
        }
        return userLogicInstance;
    }

    public LoginHistory createLoginHistoryForUser(User user){
        LoginHistory loginHistory = LoginHistoryFactory.create(user.getUsername());
        return loginHistoryDao.insert(loginHistory);
    }

    public List<LoginHistory> getLoginHistoriesByUsername(String username){
        List<LoginHistory> loginHistories = loginHistoryDao.findAllByUsername(username);
        return loginHistories;
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


}
