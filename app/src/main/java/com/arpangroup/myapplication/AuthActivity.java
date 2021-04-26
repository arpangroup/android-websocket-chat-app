package com.arpangroup.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class AuthActivity extends AppCompatActivity {

    TextInputEditText etUserName, etSendTo;
    Button btnRegister;
    SettingSession settingSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        etUserName = findViewById(R.id.etUserName);
        etSendTo = findViewById(R.id.etSendTo);
        btnRegister = findViewById(R.id.btnRegister);
        settingSession = new SettingSession(this);

        if(!TextUtils.isEmpty(settingSession.getUserName())){
            etUserName.setText(settingSession.getUserName());
        }
        if(!TextUtils.isEmpty(settingSession.getSendTo())){
            etSendTo.setText(settingSession.getSendTo());
        }

        btnRegister.setOnClickListener(view -> {
           settingSession.setUserName(etUserName.getText().toString());
           settingSession.setSendTo(etSendTo.getText().toString());

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }
}