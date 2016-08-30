package com.example.nikhilsridhar.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{

    Button save, cancel;
    EditText editName, editDesig, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        editName = (EditText) findViewById(R.id.editName);
        editDesig = (EditText) findViewById(R.id.editDesig);
        editEmail = (EditText) findViewById(R.id.editEmail);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.save:
               String eName = editName.getText().toString();
                String eDesig = editDesig.getText().toString();
                String eEmail = editEmail.getText().toString();
                        if(eName.matches("") || eDesig.matches("") || eEmail.matches(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_LONG).show();
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
