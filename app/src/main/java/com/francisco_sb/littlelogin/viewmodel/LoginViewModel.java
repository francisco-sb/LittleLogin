package com.francisco_sb.littlelogin.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private static final String USER = "user";
    private static final String PASSWORD = "123..";

    private String us;
    private String pwd;

    public LiveData<String> validateCredentials(String user, String password) {

        this.us = user;
        this.pwd = password;

        MutableLiveData<String> credentialsResult = new MutableLiveData<>();

        if (us.equals("")) {
            credentialsResult.setValue("You need a user");
            return credentialsResult;
        }

        if (pwd.equals("")) {
            credentialsResult.setValue("You need a password");
            return credentialsResult;
        }

        credentialsResult.setValue("SUCCESS");
        return credentialsResult;
    }

    public LiveData<Boolean> doLogin() {

        MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

        if (USER.equals(us) && PASSWORD.equals(pwd)) {
            loginResult.setValue(true);
        } else {
            loginResult.setValue(false);
        }

        return loginResult;
    }

}
