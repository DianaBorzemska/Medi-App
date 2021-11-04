package com.example.medi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class HistorySubmit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button submitbtn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cont1);
        submitbtn=(Button) findViewById(R.id.submitBtn);
    }
}