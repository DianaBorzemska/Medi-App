package com.example.medi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginScreen extends AppCompatActivity {
    Button loginBtn,registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        loginBtn=(Button) findViewById(R.id.login_btn);
        registerBtn=(Button) findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(send);   }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(send);   }
        });
    }
}