package com.example.medi_app;

import static java.lang.Thread.sleep;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medi_app.ml.ModelAlzheimers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Medi_Predict extends AppCompatActivity {
TextView diaPredict, alzheimersPredict, heartPrediction;
FirebaseAuth fAuth;
FirebaseFirestore fStore;
String  age,depression, agina, heart_rate, resting_ecg
            ,blood_sugar, serum, blood_pressure, chest_pain, sex,
        intracranial_volume, dementia, mental_state, socioeconomic
        ,education, mr, pregnancies, thalassemia, vessels, exercise_st,
    norm,atlas,glucose,skin,insulin,bmi,pedigree,bloodpressure;

String prediction, sex1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi_predict);
        alzheimersPredict = findViewById(R.id.alzheimersPrediction);
        diaPredict = findViewById(R.id.diabetesPrediction);
        heartPrediction = findViewById(R.id.heartDIseasePrediction);
        //firebase stuff
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("Add_Patient").document("7ezMCyuToudArorjXIgw");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            //get variables

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                assert documentSnapshot != null;
                age=documentSnapshot.getString("DOB");
                chest_pain=documentSnapshot.getString("ChestPainType");
                blood_pressure=documentSnapshot.getString("RestingBloodPressure");
                serum=documentSnapshot.getString("SerumCholesterol");
                blood_sugar=documentSnapshot.getString("FastingBloodSugar");
                resting_ecg=documentSnapshot.getString("RestingECG");
                heart_rate=documentSnapshot.getString("MaxHeartRate");
                agina=documentSnapshot.getString("Exerciseinducedangina");
                depression=documentSnapshot.getString("STdepressioninducedbyexerciserelativetorest");
                sex = documentSnapshot.getString("sex");
                intracranial_volume=documentSnapshot.getString("EstimatedtotalIntracranial");
                dementia=documentSnapshot.getString("ClinicalDementiaRating");
                mental_state=documentSnapshot.getString("MiniMentalStateExamination");
                socioeconomic=documentSnapshot.getString("SocioeconomicStatus");
                education=documentSnapshot.getString("YearsofEducation");
                mr=documentSnapshot.getString("MRIDelayTime");
                pregnancies=documentSnapshot.getString("Pregnancies");
                thalassemia=documentSnapshot.getString("Talassemia");
                vessels=documentSnapshot.getString("Numberofmajorvessels");
                exercise_st=documentSnapshot.getString("PeakexerciseSTsegment");
                pedigree=documentSnapshot.getString("DiabetesPedigreeFunction");
                bmi=documentSnapshot.getString("BMI");
                insulin=documentSnapshot.getString("Insulin");
                skin=documentSnapshot.getString("SkinThickness");
                norm=documentSnapshot.getString("NormalizeWholeBrainvolume");
                glucose=documentSnapshot.getString("Glucose");
                atlas=documentSnapshot.getString("AtlasScalingFactor");
                bloodpressure=documentSnapshot.getString("BloodPressure");

                //set up sex and age vars

                if(sex.equals("Male")){
                    sex1 = "1";
                }
                else if(sex.equals("Female")){
                    sex1 = "0";
                }

                String[] age2 = age.split("-");
                try{
                    Integer.parseInt(age2[2]);
                }
                catch(Exception outofbounds){
                    age2 = age.split("/");
                }
                Calendar dob = Calendar.getInstance();
                Calendar today = Calendar.getInstance();

                try {
                    dob.set(Integer.parseInt(age2[0]), Integer.parseInt(age2[1]), Integer.parseInt(age2[2]));
                }
                catch(Exception another){
                    System.out.println("Age was not provided");
                }
                int agex = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

                if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
                    agex--;
                }

                Integer ageInt = new Integer(agex);
                String ageS = ageInt.toString();
                float age1 = Float.parseFloat(ageS);

                //create new AI class object
                Ai predict = new Ai();
                DecimalFormat df = new DecimalFormat("0.00");
                Map<String, Object> docData = new HashMap<>();


                    //check which patient it is
                try {
                    if (pedigree.length() > 1) {

                        float pr = Float.parseFloat(pregnancies);
                        float gl = Float.parseFloat(glucose);
                        float bl = Float.parseFloat(bloodpressure);
                        float sk = Float.parseFloat(skin);
                        float in = Float.parseFloat(insulin);
                        float bm = Float.parseFloat(bmi);
                        float pe = Float.parseFloat(pedigree);


                        float[] intArray = new float[]{pr, gl, bl, sk, in, bm, pe, age1};
                        prediction = predict.predictdiabetes(Medi_Predict.this, intArray);
                        String formatted = prediction.substring(1, prediction.length() - 1);
                        float dia = Float.parseFloat(formatted);
                        float dia2 = dia * 100;
                        String more = df.format(dia2) + "%";
                        System.out.println(dia2);
                        docData.put("diabetes_predict", more);
                        documentReference.set(docData, SetOptions.merge());
                        diaPredict.setText(df.format(dia2) + "%");

                    }
                }
                catch (Exception diaexception){
                    System.out.println(diaexception);
                    System.out.println("Diabetes problem");
                }
                try {
                    if (intracranial_volume.length() >= 1) {
                        ByteBuffer buff = ByteBuffer.allocate(10*4);
                        float sex2 = Float.parseFloat(sex1);
                        float pr = Float.parseFloat(mr);
                        float gl = Float.parseFloat(education);
                        float bl = Float.parseFloat(socioeconomic);
                        float sk = Float.parseFloat(mental_state);
                        float in = Float.parseFloat(dementia);
                        float pe = Float.parseFloat(intracranial_volume);
                        float bm = Float.parseFloat(norm);
                        float at = Float.parseFloat(atlas);
                        buff.putFloat(pr);
                        buff.putFloat(sex2);
                        buff.putFloat(age1);
                        buff.putFloat(gl);
                        buff.putFloat(bl);
                        buff.putFloat(sk);
                        buff.putFloat(in);
                        buff.putFloat(pe);
                        buff.putFloat(bm);
                        buff.putFloat(at);
                        prediction = predict.predictalzheimers(Medi_Predict.this, buff);
                        String formatted = prediction.substring(1, prediction.length() - 1);
                        float al = Float.parseFloat(formatted);
                        float al1 = al * 100;
                        String more = df.format(al1) + "%";
                        System.out.println(al1);
                        docData.put("alzheimers_predict", more);
                        documentReference.set(docData, SetOptions.merge());
                        alzheimersPredict.setText(df.format(al1) + "%");
                    }
                }
                catch (Exception alzexception){
                    System.out.println(alzexception);
                    System.out.println("Alzheimers problem");
                }
                try {
                    if (heart_rate.length() > 1) {
                        float sex2 = Float.parseFloat(sex1);
                        float pr = Float.parseFloat(chest_pain);
                        float gl = Float.parseFloat(blood_pressure);
                        float bl = Float.parseFloat(serum);
                        float sk = Float.parseFloat(blood_sugar);
                        float in = Float.parseFloat(resting_ecg);
                        float pe = Float.parseFloat(heart_rate);
                        float bm = Float.parseFloat(agina);
                        float at = Float.parseFloat(depression);
                        float ex = Float.parseFloat(exercise_st);
                        float ve = Float.parseFloat(vessels);
                        float th = Float.parseFloat(thalassemia);

                        float[] intArray = new float[]{age1, sex2, pr, gl, bl, sk, in, pe, bm, at, ex, ve, th};
                        prediction = predict.predictheart(Medi_Predict.this, intArray);
                        String formatted = prediction.substring(1, prediction.length() - 1);

                        float he = Float.parseFloat(formatted);
                        float he1 = he * 100;
                        System.out.println(he1);
                        String more = df.format(he1) + "%";
                        docData.put("heart_disease_predict", more);
                        documentReference.set(docData, SetOptions.merge());
                        heartPrediction.setText(df.format(he1) + "%");

                    }
                }
                catch (Exception heartexception){
                    System.out.println(heartexception);
                    System.out.println("Heart disease problem");
                }
                documentReference.set(docData, SetOptions.merge());
                }
        });
        }
}

