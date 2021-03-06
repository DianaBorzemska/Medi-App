package com.example.medi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    Button updateBtn,predictBtn, insuranceBtn, historyBtn,supportBtn,profBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateBtn=(Button) findViewById(R.id.save_btn);
        predictBtn=(Button) findViewById(R.id.predict_btn);
        insuranceBtn=(Button) findViewById(R.id.insurance_btn);
        historyBtn=(Button) findViewById(R.id.history_btn);
        supportBtn=(Button) findViewById(R.id.support_btn);
        profBtn=(Button) findViewById(R.id.prof_btn);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, Ratings_and_reviews_screen.class);
                startActivity(send);   }
        });
        predictBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, Medi_Predict.class);
                startActivity(send);   }
        });
        insuranceBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, Insurance.class);
                startActivity(send);   }
        });
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, MedHistory.class);
                startActivity(send);   }
        });
        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, Support.class);
                startActivity(send);   }
        });
        profBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MainActivity.this, Professional.class);
                startActivity(send);   }
        });
    }
}