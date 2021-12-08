package com.example.medi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.medi_app.ml.ModelAlzheimers;

import org.jetbrains.annotations.NotNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.File;
import java.io.IOException;

public class Medi_Predict extends AppCompatActivity {
TextView diaPredict, alzheimersPredict, heartPrediction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi_predict);
        alzheimersPredict = findViewById(R.id.alzheimersPrediction);
        diaPredict = findViewById(R.id.diabetesPrediction);
        heartPrediction = findViewById(R.id.heartDIseasePrediction);

        Ai predict = new Ai();
        float[] intArray = new float[]{ 2,138,62,35,0, (float) 33.6, (float) 0.127,47};
        String prediction = predict.predictdiabetes(Medi_Predict.this, intArray);
        //String prediction = predict.predictheart(Medi_Predict.this, intArray);
        //String prediction = predict.predictalzheimers(Medi_Predict.this, intArray);
        char num = prediction.charAt(1);
        char num1 = prediction.charAt(3);
        if (Character.getNumericValue(num) == 1){
            diaPredict.setText("You are not sick");

        }
        else if(Character.getNumericValue(num1) >= 5){
            diaPredict.setText("You are sick");
        }
       else{
            diaPredict.setText("You are not sick");
        }

        }

}

