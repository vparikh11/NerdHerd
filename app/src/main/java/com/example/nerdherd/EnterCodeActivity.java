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

public class EnterCodeActivity extends AppCompatActivity {

    Button enter_btn;
    Button resend_code_btn;
    EditText code_text;
    String final_result = "";
    String final_message = "";
    String final_email = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);

        enter_btn = findViewById(R.id.enter_btn);
        resend_code_btn = findViewById(R.id.resend_code_btn);
        code_text = findViewById(R.id.enter_code);

        Intent JSONintent = getIntent();
        String message = JSONintent.getStringExtra("DataJSON");
        String email_text = JSONintent.getStringExtra("email_address");
        final_email = email_text;
        final_message = message;
        Log.d("MESSAGE = " , message);
        Log.d("EMAIL_TEXT = " , email_text);

        enter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = EnterCodeActivity.this;
                if(checkCode()){
                    // Go to reset password page
                    Class resetPasswordActivity = ResetPasswordActivity.class;
                    Intent intent = new Intent(context, resetPasswordActivity);
                    String code = code_text.getText().toString();
                    String JSON = "{'code':code}";
                    //intent.putExtra("email_field", final_email);
                    AsyncTaskRunner myTask = new AsyncTaskRunner();
                    try {
                        final_result = myTask.execute("hi").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("myTag in main", final_result);
                    intent.putExtra("DataJSON", final_result);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder codeErrorAlert = new AlertDialog.Builder(context);
                    codeErrorAlert.setMessage("Incorrect Code");
                    codeErrorAlert.setPositiveButton("OK", null);
                    codeErrorAlert.setCancelable(true);
                    codeErrorAlert.create().show();
                }
            }
        });
        resend_code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO resend verification code
            }
        });
    }

    public boolean checkCode(){
        String code = code_text.getText().toString();
        JSONObject mainObject = null;
        try {
            mainObject = new JSONObject(final_message);
            String final_code = mainObject.getString("code");
            if (final_code.equals(code))
                return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            try{
                URL url = new URL("https://nh-smartheart.herokuapp.com/reset");
                client = (HttpsURLConnection) url.openConnection();
                client.setRequestMethod("POST");

                String input = "{\n" +
                        "\t\"email\":\"" + final_email + "\"\n" +
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
            Log.i("FINAL RESULT = " , final_result);
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
