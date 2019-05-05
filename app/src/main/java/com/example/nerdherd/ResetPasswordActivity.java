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

public class ResetPasswordActivity extends AppCompatActivity {

    EditText newPassword;
    EditText confirmNewPassword;
    Button done_btn;
    String initial_JSON = "";
    String final_result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        newPassword = findViewById(R.id.new_password);
        confirmNewPassword = findViewById(R.id.confirm_new_password);
        done_btn = findViewById(R.id.done_btn);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = ResetPasswordActivity.this;
                String newPass = newPassword.getText().toString();
                String confNewPass = confirmNewPassword.getText().toString();
                Intent JSONintent = getIntent();
                String message = JSONintent.getStringExtra("DataJSON");
                initial_JSON = message;
                //
                if(newPass.equals(confNewPass)){
                    //TODO send new password to DB
                    Class loginActivity = LoginActivity.class;
                    Intent intent = new Intent(context, loginActivity);
                    AsyncTaskRunner myTask = new AsyncTaskRunner();
                    try {
                        final_result = myTask.execute("hi").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                } else {
                    AlertDialog.Builder mismatchPasswords = new AlertDialog.Builder(context);
                    mismatchPasswords.setMessage("Passwords do not match");
                    mismatchPasswords.setCancelable(true);
                    mismatchPasswords.setPositiveButton("OK", null);
                    mismatchPasswords.create().show();
                }
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
            newPassword = findViewById(R.id.new_password);
            try{
                URL url = new URL("https://nh-smartheart.herokuapp.com/update");
                client = (HttpsURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                JSONObject mainObject = new JSONObject(initial_JSON);
                String old_password = mainObject.getString("password");
                String input = initial_JSON.replace(old_password, newPassword.getText().toString()).toString();
//                String input = "{\n" +
//                        "\t\"email\":\"" + email_address + "\"\n" +
//                        "}";
                //String input = initial_JSON.toString();
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
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
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
