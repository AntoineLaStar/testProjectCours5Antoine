package com.example.a533.testprojectcours5antoine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignUpOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_or_login_activity);
        setListener();
    }

    private void setListener(){
        findViewById(R.id.button_gotoLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLoginActivity();
            }
        });

        findViewById(R.id.button_gotoSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToSignUpActivity();
            }
        });
    }

    public void sendToSignUpActivity(){
        Intent sendToSignUpOrLogin = new Intent(this, SignUpActivity.class);
        startActivity(sendToSignUpOrLogin);
    }

    public void sendToLoginActivity(){
        Intent sendToSignUpOrLogin = new Intent(this, LoginActivity.class);
        startActivity(sendToSignUpOrLogin);
    }
}
