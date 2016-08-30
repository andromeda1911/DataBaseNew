package com.example.nikhilsridhar.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView usr, pass, bReg;
    EditText etUsername, etPassword;
    Button bLogin;


    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss);

        usr               = (TextView) findViewById(R.id.usr);
        pass              = (TextView) findViewById(R.id.pass);
        etUsername        = (EditText) findViewById(R.id.etUsername);
        etPassword        = (EditText) findViewById(R.id.etPassword);
        bLogin            = (Button) findViewById(R.id.bLogin);
        imageView         = (ImageView) findViewById(R.id.imageView);
        bReg              = (TextView) findViewById(R.id.bReg);

        bLogin.setOnClickListener(this);
        bReg.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()){


            case R.id.bLogin:
                if (etUsername.getText().toString().contains(" ")) {
                    etUsername.setError("No Spaces Allowed");
                    Toast.makeText(this, "No Spaces Allowed", Toast.LENGTH_SHORT).show();
                    break;
                }
                int us = etUsername.getText().length();
                int pswrd = etPassword.getText().length();
                if(us >6 && pswrd >6){
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_in);
                    break;
                }
                else
                    Toast.makeText(this, "Min 7 characters for username & password without spaces", Toast.LENGTH_SHORT).show();
                break;

            case R.id.bReg:
                startActivity(new Intent(this, Register.class));
                break;

        }
    }}
