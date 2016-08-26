package com.example.nikhilsridhar.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{

    Button save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.save:
                Toast.makeText(getApplicationContext(), "Saving changes..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.cancel:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
