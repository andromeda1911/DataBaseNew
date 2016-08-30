package com.example.nikhilsridhar.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    Button save, cancel;
    EditText pass1, pass2, pass3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        pass3 = (EditText) findViewById(R.id.pass3);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.save:
                String p1 = pass1.getText().toString();
                String p2 = pass2.getText().toString();
                String p3 = pass3.getText().toString();
                if(p1.matches("") || p2.matches("") || p3.matches("") ) {
                    Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_LONG).show();
                    break;
                }
                else if(p2!=p3){
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    break;
                }
                Toast.makeText(getApplicationContext(), "Saving changes..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.cancel:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }
}
