package com.example.nikhilsridhar.database;

/**
 * Created by nikhilsridhar on 16/08/16.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nikhilsridhar on 12/08/16.
 */
public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread t = new Thread(){

            @Override

            public void run(){
                try {
                    sleep(3000);
                    Intent startMainScreen = new Intent(getApplicationContext(), Login.class);
                    startActivity(startMainScreen);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        t.start();

    }

}
