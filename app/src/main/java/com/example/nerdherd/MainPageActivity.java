package com.example.nerdherd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainPageActivity extends AppCompatActivity {
//TODO EVERYTHING ON THIS PAGE
    ImageButton update_btn;
    TextView display_weight;
    TextView display_height;
    TextView display_risk;
    TextView hello_name;
    String final_result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        update_btn = findViewById(R.id.update);
        display_weight = findViewById(R.id.display_weight);
        display_height = findViewById(R.id.display_height);
        display_risk = findViewById(R.id.display_risk);
        hello_name = findViewById(R.id.hello_name);

        Intent JSONintent = getIntent();
        String message = JSONintent.getStringExtra("DataJSON");
        Log.d("myTagINSIDE MAIN PAGE" , message);
        //message = message.replace("\"","");
        //message = message.replace("\\","");
        final_result = message;

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainPageActivity.this;
                Class profileActivity = ProfileActivity.class;
                Intent intent = new Intent(context, profileActivity);
                intent.putExtra("DataJSON", final_result);
                startActivity(intent); //transition to signup screen
            }
        });

        Log.d("myTagINSIDE REPLACED" , message);
        Log.i("INPUT", "HELLOOOOOOO MORTALSSSSSSSS");
        try {
           // JSONObject heightObject = mainObject.getJSONObject("aheight");
            //JSONObject weightObject =  mainObject.getJSONObject("aweightp");
            JSONObject mainObject = new JSONObject(message);
            String height = mainObject.getString("aheight");
            String weight = mainObject.getString("aweightp");
            String risk = mainObject.getString("hypev_hypertension__1_Yes");
            String email = mainObject.getString("email");
            display_height.setText("Height: " + height + "inches");
            display_weight.setText("Weight: " + weight + "lbs");
            if (risk.equals("1")){
                display_risk.setText("Risk of Hypertension? Yes (PLEASE VISIT A DOCTOR)");
            }else {
                display_risk.setText("Risk of Hypertension? No");
            }
            hello_name.setText("Hello " + email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
