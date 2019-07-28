package com.example.amitycodingclub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    //General variable declaration.
    EditText etEmail,etPassward;
    Button btnLognin;
    TextView btnsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.mainactivity_edittext_email);
        etPassward = findViewById(R.id.mainactivity_edittext_password);
        btnLognin = findViewById(R.id.mainactivity_button_login);
        btnsignup = findViewById(R.id.mainactivity_button_signup);

        btnLognin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:sign in request.
                BackgroundWorker backgroundWorker = new BackgroundWorker(getApplication());
                backgroundWorker.execute("login",etEmail.getText().toString(),etPassward.getText().toString());
            }
        });
    }
    public class BackgroundWorker extends AsyncTask<String, String, String> {
        Context context;
        AlertDialog alertDialog;
        String user = "nouser";
        private Constants constants;
        private String ip;



        @Override
        protected String doInBackground(String... strings) {
            String type = strings[0];
            constants = new Constants(context);
            String login_url = "https://acc.amityaump.com/wp-json/app-gateway/login";

            if (type.equals("login")){
                try {
                    String username = strings[1];
                    String password = strings[2];
                    Log.i("status","inside the login try catch");
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoOutput(true);
                    Log.i("status","Http url connection established properly");

                    OutputStream outputStream = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    Log.i("status","buffer writer working");

                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    Log.i("status","string post_data concatenation successful");

                    bufferedWriter.write(post_data);
                    Log.i("status","bufferedWriter.write(post_data) executed successfully");

                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    //reading response for feedback
                    Log.i("status","now reading feedback");


                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result = "";
                    String line  = "";
                    while ((line = bufferedReader.readLine())!=null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "error";
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("LoginStatus");
        }

        @Override
        protected void onProgressUpdate(String... values) {

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("ssss",s);
//            try {
//                boolean loginSuccessful = s.contains("loginsuccess");
//                boolean incorrectPassword = s.contains("incorrectpassword");
//                boolean userNotFound = s.contains("userdoesnotexist");
//                boolean vneed = s.contains("vneed");
//                Log.i("SessionId",s);
//
//                if (loginSuccessful) {
//                    //alertDialog.setMessage("Login Successful!");
//                    try {
//                        JSONObject jsonObj = new JSONObject(s);
//                        constants.setEmail(jsonObj.getString("email"));
//                        constants.setPhone(jsonObj.getString("phone"));
//                        constants.setName(jsonObj.getString("name"));
//                        constants.setGender(jsonObj.getString("gender"));
//                        constants.setDob(jsonObj.getString("dob"));
//                        String p = "";
//                        for(int k=0;k<Integer.parseInt(jsonObj.getString("password_len"));k++){
//                            p+="•";
//                        }
//                        constants.setPassword_coded(p);
//
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                    Intent i = new Intent(context,HomeActivity.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    //Generating SESSION_ID
//                    //s = s.replace("loginsuccess","");
//
//                    context.startActivity(i);
//                    return;
//                } else if (incorrectPassword) {
//                    alertDialog.setMessage("Incorrect Password!");
//                    Log.i("info",s);
//                } else if (userNotFound) {
//                    alertDialog.setMessage("User does not exist!");
//                } else if(vneed){
//                    Intent i = new Intent(getApplication(),VerifyEmail.class);
//                    i.putExtra("email",user);
//                    startActivity(i);
//                } else {
//                    alertDialog.setMessage("Unknown error!");
//                    Log.i("error", s);
//                }
//                alertDialog.show();
//            }catch (Exception e){
//                e.printStackTrace();
//                Log.i("Exception","e");
//            }
            if (s.contains("true")){
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context, "Login Unsuccessfull", Toast.LENGTH_SHORT).show();
            }
        }

        public BackgroundWorker(Context ctx) {
            context = ctx;
        }
    }
}
