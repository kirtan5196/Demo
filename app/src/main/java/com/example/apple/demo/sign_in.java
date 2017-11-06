package com.example.apple.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class sign_in extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 19) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.activity_sign_in);

        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);


        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = editText5.getText().toString();
                String pass = editText6.getText().toString();

                if (email.isEmpty() && pass.isEmpty()) {
                    Toast toast = Toast.makeText(sign_in.this, "Please fill up all values", Toast.LENGTH_LONG);
                    toast.show();
                } else if (email.equals("sunil") && pass.equals("zoomxpass")) {
                    Toast.makeText(getApplicationContext(), "sign in successful", Toast.LENGTH_LONG).show();

                    //String url = "https://ikidzcare.com/JSON/LoginService.svc/ValidateUser?UserName=admin@testschool&Password=adminpw@testschool&SchoolId=5ea2354f-ddf3-4706-b68d-3976f76b69b6";

                    login();
                   // loginjson1();


                } else {
                    Intent intent = new Intent(sign_in.this, MainActivity.class);
                    intent.putExtra("Thank You", email);
                    startActivity(intent);
                }
            }
        });
    }

    public void login() {

        final EditText editText5 = (EditText) findViewById(R.id.editText5);
        final EditText editText6 = (EditText) findViewById(R.id.editText6);

        String email = editText5.getText().toString();
        String pass = editText6.getText().toString();

        Log.e("username", ">" + email);
        Log.e("password", ">" + pass);

        String requestURL = "https://ikidzcare.com/JSON/LoginService.svc/ValidateUser?UserName=admin@testschool&Password=adminpw@testschool&SchoolId=5ea2354f-ddf3-4706-b68d-3976f76b69b6";


        try {
            HttpUtility.sendGetRequest(requestURL);
           // httpURLConnection.connect();
            String response = HttpUtility.readSingleLineRespone();

            Log.e("response", ">" + response);

            JSONObject jsonObject = new JSONObject(String.valueOf(response));
            String CurrentSchoolYearId = jsonObject.getString("CurrentSchoolYearId");
            Log.e("CurrentSchoolYearId" ,">"+CurrentSchoolYearId);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpUtility.disconnect();

        /*
        String url = "https://www.zoomx.us/zoomx/app/login/";
        JSONObject jsonBody = null;
        try {
            jsonBody = new JSONObject();
            jsonBody.put("uid", email);
            jsonBody.put("pwd", pass);

            Log.e("JasonRequest",""+jsonBody);
        } catch (JSONException e) {
        }

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            //Creating the json object from the response"

                            Log.e("Response" ,">"+ response);
                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            String result = jsonObject.getString("result");
                            Log.e("result" ,">"+result);
                            String message = jsonObject.getString("message");
                            Log.e("result" ,">"+message);

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println(error.getMessage());


                    }
                }){
        };

        client.getInstance().getRequestQueue().add(jsObjRequest);
*/
    }

    public void loginjson1()

    {
        String result = null;
        try {

            final EditText editText5 = (EditText) findViewById(R.id.editText5);
            final EditText editText6 = (EditText) findViewById(R.id.editText6);

            String email = editText5.getText().toString();
            String pass = editText6.getText().toString();

            Log.e("username", ">" + email);
            Log.e("password", ">" + pass);

            String requestURL = "https://ikidzcare.com/JSON/LoginService.svc/ValidateUser?UserName=admin@testschool&Password=adminpw@testschool&SchoolId=5ea2354f-ddf3-4706-b68d-3976f76b69b6";

            HttpURLConnection con = (HttpURLConnection)HttpUtility.sendGetRequest(requestURL);
            StringBuilder sb = new StringBuilder();

            Log.e("con.getResponseCode", "===" + con.getResponseCode());

            con.setAllowUserInteraction(false);
            con.setInstanceFollowRedirects(true);
            //con.setRequestMethod("GET");
            //con.connect();


            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // BufferedReader bufferedReader = new BufferedReader(new
                // InputStreamReader(con.getInputStream()));
                try {
                    InputStream in = new BufferedInputStream(con.getInputStream());

                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(in));

                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        System.out.println(requestURL + "kkkkkkkkk------" + json);
                        sb.append(json + "\n");
                    }
                } catch (Exception e) {

                    e.printStackTrace();

                }
                Log.i("json", "===" + sb.toString().trim());
                result = sb.toString().trim();
            } else {
                result = null;
            }

        } catch (Exception e) {

        }
    }


}
