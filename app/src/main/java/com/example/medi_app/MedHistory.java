package com.example.medi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedHistory extends AppCompatActivity {
    Button continuebtn;
    TextView age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        continuebtn=(Button) findViewById(R.id.continue_btn);

        age = findViewById(R.id.age);
        //get age from db
        String agefromdb="";
        age.setText(agefromdb);

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MedHistory.this, HistoryContinue.class);
                startActivity(send);   }

        });

    }
}