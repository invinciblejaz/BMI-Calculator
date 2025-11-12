package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView title;
    TextView result;
    TextView result2;

    EditText editweight;
    EditText editheight;

    Button button;
    Switch unitSwitch;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing ui elements

        title = findViewById(R.id.title);
        result = findViewById(R.id.result);
        result2 = findViewById(R.id.result2);
        editweight = findViewById(R.id.editweight);
        editheight = findViewById(R.id.editheight);
        button = findViewById(R.id.button);
        unitSwitch = findViewById(R.id.unitSwitch);

        unitSwitch.setOnCheckedChangeListener( (buttonView, isChecked) -> {
            if(isChecked){
                title.setText("BMI Calculator - Imperial");
                editheight.setHint("Enter Height (in)");
                editweight.setHint("Enter Weight (lbs)");
            }else{
                title.setText("BMI Calculator - Metric");
                editheight.setHint("Enter Height (M)");
                editweight.setHint("Enter Weight (kg)");
            }
            editheight.setText("");
            editweight.setText("");
            result.setText("");
            result2.setText("");
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = editweight.getText().toString();
                String height = editheight.getText().toString();
                double wt;
                double ht;
                double res;

                if (weight.isEmpty() || height.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter Height and Weight", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    wt = Double.parseDouble(weight);
                    ht = Double.parseDouble(height);

                    if (unitSwitch.isChecked()) {
                        res = (wt / (ht * ht)) * 703;
                    } else {
                        res = wt / (ht * ht);
                    }
                    result.setVisibility(View.INVISIBLE);
                    result2.setVisibility(View.INVISIBLE);

                    result.setText(String.format("%.2f", res)); // Set the formatted text just once


                    if (res < 18.5) {
                        result2.setText("You are Underweight");
                        result2.setTextColor(getColor(R.color.bmi_underweight));
                    } else if (res >= 18.5 && res <= 24.9) {
                        result2.setText("You are Normal");
                        result2.setTextColor(getColor(R.color.bmi_normal));
                    } else if (res >= 25 && res <= 29.9) {
                        result2.setText("You are Overweight");
                        result2.setTextColor(getColor(R.color.bmi_overweight));
                    } else {
                        result2.setText("You are Obese");
                        result2.setTextColor(getColor(R.color.bmi_obese));
                    }


                    // Make the result TextViews invisible before animating
                    result.setVisibility(View.INVISIBLE);
                    result2.setVisibility(View.INVISIBLE);

                    // Set the text for the result views
                    result.setText(String.format("%.2f", res));
                    // ... (your if/else block to set result2 text and color)

                    // Load the animation
                    Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);

                    // Apply the animation and make the views visible
                    result.setVisibility(View.VISIBLE);
                    result.startAnimation(fadeIn);

                    result2.setVisibility(View.VISIBLE);
                    result2.startAnimation(fadeIn);



                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Height and Weight", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}