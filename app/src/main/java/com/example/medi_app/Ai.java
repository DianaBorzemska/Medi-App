package com.example.medi_app;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medi_app.ml.ModelAlzheimers;
import com.example.medi_app.ml.ModelDiabetes;
import com.example.medi_app.ml.ModelHeart;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.util.Arrays;

public class Ai extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public String predictalzheimers(Context context, float[] intArray) {
        try {
            ModelAlzheimers model = ModelAlzheimers.newInstance(context);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1,10}, DataType.FLOAT32);
            //TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(intArray, DataType.FLOAT32);
            inputFeature0.loadArray(intArray);
            // Runs model inference and gets result.
            ModelAlzheimers.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            String bla = Arrays.toString(outputFeature0.getFloatArray());
            model.close();
            return bla;
            // Releases model resources if no longer used.

        } catch (IOException e) {
            System.out.println("Io error with alzheimers prediction");
        }
        return "haha";
    }

    public String predictdiabetes(Context context, float[] intArray){
        try {
            ModelDiabetes model = ModelDiabetes.newInstance(context);
            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 8}, DataType.FLOAT32);
            inputFeature0.loadArray(intArray);

            // Runs model inference and gets result.
            ModelDiabetes.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            // Releases model resources if no longer used.


            String bla = Arrays.toString(outputFeature0.getFloatArray());
            model.close();
            return bla;
        } catch (IOException e) {
            System.out.println("Io error with diabetes prediction");
        }
        return "haha";
    }

    public String predictheart(Context context, float[] intArray){

        try {
            ModelHeart model = ModelHeart.newInstance(context);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 13}, DataType.FLOAT32);
            inputFeature0.loadArray(intArray);

            // Runs model inference and gets result.
            ModelHeart.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            // Releases model resources if no longer used.
            model.close();


            String bla = Arrays.toString(outputFeature0.getFloatArray());
            model.close();
            return bla;
        } catch (IOException e) {
            System.out.println("Io error with heart prediction");
        }
        return "haha";
    }
}
