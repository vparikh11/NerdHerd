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
import android.widget.EditText;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
//import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class SendVerificationCodeActivity extends AppCompatActivity {

    EditText email;
    EditText confirm_email;
    Button verification_btn;
    Button login_btn;
    String final_result = "";
    String final_email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_verification_code);

        email = findViewById(R.id.verification_email);
        confirm_email = findViewById(R.id.confirm_verification_email);
        verification_btn = findViewById(R.id.verification_btn);
        login_btn = findViewById(R.id.login_btn);

        verification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SendVerificationCodeActivity.this;
                String email_text = email.getText().toString().toLowerCase();
                String confirm_email_text = confirm_email.getText().toString().toLowerCase();
                final_email = email_text;
                if(email_text.equals(confirm_email_text)){
                    if(true){
                        //TODO send email
                        Class enterCodeActivity = EnterCodeActivity.class;
                        Intent intent = new Intent(context, enterCodeActivity);
                        AsyncTaskRunner myTask = new AsyncTaskRunner();
                        try {
                            final_result = myTask.execute("hi").get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //myTask.execute();
                        intent.putExtra("DataJSON", final_result);
                        intent.putExtra("email_address", final_email);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder mismatchEmailAlert = new AlertDialog.Builder(context);
                        mismatchEmailAlert.setMessage("Email Address does not match one on record");
                        mismatchEmailAlert.setPositiveButton("OK", null);
                        mismatchEmailAlert.setCancelable(true);
                        mismatchEmailAlert.create().show();
                    }
                } else {
                    AlertDialog.Builder mismatchEmailAlert = new AlertDialog.Builder(context);
                    mismatchEmailAlert.setMessage("Emails do not match");
                    mismatchEmailAlert.setPositiveButton("OK", null);
                    mismatchEmailAlert.setCancelable(true);
                    mismatchEmailAlert.create().show();
                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = SendVerificationCodeActivity.this;
                Class loginActivity = LoginActivity.class;
                Intent intent = new Intent(context, loginActivity);
                startActivity(intent);
            }
        });
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
           HttpsURLConnection client = null;
           email = findViewById(R.id.verification_email);
            String result = "";
            String inputLine;
           try{
               URL url = new URL("https://nh-smartheart.herokuapp.com/sendcode");
               client = (HttpsURLConnection) url.openConnection();
               client.setRequestMethod("POST");
               //client.setRequestProperty("email","{\"email\":\"kichdi22@aol.com\"}");
               String input = "{\"email\":\"" + email.getText().toString() + "\"}";
               client.setRequestProperty("Accept","application/json");
               client.setDoOutput(true);
               OutputStream outputPost = new BufferedOutputStream(client.getOutputStream());
               outputPost.write(input.getBytes());
               outputPost.flush();
               outputPost.close();
               //Read response
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
            final_result = result;
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
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

    public boolean validEmail(){
        String email_text = email.getText().toString().toLowerCase();
        URL url = null;
        try {
            url = new URL("https://nh-smartheart.herokuapp.com/sendcode");
            Log.d("myTag", "URL POSTEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection urlConnection = null;
        try {
            urlConnection = (HttpsURLConnection) url.openConnection();
            Log.d("myTag", "URL NOT POSTEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            Log.d("myTag", urlConnection.toString());
        } catch (IOException e) {
            Log.d("myTag", "THIS IS A MALFORMED URL EXCEPTION");
            e.printStackTrace();
        }
        try {
            if (urlConnection == null){
                Log.d("myTag", "URL IS NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
            }
            OutputStream outputStream = urlConnection.getOutputStream();
            //OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            Log.d("myTag", "URL NOT POSTEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD 0");
           // InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("myTag", "URL NOT POSTEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD 1");
           // readStream(in);
            Log.d("myTag", "URL NOT POSTEDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD 2");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return true;

    }
    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
