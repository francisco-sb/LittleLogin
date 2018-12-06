package com.francisco_sb.littlelogin.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private static final String USER = "user";
    private static final String PASSWORD = "123..";

    private MutableLiveData<Object> result;

    public void doLogin(String user, String password) {
        if (user.equals("")) {
            result.setValue("You need a user");
            return;
        }

        if (password.equals("")) {
            result.setValue("You need a password");
            return;
        }

        if (USER.equals(user) && PASSWORD.equals(password)) {
            result.setValue(true);
        } 
    }

    public MutableLiveData<Object> getResult() {
        return this.result;
    }
}
