package com.example.nikhilsridhar.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;



public class Register extends Activity  {







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText  etName     = (EditText) findViewById(R.id.etName);
        final EditText etAge      = (EditText) findViewById(R.id.etAge);
        final EditText etUsernameR = (EditText) findViewById(R.id.etUsernameR);
        final EditText etPasswordR = (EditText) findViewById(R.id.etPasswordR);

        final Button bRegister  = (Button)   findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
    public void onClick(View view) {


            final String name = etName.getText().toString();
            final int age = Integer.parseInt(etAge.getText().toString());
            final String username = etUsernameR.getText().toString();
            final String password = etPasswordR.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    public Boolean jsonResponse;

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success =  jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(Register.this, Login.class);
                                Register.this.startActivity(intent);
                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Register failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener );
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
        }

    });
}
    }