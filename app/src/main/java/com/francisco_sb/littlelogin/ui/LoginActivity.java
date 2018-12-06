package com.francisco_sb.littlelogin.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.francisco_sb.littlelogin.R;
import com.francisco_sb.littlelogin.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout userLayout, passwordLayout;
    private TextInputEditText user, password;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        userLayout = findViewById(R.id.userLayout);
        passwordLayout = findViewById(R.id.passwordLayout);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
    }

    private void updateUI() {

    }

}
