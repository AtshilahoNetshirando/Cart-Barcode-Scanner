package com.example.sunday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class HomeActivity extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 4000;
    DatabaseOperations dob;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeintent = new Intent(HomeActivity.this,HomeIntroActivity.class);
                startActivity(homeintent);
                finish();
            }

        },SPLASH_TIME_OUT);





    }


}
