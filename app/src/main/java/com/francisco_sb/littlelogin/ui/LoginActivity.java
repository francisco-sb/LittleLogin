package com.francisco_sb.littlelogin.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.francisco_sb.littlelogin.R;
import com.francisco_sb.littlelogin.viewmodel.LoginViewModel;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout userLayout, passwordLayout;
    private TextInputEditText user, password;

    private LinearLayout loginView;
    private LinearLayout progressView;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        userLayout = findViewById(R.id.user_layout);
        passwordLayout = findViewById(R.id.password_layout);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        MaterialButton buttonLogin = findViewById(R.id.button_login);

        loginView = findViewById(R.id.login_view);
        progressView = findViewById(R.id.progress_view);

        buttonLogin.setOnClickListener(this);
    }

    private void updateUI(Object result) {
        if (result instanceof Boolean) {
            if ((Boolean) result) {
                loginView.setVisibility(View.GONE);
                progressView.setVisibility(View.VISIBLE);

                progressView.postDelayed(() -> {
                    PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("ACCESSED", true).apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }, 3000);

            } else {
                userLayout.setError("Verify your credentials");
                passwordLayout.setError("Verify your credentials");

                userLayout.postDelayed(() -> {
                    userLayout.setError(null);
                    passwordLayout.setError(null);
                    userLayout.setErrorEnabled(false);
                    passwordLayout.setErrorEnabled(false);
                }, 5000);

            }
        }

        if (result instanceof String) {
            if (((String) result).contains("user"))
                userLayout.setError((String) result);
            else if (((String) result).contains("password"))
                passwordLayout.setError((String) result);
            else if (((String) result).contains("SUCCESS"))
                loginViewModel.doLogin().observe(this, this::updateUI);
        }
    }

    @Override
    public void onClick(View view) {
        userLayout.setError(null);
        passwordLayout.setError(null);
        userLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);

        String userString = Objects.requireNonNull(user.getText()).toString();
        String passwordString = Objects.requireNonNull(password.getText()).toString();

        loginViewModel.validateCredentials(userString, passwordString).observe(this, this::updateUI);
    }
}
