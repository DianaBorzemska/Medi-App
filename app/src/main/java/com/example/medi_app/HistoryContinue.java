package com.example.medi_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HistoryContinue extends AppCompatActivity {
    TextView intracranial_volume, dementia, mental_state, socioeconomic
            ,education, mr, pregnancies, thalassemia, vessels, exercise_st, user_name;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button continuebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cont);
        continuebtn=(Button) findViewById(R.id.continueBtn1);

        intracranial_volume = findViewById(R.id.estimated_tot_intracranial_vol);
        dementia = findViewById(R.id.clinical_dementia_rating);
        mental_state = findViewById(R.id.mental_state_exam);
        socioeconomic = findViewById(R.id.social_status);
        education = findViewById(R.id.education_years);
        mr = findViewById(R.id.mri_delay);
        pregnancies = findViewById(R.id.pregnancies);
        thalassemia = findViewById(R.id.thalassemia);
        vessels = findViewById(R.id.major_vessels_num);
        exercise_st = findViewById(R.id.peak_ex_st);
        user_name = findViewById(R.id.user);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = fStore.collection("Add_Patient").document("dsvrw42o5llBnvkTUxGB");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                intracranial_volume.setText(documentSnapshot.getString("EstimatedtotalIntracranial"));
                user_name.setText(documentSnapshot.getString("FirstName"));
                dementia.setText(documentSnapshot.getString("ClinicalDementiaRating"));
                mental_state.setText(documentSnapshot.getString("MiniMentalStateExamination"));
                socioeconomic.setText(documentSnapshot.getString("SocioeconomicStatus"));
                education.setText(documentSnapshot.getString("YearsofEducation"));
                mr.setText(documentSnapshot.getString("MRIDelayTime"));
                pregnancies.setText(documentSnapshot.getString("Pregnancies"));
                thalassemia.setText(documentSnapshot.getString("Talassemia"));
                vessels.setText(documentSnapshot.getString("Numberofmajorvessels"));
                exercise_st.setText(documentSnapshot.getString("PeakexerciseSTsegment"));


            }
        });
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(HistoryContinue.this, HistorySubmit.class);
                startActivity(send);   }
        });

    }
}