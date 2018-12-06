package com.francisco_sb.littlelogin.ui;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.francisco_sb.littlelogin.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean accessed = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("ACCESSED", false);

        if (!accessed) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        final Context context = getApplicationContext();
        MaterialButton buttonLogout = findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(view -> {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("ACCESSED", false).apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
