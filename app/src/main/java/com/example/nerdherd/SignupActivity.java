package com.example.nerdherd;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class SignupActivity extends AppCompatActivity {

    Button confirm_signup_btn;
    EditText email_field;
    EditText password_field;
    EditText confirm_password_field , height_field, weight_field;
    RadioGroup gender_field, hypertension_field, hypertension_history_field, alcohol_consumption_field, overweight_field, anxiety_field;
    Spinner race_fields, sad_fields, nervous_fields, restless_fields, excercise_fields, alcohol_fields;
    EditText age_field;
    RadioGroup stressed_field,diabetes_history_field, pregnant_field, smoking_field, thyroid_field, kidney_field;
    CheckBox checkbox_field;
    String final_result = "";
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
        setContentView(R.layout.activity_signup);

        //initializing the layout widgets
        confirm_signup_btn = findViewById(R.id.signup_confirm_btn);

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

        //setting intents for signup btn
        confirm_signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SignupActivity.this;

                //go to main page if every field is valid
                if(validityCheck()){
                    //Class mainPageActivity = MainPageActivity.class;
                    Class loginActivity = LoginActivity.class;
                    //Intent intent = new Intent(context, mainPageActivity);
                    Intent intent = new Intent(context, loginActivity);
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
                    //intent.putExtra("DataJSON", final_result);
                    startActivity(intent);
                } else {
                    //TODO if not every field valid then highlight the problem and remain on signup page
                        //TODO cont. maybe a TOAST msg or something similar
                }


            }
        });

        //hiding password fields
        password_field.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        confirm_password_field.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    public static boolean validityCheck(){
        return true;
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
                URL url = new URL("https://nh-smartheart.herokuapp.com/signup");
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
                //Log.i("Login EMAIL 1", input1);
                Log.i("Login EMAIL 2", input);
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
            Log.i("MSG" , final_result);
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
