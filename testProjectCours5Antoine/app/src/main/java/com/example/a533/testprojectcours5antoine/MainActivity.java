package com.example.a533.testprojectcours5antoine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a533.testprojectcours5antoine.notification.NotificationService;
import com.example.a533.testprojectcours5antoine.notification.model.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser currentUser;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void startService(){
        Intent serviceIntent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this,serviceIntent);

    }

    private void updateUI(FirebaseUser currentUser){
        if (currentUser == null){
            sendUserToSignUpOrLoginActivity();
        }
        else{
            startService();
        }
    }

    private void sendUserToSignUpOrLoginActivity(){
        Intent SendToSignUpOrLogin = new Intent(this,SignUpOrLoginActivity.class);
        startActivity(SendToSignUpOrLogin);
    }


    private void setListener(){
        findViewById(R.id.button_signout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });

        findViewById(R.id.button_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        EditText editTextMessage = findViewById(R.id.editText_message);
        MessageModel messageModel = new MessageModel(editTextMessage.getText().toString(),auth.getCurrentUser().getEmail());
        database.collection("Notification").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void signOutUser(){
        auth.signOut();
        Intent sendToSignUpOrLogin = new Intent(this, SignUpOrLoginActivity.class);
        startActivity(sendToSignUpOrLogin);
    }
}
