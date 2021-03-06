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
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.json.*;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    Button signup_btn;
    Button forgotPasswordBtn;
    EditText login_email;
    EditText login_password;
    String inline = "";
    String final_result = "";

    public String getFinal_result(){
        return final_result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initializing the buttons
        login_btn = findViewById(R.id.login);
        signup_btn = findViewById(R.id.signup_btn);
        forgotPasswordBtn = findViewById(R.id.forgot_password);
        login_email = findViewById(R.id.login_email_field);
        login_password = findViewById(R.id.login_password_field);

        //signup activity intent

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LoginActivity.this;
                Class signupActivity = SignupActivity.class;
                Intent intent = new Intent(context, signupActivity);
                startActivity(intent); //transition to signup screen
            }
        });

        //login activity intent
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LoginActivity.this;
                if(true){
                    Class mainPageActivity = MainPageActivity.class;
                    Intent intent = new Intent(context, mainPageActivity);
                    String email = login_email.getText().toString();
                    String password = login_password.getText().toString();
                    //String JSON = "{'email':email,'password':password}";
                    AsyncTaskRunner myTask = new AsyncTaskRunner();
                    //myTask.execute();
                    try {
                        final_result = myTask.execute("hi").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("myTag in main", final_result);
                    if (final_result.contains("valid")){
                        AlertDialog.Builder mismatchPasswords = new AlertDialog.Builder(context);
                        mismatchPasswords.setMessage(final_result);
                        mismatchPasswords.setCancelable(true);
                        mismatchPasswords.setPositiveButton("OK", null);
                        mismatchPasswords.create().show();
                    }else{
                        intent.putExtra("DataJSON", final_result);
                        startActivity(intent); //transition to main page screen
                    }
                   // startActivity(intent); //transition to main page screen
                } else {
                    AlertDialog.Builder mismatchPasswords = new AlertDialog.Builder(context);
                    mismatchPasswords.setMessage("Invalid Email or Password");
                    mismatchPasswords.setCancelable(true);
                    mismatchPasswords.setPositiveButton("OK", null);
                    mismatchPasswords.create().show();
                }

            }
        });

        //forgot password intent
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = LoginActivity.this;
                Class sendVerificationCodeActivity = SendVerificationCodeActivity.class;
                Intent intent = new Intent(context, sendVerificationCodeActivity);
                startActivity(intent);
            }
        });

    }



    public boolean accountVerification(){
        //TODO check if email and password are a correct match
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();
        return false;
    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection client = null;
            String result = "";
            String inputLine;
            login_email = findViewById(R.id.login_email_field);
            login_password = findViewById(R.id.login_password_field);
            try{
                URL url = new URL("https://nh-smartheart.herokuapp.com/login");
                client = (HttpsURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                //client.setRequestProperty("email","{\"email\":\"kichdi22@aol.com\"}");
//                String input = "{\n" +
//                        "\t\"email\":\"test7@gmail.com\" ,\n" +
//                        "\t\"password\":\"password7\"\n" +
//                        "}";
                String input = "{\n" +
                        "\t\"email\": \"" + login_email.getText().toString() +  "\",\n" +
                        "\t\"password\":\"" + login_password.getText().toString() + "\"\n" +
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
