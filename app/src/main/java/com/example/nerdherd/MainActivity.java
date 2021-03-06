package com.example.nerdherd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button login_btn;
    Button signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the buttons
        login_btn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.login_btn);

        //signup activity intent
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class signupActivity = SignupActivity.class;
                Intent intent = new Intent(context, signupActivity);
                startActivity(intent); //transition to signup screen
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                //TODO change to login class
                Class loginActivity = LoginActivity.class;
                Intent intent = new Intent(context, loginActivity);
                startActivity(intent); //transition to login screen
            }
        });

    }
}
