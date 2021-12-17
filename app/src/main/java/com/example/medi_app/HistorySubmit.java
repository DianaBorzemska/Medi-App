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

public class HistorySubmit extends AppCompatActivity {
    TextView bloodpressure, pedigree, bmi, insulin, skin, norm_brain_vol, glucose, atlas, user_name, brain;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_cont1);
        submitBtn=(Button) findViewById(R.id.submitBtn);
        glucose = findViewById(R.id.glucose);
        bloodpressure = findViewById(R.id.bloodpressure);
        skin = findViewById(R.id.skinThickness);
        insulin = findViewById(R.id.insulin);
        bmi = findViewById(R.id.bmi);
        pedigree = findViewById(R.id.diabetesPedigreeFunction);
        user_name = findViewById(R.id.user_name);
        atlas = findViewById(R.id.atlas_scaling_factor);
        brain = findViewById(R.id.norm_brain_vol);



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = fStore.collection("Add_Patient").document("dsvrw42o5llBnvkTUxGB");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                user_name.setText(documentSnapshot.getString("FirstName"));
                pedigree.setText(documentSnapshot.getString("DiabetesPedigreeFunction"));
                bmi.setText(documentSnapshot.getString("BMI"));
                insulin.setText(documentSnapshot.getString("Insulin"));
                skin.setText(documentSnapshot.getString("SkinThickness"));
                brain.setText(documentSnapshot.getString("NormalizeWholeBrainvolume"));
                glucose.setText(documentSnapshot.getString("Glucose"));
                atlas.setText(documentSnapshot.getString("AtlasScalingFactor"));
                bloodpressure.setText(documentSnapshot.getString("BloodPressure"));


            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent send = new Intent(HistorySubmit.this, MainActivity.class);
                startActivity(send);   }
        });
    }
}