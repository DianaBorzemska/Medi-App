package com.example.medi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import androidx.annotation.Nullable;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MedHistory extends AppCompatActivity {
    TextView depression, agina, heart_rate, resting_ecg
            ,blood_sugar, serum, blood_pressure, chest_pain, sex, age, user_name;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    Button continuebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        continuebtn=(Button) findViewById(R.id.continue_btn);

        depression = findViewById(R.id.st_depression_exercise_induced);
        agina = findViewById(R.id.excercise_induced_angina);
        heart_rate = findViewById(R.id.max_heart_rate);
        resting_ecg = findViewById(R.id.resting_ecg);
        blood_sugar = findViewById(R.id.fasting_blood_sugar);
        serum = findViewById(R.id.serum_cholestrol);
        blood_pressure = findViewById(R.id.resting_blood_pressure);
        chest_pain = findViewById(R.id.chest_pain_type);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.dob);
        user_name = findViewById(R.id.user);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = fStore.collection("Add_Patient").document("dsvrw42o5llBnvkTUxGB");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                age.setText(documentSnapshot.getString("DOB"));
                user_name.setText(documentSnapshot.getString("FirstName"));
                chest_pain.setText(documentSnapshot.getString("ChestPainType"));
                blood_pressure.setText(documentSnapshot.getString("BloodPressure"));
                serum.setText(documentSnapshot.getString("SerumCholesterol"));
                blood_sugar.setText(documentSnapshot.getString("FastingBloodSugar"));
                resting_ecg.setText(documentSnapshot.getString("RestingECG"));
                heart_rate.setText(documentSnapshot.getString("MaxHeartRate"));
                agina.setText(documentSnapshot.getString("Exerciseinducedangina"));
                depression.setText(documentSnapshot.getString("STdepressioninducedbyexerciserelativetorest"));
                sex.setText(documentSnapshot.getString("sex"));

            }
        });


        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(MedHistory.this, HistoryContinue.class);
                startActivity(send);   }
        });


    }

}