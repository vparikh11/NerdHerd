package com.example.nerdherd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class ProfileActivity extends AppCompatActivity {
    Button save_changes;
    String final_result = "";
    EditText email_field;
    EditText password_field;
    EditText confirm_password_field , height_field, weight_field;
    RadioGroup gender_field, hypertension_field, hypertension_history_field, alcohol_consumption_field, overweight_field, anxiety_field;
    Spinner race_fields, sad_fields, nervous_fields, restless_fields, excercise_fields, alcohol_fields;
    EditText age_field;
    RadioGroup stressed_field,diabetes_history_field, pregnant_field, smoking_field, thyroid_field, kidney_field;
    CheckBox checkbox_field;
    String alcohol_field, alcohol_never_field, alcohol_week_field, alcohol_month_field, alcohol_year_field;
    String alcohol_consumption_yes, alcohol_consumption_no, anxiety_yes, anxiety_no, overweight_yes,
            overweight_no, stressed_yes, stressed_no, thyroid_yes_no, kidney_yes_no,
            diabetes_yes, diabetes_no, diabetes_borderline, hypertension_yes, hypertension_no;
    String excercise_field, excercise_never_field, excercise_day_field, excercise_week_field, excercise_month_field,
            excercise_year_field, excercise_unable_field;
    String nervous_field, nervous_all_field, nervous_most_field, nervous_some_field, nervous_little_field, nervous_never_field;
    String pregnant_yes, pregnant_no, race_field, race_white_field, race_african_field, race_indian_field,
            race_asian_field, race_multiple_field;
    String restless_field, restless_all_field, restless_most_field, restless_some_field, restless_little_field,
            restless_never_field;
    String sad_field, sad_all_field, sad_most_field, sad_some_field, sad_little_field, sad_never_field;
    String Gender_Male, Gender_Female, smoking_every_field, smoking_some_field, smoking_none_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent JSONintent = getIntent();
        String message = JSONintent.getStringExtra("DataJSON");
        email_field = findViewById(R.id.email);
        password_field = findViewById(R.id.password);
        confirm_password_field = findViewById(R.id.confirm_password);
        height_field = findViewById(R.id.height);
        weight_field = findViewById(R.id.weight);
        gender_field = findViewById(R.id.gender_options);
        race_fields = findViewById(R.id.race);
        age_field = findViewById(R.id.age);
        hypertension_field = findViewById(R.id.hypertension_options);
        hypertension_history_field = findViewById(R.id.hypertension_hist_options);
        alcohol_consumption_field = findViewById(R.id.alcohol_consump_options);
        //alcohol_consumption_yes = findViewById(R.id.alcohol_consump_yes);
        //alcohol_consumption_no = findViewById(R.id.alcohol_consump_no);
        overweight_field = findViewById(R.id.overweight);
        anxiety_field = findViewById(R.id.anxiety_options);
        stressed_field = findViewById(R.id.stressed_options);
        diabetes_history_field = findViewById(R.id.diabetes_hist_options);
        pregnant_field = findViewById(R.id.pregnant_options);
        sad_fields = (Spinner) findViewById(R.id.sad_options);
        nervous_fields = (Spinner) findViewById(R.id.nervous_options);
        restless_fields = (Spinner) findViewById(R.id.restless_options);
        smoking_field = findViewById(R.id.smoke_hist_options);
        excercise_fields = (Spinner) findViewById(R.id.excercise_options);
        alcohol_fields = (Spinner) findViewById(R.id.alcohol_options);
        nervous_fields = (Spinner) findViewById(R.id.nervous_options);
        thyroid_field = findViewById(R.id.thyroid_hist_options);
        kidney_field = findViewById(R.id.chronic_kidney_hist_options);
        hypertension_field = findViewById(R.id.hypertension_options);

        JSONObject mainObject = null;
        try {
            mainObject = new JSONObject(message);

            String email = mainObject.getString("email");
            String password = mainObject.getString("password");
            String age = mainObject.getString("age_p_85_85__years");
            String height = mainObject.getString("aheight");
            String alcohol_never = mainObject.getString("alc5uptp_5__Drinks_Days_Time_Unit__0_Never_None");
            String alcohol_week = mainObject.getString("alc5uptp_5__Drinks_Days_Time_Unit__1_Per_week");
            String alcohol_month = mainObject.getString("alc5uptp_5__Drinks_Days_Time_Unit__2_Per_month");
            String alcohol_year = mainObject.getString("alc5uptp_5__Drinks_Days_Time_Unit__3_Per_year");
            String alcohol_comsumption_yes = mainObject.getString("alctobyr_alcohol__1_Yes");
            String alcohol_comsumption_no = mainObject.getString("alctobyr_alcohol__2_No");
            String anxiety_yes = mainObject.getString("anxnwyr_anxiety__1_Yes");
            String anxiety_no = mainObject.getString("anxnwyr_anxiety__2_No");
            String overweight_yes = mainObject.getString("aovrwtyr_overweight__1_Yes");
            String overweight_no = mainObject.getString("aovrwtyr_overweight__2_No");
            String stress_yes = mainObject.getString("astresyr_stress__1_Yes");
            String stress_no = mainObject.getString("astresyr_stress__2_No");
            String weight = mainObject.getString("aweightp");
            String cigs_per_day = mainObject.getString("cigsda1_cigs_per_day_");
            String kidney = mainObject.getString("cnkind10_kidney_cancer__1_Mentioned");
            String thyroid = mainObject.getString("cnkind28_thyroid_cancer__1_Mentioned");
            String diabetes_yes = mainObject.getString("dibev_diabetes__1_Yes");
            String diabetes_no = mainObject.getString("dibev_diabetes__2_No");
            String diabetes_borderline = mainObject.getString("dibev_diabetes__3_Borderline");
            String hispanic = mainObject.getString("hispan_i_12_Not_Hispanic_Spanish_origin");
            String hypertension_hist_yes = mainObject.getString("hypev_hypertension__1_Yes");
            String hypertension_hist__no = mainObject.getString("hypev_hypertension__2_No");
            String hypertension_yes = mainObject.getString("hypyr_hyper_for_a_year__1_Yes");
            String hypertension_no = mainObject.getString("hypyr_hyper_for_a_year__2_No");
            String activity_never = mainObject.getString("modtp_light_activity__0_Never");
            String activity_day = mainObject.getString("modtp_light_activity__1_Per_day");
            String activity_week = mainObject.getString("modtp_light_activity__2_Per_week");
            String activity_month = mainObject.getString("modtp_light_activity__3_Per_month");
            String activity_year = mainObject.getString("modtp_light_activity__4_Per_year");
            String activity_unable = mainObject.getString("modtp_light_activity__6_Unable_to_do_this_activity");
            String nervous_all = mainObject.getString("nervous_1_ALL_of_the_time");
            String nervous_most = mainObject.getString("nervous_2_MOST_of_the_time");
            String nervous_some = mainObject.getString("nervous_3_SOME_of_the_time");
            String nervous_little = mainObject.getString("nervous_4_A_LITTLE_of_the_time");
            String nervous_none = mainObject.getString("nervous_5_NONE_of_the_time");
            String pregnant_yes = mainObject.getString("pregnow_1_Yes");
            String pregnant_no = mainObject.getString("pregnow_2_No");
            String race_white = mainObject.getString("racerpi2_01_White_only");
            String race_african = mainObject.getString("racerpi2_02_Black_African_American_only");
            String race_indian = mainObject.getString("racerpi2_03_AIAN_only");
            String race_asian = mainObject.getString("racerpi2_04_Asian_only");
            String race_multiple = mainObject.getString("racerpi2_06_Multiple_race");
            String restless_all = mainObject.getString("restless_1_ALL_of_the_time");
            String restless_most = mainObject.getString("restless_2_MOST_of_the_time");
            String restless_some = mainObject.getString("restless_3_SOME_of_the_time");
            String restless_little = mainObject.getString("restless_4_A_LITTLE_of_the_time");
            String restless_none = mainObject.getString("restless_5_NONE_of_the_time");
            String rhr = mainObject.getString("rhr");
            String sad_all = mainObject.getString("sad_1_ALL_of_the_time");
            String sad_most = mainObject.getString("sad_2_MOST_of_the_time");
            String sad_some = mainObject.getString("sad_3_SOME_of_the_time");
            String sad_little = mainObject.getString("sad_4_A_LITTLE_of_the_time");
            String sad_none = mainObject.getString("sad_5_NONE_of_the_time");
            String sex_male = mainObject.getString("sex_1_Male");
            String sex_female = mainObject.getString("sex_2_Female");
            String smoking_every = mainObject.getString("smknow_smoking_frequency__1_Every_day");
            String smoking_some = mainObject.getString("smknow_smoking_frequency__2_Some_days");
            String smoking_never = mainObject.getString("smknow_smoking_frequency__3_Not_at_all");

            email_field.setText(email);
            password_field.setText(password);
            confirm_password_field.setText(password);
            height_field.setText(height);
            weight_field.setText(weight);
            age_field.setText(age);

            if (sex_male.equals("1")){
                gender_field.check(R.id.male);
            }else{
                gender_field.check(R.id.female);
            }

            if (hypertension_yes.equals("1")){
                hypertension_field.check(R.id.hypertension_yes);
            }else{
                hypertension_field.check(R.id.hypertension_no);
            }

            if (hypertension_hist_yes.equals("1")){
                hypertension_history_field.check(R.id.hypertension_hist_yes);
            }else{
                hypertension_history_field.check(R.id.hypertension_hist_no);
            }

            if (alcohol_comsumption_yes.equals("1")){
                alcohol_consumption_field.check(R.id.alcohol_consump_yes);
            }else{
                alcohol_consumption_field.check(R.id.alcohol_consump_no);
            }

            if (overweight_yes.equals("1")){
                overweight_field.check(R.id.overweight_yes);
            }else{
                overweight_field.check(R.id.overweight_no);
            }

            if (anxiety_yes.equals("1")){
                anxiety_field.check(R.id.anxiety_yes);
            }else{
                anxiety_field.check(R.id.anxiety_no);
            }

            if (stress_yes.equals("1")){
                stressed_field.check(R.id.stressed_yes);
            }else{
                stressed_field.check(R.id.stressed_no);
            }

            if (diabetes_yes.equals("1")){
                diabetes_history_field.check(R.id.diabetes_yes);
            }else if (diabetes_no.equals("1")){
                diabetes_history_field.check(R.id.diabetes_no);
            }else{
                diabetes_history_field.check(R.id.diabetes_borderline);
            }

            if (pregnant_yes.equals("1")){
                pregnant_field.check(R.id.pregnant_yes);
            }else{
                pregnant_field.check(R.id.pregnant_no);
            }

            if (smoking_every.equals("1")){
                smoking_field.check(R.id.smoke_every);
            }else if (smoking_some.equals("1")){
                smoking_field.check(R.id.smoke_some);
            }else{
                smoking_field.check(R.id.smoke_never);
            }

            if (thyroid.equals("1")){
                thyroid_field.check(R.id.thyroid_yes);
            }else{
                thyroid_field.check(R.id.thyroid_no);
            }

            if (kidney.equals("1")){
                kidney_field.check(R.id.chronic_kidney_yes);
            }else{
                kidney_field.check(R.id.chronic_kidney_no);
            }

            if (race_white.equals("1")){
                race_fields.setSelection(1);
            } else if (race_african.equals("1")){
                race_fields.setSelection(2);
            } else if (race_indian.equals("1")){
                race_fields.setSelection(3);
            } else if (race_asian.equals("1")){
                race_fields.setSelection(4);
            } else if (race_multiple.equals("1")){
                race_fields.setSelection(5);
            }

            if (sad_all.equals("1")){
                sad_fields.setSelection(0);
            } else if (sad_most.equals("1")){
                sad_fields.setSelection(1);
            } else if (sad_some.equals("1")){
                sad_fields.setSelection(2);
            } else if (sad_little.equals("1")){
                sad_fields.setSelection(3);
            } else if (sad_none.equals("1")){
                sad_fields.setSelection(4);
            }

            if (nervous_all.equals("1")){
                nervous_fields.setSelection(0);
            } else if (nervous_most.equals("1")){
                nervous_fields.setSelection(1);
            } else if (nervous_some.equals("1")){
                nervous_fields.setSelection(2);
            } else if (nervous_little.equals("1")){
                nervous_fields.setSelection(3);
            } else if (nervous_none.equals("1")){
                nervous_fields.setSelection(4);
            }

            if (restless_all.equals("1")){
                restless_fields.setSelection(0);
            } else if (restless_most.equals("1")){
                restless_fields.setSelection(1);
            } else if (restless_some.equals("1")){
                restless_fields.setSelection(2);
            } else if (restless_little.equals("1")){
                restless_fields.setSelection(3);
            } else if (restless_none.equals("1")){
                restless_fields.setSelection(4);
            }

            if (activity_never.equals("1")){
                excercise_fields.setSelection(0);
            } else if (activity_day.equals("1")){
                excercise_fields.setSelection(1);
            } else if (activity_week.equals("1")){
                excercise_fields.setSelection(2);
            } else if (activity_month.equals("1")){
                excercise_fields.setSelection(3);
            } else if (activity_year.equals("1")){
                excercise_fields.setSelection(4);
            } else if (activity_unable.equals("1")){
                excercise_fields.setSelection(5);
            }

            if (alcohol_never.equals("1")){
                alcohol_fields.setSelection(0);
            }else if (alcohol_week.equals("1")) {
                alcohol_fields.setSelection(1);
            } else if (alcohol_month.equals("1")) {
                alcohol_fields.setSelection(2);
            } else if (alcohol_year.equals("1")){
                alcohol_fields.setSelection(3);
            }

            //checkbox_field.setChecked(true);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        save_changes = findViewById(R.id.save_changes_btn);
        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = ProfileActivity.this;
                Class mainpageActivity = MainPageActivity.class;
                Intent intent = new Intent(context, mainpageActivity);
                AsyncTaskRunner myTask = new AsyncTaskRunner();
                //myTask.execute();
                try {
                    excercise_field = excercise_fields.getSelectedItem().toString();
                    if (excercise_field.equals("Never")){
                        excercise_never_field = "1";
                        excercise_day_field = "0";
                        excercise_week_field = "0";
                        excercise_month_field = "0";
                        excercise_year_field = "0";
                        excercise_unable_field = "0";
                    }else if (excercise_field.equals("Once per Day")){
                        excercise_never_field = "0";
                        excercise_day_field = "1";
                        excercise_week_field = "0";
                        excercise_month_field = "0";
                        excercise_year_field = "0";
                        excercise_unable_field = "0";
                    } else if (excercise_field.equals("Once per Week")){
                        excercise_never_field = "0";
                        excercise_day_field = "0";
                        excercise_week_field = "1";
                        excercise_month_field = "0";
                        excercise_year_field = "0";
                        excercise_unable_field = "0";
                    } else if (excercise_field.equals("Once per Month")){
                        excercise_never_field = "0";
                        excercise_day_field = "0";
                        excercise_week_field = "0";
                        excercise_month_field = "1";
                        excercise_year_field = "0";
                        excercise_unable_field = "0";
                    } else if (excercise_field.equals("Once per Year")){
                        excercise_never_field = "0";
                        excercise_day_field = "0";
                        excercise_week_field = "0";
                        excercise_month_field = "0";
                        excercise_year_field = "1";
                        excercise_unable_field = "0";
                    } else if (excercise_field.equals("Unable to do this activity")){
                        excercise_never_field = "0";
                        excercise_day_field = "0";
                        excercise_week_field = "0";
                        excercise_month_field = "0";
                        excercise_year_field = "0";
                        excercise_unable_field = "1";
                    }

                    alcohol_field = alcohol_fields.getSelectedItem().toString();
                    if (alcohol_field.equals("Never")){
                        alcohol_never_field = "1";
                        alcohol_week_field = "0";
                        alcohol_month_field = "0";
                        alcohol_year_field = "0";
                    }else if (alcohol_field.equals("5 drinks Per Week")){
                        alcohol_never_field = "0";
                        alcohol_week_field = "1";
                        alcohol_month_field = "0";
                        alcohol_year_field = "0";
                    } else if (alcohol_field.equals("5 drinks per Month")){
                        alcohol_never_field = "0";
                        alcohol_week_field = "0";
                        alcohol_month_field = "1";
                        alcohol_year_field = "0";
                    } else if (alcohol_field.equals("5 drinks per Year")){
                        alcohol_never_field = "0";
                        alcohol_week_field = "0";
                        alcohol_month_field = "0";
                        alcohol_year_field = "1";
                    }

                    nervous_field = nervous_fields.getSelectedItem().toString();
                    if (nervous_field.equals("All of the time")){
                        nervous_all_field = "1";
                        nervous_most_field = "0";
                        nervous_some_field = "0";
                        nervous_little_field = "0";
                        nervous_never_field = "0";
                    }else if (nervous_field.equals("Most of the time")){
                        nervous_all_field = "0";
                        nervous_most_field = "1";
                        nervous_some_field = "0";
                        nervous_little_field = "0";
                        nervous_never_field = "0";
                    } else if (nervous_field.equals("Some of the time")){
                        nervous_all_field = "0";
                        nervous_most_field = "0";
                        nervous_some_field = "1";
                        nervous_little_field = "0";
                        nervous_never_field = "0";
                    } else if (nervous_field.equals("A little of the time")){
                        nervous_all_field = "0";
                        nervous_most_field = "0";
                        nervous_some_field = "0";
                        nervous_little_field = "1";
                        nervous_never_field = "0";
                    } else if (nervous_field.equals("Never")){
                        nervous_all_field = "0";
                        nervous_most_field = "0";
                        nervous_some_field = "0";
                        nervous_little_field = "0";
                        nervous_never_field = "1";
                    }
                    int selectedId = alcohol_consumption_field.getCheckedRadioButtonId();
                    RadioButton radioAlcoholButton = (RadioButton) findViewById(selectedId);
                    if (radioAlcoholButton.getText().equals("Yes")){
                        alcohol_consumption_yes = "1";
                        alcohol_consumption_no = "0";
                    } else{
                        alcohol_consumption_yes = "0";
                        alcohol_consumption_no = "1";
                    }
                    selectedId = anxiety_field.getCheckedRadioButtonId();
                    RadioButton radioAnxietyButton = (RadioButton) findViewById(selectedId);
                    if (radioAnxietyButton.getText().equals("Yes")){
                        anxiety_yes = "1";
                        anxiety_no = "0";
                    } else{
                        anxiety_yes = "0";
                        anxiety_no = "1";
                    }
                    selectedId = overweight_field.getCheckedRadioButtonId();
                    RadioButton radioOverweightButton = (RadioButton) findViewById(selectedId);
                    if (radioOverweightButton.getText().equals("Yes")){
                        overweight_yes = "1";
                        overweight_no = "0";
                    } else{
                        overweight_yes = "0";
                        overweight_no = "1";
                    }
                    selectedId = stressed_field.getCheckedRadioButtonId();
                    RadioButton radioStressedtButton = (RadioButton) findViewById(selectedId);
                    if (radioStressedtButton.getText().equals("Yes")){
                        stressed_yes = "1";
                        stressed_no = "0";
                    } else{
                        stressed_yes = "0";
                        stressed_no = "1";
                    }

                    selectedId = thyroid_field.getCheckedRadioButtonId();
                    RadioButton radioThyroidButton = (RadioButton) findViewById(selectedId);
                    if (radioThyroidButton.getText().equals("Yes")){
                        thyroid_yes_no = "1";
                    } else{
                        thyroid_yes_no = "0";
                    }
                    selectedId = kidney_field.getCheckedRadioButtonId();
                    RadioButton radioKidneyButton = (RadioButton) findViewById(selectedId);
                    if (radioKidneyButton.getText().equals("Yes")){
                        kidney_yes_no = "1";
                    } else{
                        kidney_yes_no = "0";
                    }
                    checkbox_field = findViewById(R.id.terms_checkbox);

                    selectedId = diabetes_history_field.getCheckedRadioButtonId();
                    RadioButton radioDiabetesButton = (RadioButton) findViewById(selectedId);
                    if (radioDiabetesButton.getText().equals("Yes")) {
                        diabetes_yes = "1";
                        diabetes_no = "0";
                        diabetes_borderline = "0";
                    }else if (radioDiabetesButton.getText().equals("No")){
                        diabetes_yes = "0";
                        diabetes_no = "1";
                        diabetes_borderline = "0";
                    } else {
                        diabetes_yes = "0";
                        diabetes_no = "0";
                        diabetes_borderline = "1";
                    }

                    selectedId = hypertension_history_field.getCheckedRadioButtonId();
                    RadioButton radioHypertensionButton = (RadioButton) findViewById(selectedId);
                    if (radioHypertensionButton.getText().equals("Yes")){
                        hypertension_yes = "1";
                        hypertension_no = "0";
                    } else{
                        hypertension_yes = "0";
                        hypertension_no = "1";
                    }

                    selectedId = pregnant_field.getCheckedRadioButtonId();
                    RadioButton radioPregnantButton = (RadioButton) findViewById(selectedId);
                    if (radioPregnantButton.getText().equals("Yes")){
                        pregnant_yes = "1";
                        pregnant_no = "0";
                    } else{
                        pregnant_yes = "0";
                        pregnant_no = "1";
                    }

                    race_fields = (Spinner) findViewById(R.id.race);
                    race_field = race_fields.getSelectedItem().toString();
                    if (race_field.equals("White (Not Hispanic or Latino)")){
                        race_white_field = "1";
                        race_african_field = "0";
                        race_indian_field = "0";
                        race_asian_field = "0";
                        race_multiple_field = "0";
                    }else if (race_field.equals("African American")){
                        race_white_field = "0";
                        race_african_field = "1";
                        race_indian_field = "0";
                        race_asian_field = "0";
                        race_multiple_field = "0";
                    } else if (race_field.equals("American Indian and Alaska Native")){
                        race_white_field = "0";
                        race_african_field = "0";
                        race_indian_field = "1";
                        race_asian_field = "0";
                        race_multiple_field = "0";
                    } else if (race_field.equals("Asian")){
                        race_white_field = "0";
                        race_african_field = "0";
                        race_indian_field = "0";
                        race_asian_field = "1";
                        race_multiple_field = "0";
                    } else if (race_field.equals("Multiple races")){
                        race_white_field = "0";
                        race_african_field = "0";
                        race_indian_field = "0";
                        race_asian_field = "0";
                        race_multiple_field = "1";
                    }

                    restless_field = restless_fields.getSelectedItem().toString();
                    if (restless_field.equals("All of the time")){
                        restless_all_field = "1";
                        restless_most_field = "0";
                        restless_some_field = "0";
                        restless_little_field = "0";
                        restless_never_field = "0";
                    }else if (restless_field.equals("Most of the time")){
                        restless_all_field = "0";
                        restless_most_field = "1";
                        restless_some_field = "0";
                        restless_little_field = "0";
                        restless_never_field = "0";
                    } else if (restless_field.equals("Some of the time")){
                        restless_all_field = "0";
                        restless_most_field = "0";
                        restless_some_field = "1";
                        restless_little_field = "0";
                        restless_never_field = "0";
                    } else if (restless_field.equals("A little of the time")){
                        restless_all_field = "0";
                        restless_most_field = "0";
                        restless_some_field = "0";
                        restless_little_field = "1";
                        restless_never_field = "0";
                    } else if (restless_field.equals("Never")){
                        restless_all_field = "0";
                        restless_most_field = "0";
                        restless_some_field = "0";
                        restless_little_field = "0";
                        restless_never_field = "1";
                    }

                    sad_field = sad_fields.getSelectedItem().toString();
                    if (sad_field.equals("All of the time")){
                        sad_all_field = "1";
                        sad_most_field = "0";
                        sad_some_field = "0";
                        sad_little_field = "0";
                        sad_never_field = "0";
                    }else if (sad_field.equals("Most of the time")){
                        sad_all_field = "0";
                        sad_most_field = "1";
                        sad_some_field = "0";
                        sad_little_field = "0";
                        sad_never_field = "0";
                    } else if (sad_field.equals("Some of the time")){
                        sad_all_field = "0";
                        sad_most_field = "0";
                        sad_some_field = "1";
                        sad_little_field = "0";
                        sad_never_field = "0";
                    } else if (sad_field.equals("A little of the time")){
                        sad_all_field = "0";
                        sad_most_field = "0";
                        sad_some_field = "0";
                        sad_little_field = "1";
                        sad_never_field = "0";
                    } else if (sad_field.equals("Never")){
                        sad_all_field = "0";
                        sad_most_field = "0";
                        sad_some_field = "0";
                        sad_little_field = "0";
                        sad_never_field = "1";
                    }

                    selectedId = gender_field.getCheckedRadioButtonId();
                    RadioButton radioGenderButton = (RadioButton) findViewById(selectedId);
                    if (radioGenderButton.getText().equals("Male")){
                        Gender_Male = "1";
                        Gender_Female = "0";
                    } else{
                        Gender_Male = "0";
                        Gender_Female = "1";
                    }

                    selectedId = smoking_field.getCheckedRadioButtonId();
                    RadioButton radioSmokingButton = (RadioButton) findViewById(selectedId);
                    if (radioSmokingButton.getText().equals("Every day")){
                        smoking_every_field = "1";
                        smoking_some_field = "0";
                        smoking_none_field = "0";
                    } else if (radioSmokingButton.getText().equals("Some days")){
                        smoking_every_field = "0";
                        smoking_some_field = "1";
                        smoking_none_field = "0";
                    } else if (radioSmokingButton.getText().equals("Not at all")){
                        smoking_every_field = "0";
                        smoking_some_field = "0";
                        smoking_none_field = "1";
                    }
                    final_result = myTask.execute("hi").get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("myTag in main", final_result);
                if (!(final_result.equals(""))){
                    intent.putExtra("DataJSON", final_result);
                    startActivity(intent); //transition to main page screen
                }else{
                    AlertDialog.Builder mismatchPasswords = new AlertDialog.Builder(context);
                    mismatchPasswords.setMessage(final_result);
                    mismatchPasswords.setCancelable(true);
                    mismatchPasswords.setPositiveButton("OK", null);
                    mismatchPasswords.create().show();
                }
                //startActivity(intent); //transition to signup screen
            }
        });
    }



    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection client = null;
            String result = "";
            String inputLine;
            try{
                URL url = new URL("https://nh-smartheart.herokuapp.com/update");
                client = (HttpsURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                //client.setRequestProperty("email","{\"email\":\"kichdi22@aol.com\"}");
                String input = "{\n" +
                        "\t\"email\": \"" + email_field.getText().toString() +  "\",\n" +
                        "\t\"password\": \"" + password_field.getText().toString() +  "\",\n" +
                        "\t\"account_creation_date_time\": \"" + "0000-00-00 00:00:00" +  "\",\n" +
                        "\t\"age_p_85_85__years\": \"" + age_field.getText().toString() +  "\",\n" +
                        "\t\"aheight\": \"" + height_field.getText().toString() +  "\",\n" +
                        "\t\"alc5uptp_5__Drinks_Days_Time_Unit__0_Never_None\": \"" + alcohol_never_field +  "\",\n" +
                        "\t\"alc5uptp_5__Drinks_Days_Time_Unit__1_Per_Week\": \"" + alcohol_week_field +  "\",\n" +
                        "\t\"alc5uptp_5__Drinks_Days_Time_Unit__2_Per_Month\": \"" + alcohol_month_field +  "\",\n" +
                        "\t\"alc5uptp_5__Drinks_Days_Time_Unit__3_Per_Year\": \"" + alcohol_year_field +  "\",\n" +
                        "\t\"alctobyr_alcohol__1_Yes\": \"" + alcohol_consumption_yes +  "\",\n" +
                        "\t\"alctobyr_alcohol__2_No\": \"" + alcohol_consumption_no +  "\",\n" +
                        "\t\"anxnwyr_anxiety__1_Yes\": \"" + anxiety_yes +  "\",\n" +
                        "\t\"anxnwyr_anxiety__2_No\": \"" + anxiety_no +  "\",\n" +
                        "\t\"aovrwtyr_overweight__1_Yes\": \"" + overweight_yes +  "\",\n" +
                        "\t\"aovrwtyr_overweight__2_No\": \"" + overweight_no +  "\",\n" +
                        "\t\"astresyr_stress__1_Yes\": \"" + stressed_yes +  "\",\n" +
                        "\t\"astresyr_stress__2_No\": \"" + stressed_no +  "\",\n" +
                        "\t\"aweightp\": \"" + weight_field.getText().toString() +  "\",\n" +
                        "\t\"cigsda1_cigs_per_day_\": \"" + "2" +  "\",\n" +
                        "\t\"cnkind10_kidney_cancer__1_Mentioned\": \"" + kidney_yes_no +  "\",\n" +
                        "\t\"cnkind28_thyroid_cancer__1_Mentioned\": \"" + thyroid_yes_no +  "\",\n" +
                        "\t\"dibev_diabetes__1_Yes\": \"" + diabetes_yes +  "\",\n" +
                        "\t\"dibev_diabetes__2_No\": \"" + diabetes_no +  "\",\n" +
                        "\t\"dibev_diabetes__3_Borderline\": \"" + diabetes_borderline +  "\",\n" +
                        "\t\"hispan_i_12_Not_Hispanic_Spanish_origin\": \"" + "1" +  "\",\n" +
                        "\t\"hypev_hypertension__1_Yes\": \"" + "0" +  "\",\n" +
                        "\t\"hypev_hypertension__2_No\": \"" + "0" +  "\",\n" +
                        "\t\"hypyr_hyper_for_a_year__1_Yes\": \"" + hypertension_yes +  "\",\n" +
                        "\t\"hypyr_hyper_for_a_year__2_No\": \"" + hypertension_no +  "\",\n" +
                        "\t\"modtp_light_activity__0_Never\": \"" + excercise_never_field +  "\",\n" +
                        "\t\"modtp_light_activity__1_Per_day\": \"" + excercise_day_field +  "\",\n" +
                        "\t\"modtp_light_activity__2_Per_week\": \"" + excercise_week_field +  "\",\n" +
                        "\t\"modtp_light_activity__3_Per_month\": \"" + excercise_month_field +  "\",\n" +
                        "\t\"modtp_light_activity__4_Per_yea\": \"" + excercise_year_field +  "\",\n" +
                        "\t\"modtp_light_activity__6_Unable_to_do_this_activity\": \"" + excercise_unable_field +  "\",\n" +
                        "\t\"nervous_1_ALL_of_the_time\": \"" + nervous_all_field +  "\",\n" +
                        "\t\"nervous_2_MOST_of_the_time\": \"" + nervous_most_field +  "\",\n" +
                        "\t\"nervous_3_SOME_of_the_time\": \"" + nervous_some_field +  "\",\n" +
                        "\t\"nervous_4_A_LITTLE_of_the_time\": \"" + nervous_little_field +  "\",\n" +
                        "\t\"nervous_5_NONE_of_the_time\": \"" + nervous_never_field +  "\",\n" +
                        "\t\"pregnow_1_Yes\": \"" + pregnant_yes +  "\",\n" +
                        "\t\"pregnow_2_No\": \"" + pregnant_no +  "\",\n" +
                        "\t\"racerpi2_01_White_only\": \"" + race_white_field +  "\",\n" +
                        "\t\"racerpi2_02_Black_African_American_only\": \"" + race_african_field +  "\",\n" +
                        "\t\"racerpi2_03_AIAN_only\": \"" + race_indian_field +  "\",\n" +
                        "\t\"racerpi2_04_Asian_only\": \"" + race_asian_field +  "\",\n" +
                        "\t\"racerpi2_06_Multiple_race\": \"" + race_multiple_field +  "\",\n" +
                        "\t\"restless_1_ALL_of_the_time\": \"" + restless_all_field +  "\",\n" +
                        "\t\"restless_2_MOST_of_the_time\": \"" + restless_most_field +  "\",\n" +
                        "\t\"restless_3_SOME_of_the_time\": \"" + restless_some_field +  "\",\n" +
                        "\t\"restless_4_A_LITTLE_of_the_time\": \"" + restless_little_field +  "\",\n" +
                        "\t\"restless_5_NONE_of_the_time\": \"" + restless_never_field +  "\",\n" +
                        "\t\"rhr\": \"" + "60" +  "\",\n" +
                        "\t\"sad_1_ALL_of_the_time\": \"" + sad_all_field +  "\",\n" +
                        "\t\"sad_2_MOST_of_the_time\": \"" + sad_most_field +  "\",\n" +
                        "\t\"sad_3_SOME_of_the_time\": \"" + sad_some_field +  "\",\n" +
                        "\t\"sad_4_A_LITTLE_of_the_time\": \"" + sad_little_field +  "\",\n" +
                        "\t\"sad_5_NONE_of_the_time\": \"" + sad_never_field +  "\",\n" +
                        "\t\"sex_1_Male\": \"" + Gender_Male +  "\",\n" +
                        "\t\"sex_2_Female\": \"" + Gender_Female +  "\",\n" +
                        "\t\"smknow_smoking_frequency__1_Every_day\": \"" + smoking_every_field +  "\",\n" +
                        "\t\"smknow_smoking_frequency__2_Some_days\": \"" + smoking_some_field +  "\",\n" +
                        "\t\"smknow_smoking_frequency__3_Not_at_all\":\"" + smoking_none_field + "\"\n" +
                        "}";
                client.setRequestProperty("Accept","application/json");
                client.setDoOutput(true);
                OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
                outputPost.write(input.getBytes());
                outputPost.flush();
                outputPost.close();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(client.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.i("INPUT", String.valueOf(client.getInputStream()));
                Log.i("STATUS", String.valueOf(client.getResponseCode()));
                Log.i("MSG" , client.getResponseMessage());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(client != null) // Make sure the connection is not null.
                    client.disconnect();
            }
            Log.d("myTag", "URL POSTEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            //Log.d("myTag", result);
            final_result = result;
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            // progressDialog.dismiss();
            // finalResult.setText(result);
            Log.d("postexec", result);
            final_result = result;
        }


        @Override
        protected void onPreExecute() {
            //progressDialog = ProgressDialog.show(MainActivity.this,
            //  "ProgressDialog",
            //  "Wait for "+time.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            // finalResult.setText(text[0]);

        }
    }
}
